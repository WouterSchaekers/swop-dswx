package testscenarios;

import static org.junit.Assert.assertTrue;
import java.util.Collection;
import java.util.Iterator;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTestFactory;
import medicaltest.UltraSoundScanFactory;
import medicaltest.XRayScanFactory;
import patient.PatientFile;
import scheduler.HospitalDate;
import system.Hospital;
import system.Location;
import treatment.SurgeryFactory;
import treatment.TreatmentFactory;
import ui.UserFilter;
import users.Doctor;
import controllers.AdvanceTimeController;
import controllers.ConsultPatientFileController;
import controllers.CreateAppointmentController;
import controllers.EnterMedicaltestResultController;
import controllers.LoginController;
import controllers.OrderMedicalTestController;
import controllers.PrescribeTreatmentController;
import controllers.interfaces.CampusIN;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.TaskIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * Executes our testing scenario by only using the controllers.
 */
public class TestScenario
{

	private Hospital hospital;

	public TestScenario(Hospital initialisedHospital) throws Exception {
		hospital = initialisedHospital;
		runScenario();
	}

	private void runScenario() throws Exception {
		System.out.print("Doctor Jennifer will now attempt to schedule a new medical test for Stefaan... ");
		letJenniferOrderStefTest();
		System.out.println("Success!");
		System.out.print("Nurse Jenna will now attempt to create an appointment with doctor Joe for Wouter... ");
		createAppointmentForWouter();
		System.out.println("Success!");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 30 minutes... ");
		advanceTime(HospitalDate.ONE_MINUTE * 30);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime());
		System.out.print("Joanne will now attempt to prescribe a treatment for Dieter... ");
		letJoanneOrderDieterTreatment();
		System.out.println("Succes!");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 10 hours... ");
		advanceTime(HospitalDate.ONE_HOUR * 10);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime());
		System.out.print("Pending medical tests and treatments should all have happened now. Checking... ");
		for(TaskIN curTask : hospital.getTaskManager().getAllTasks()) 
			assertTrue(curTask.isFinished());
		System.out.println("Success!");
		System.out.println("Nurse Jeffrey will now enter the results of the finished treatments... ");
		letJeffreyEnterResults();
		System.out.println("Success!");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 10 minutes... ");
		advanceTime(HospitalDate.ONE_MINUTE * 10);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime());
		System.out.print("Doctor Jens will now attempt to discharge Stefaan and Dieter... ");
		letJensDischargeStefAndDieter();
		System.out.println("Success!");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 10 hours... ");
		advanceTime(HospitalDate.ONE_HOUR * 10);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime());
		System.out.println("\n Five new patients have arrived on Campus 2: Peter, Paul, Petra, Pauline and Paula.");
		System.out.println("\t Complaints: Pain in chest and difficulty breathing.");
		System.out.println("Nurse Joy will now attempt to register each of these new patients and create appointments for them... ");
		letJoyRegisterPatients();
		System.out.println("Success!");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 45 minutes... ");
		advanceTime(HospitalDate.ONE_MINUTE * 45);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime());
		System.out.println("\n Thibault has arrived on Campus 1.");
		System.out.println("\t Complaints: stomach hurts, jaundice");
		System.out.println("Nurse Jenna will now check in Thibault and create an appointment for him... ");
		letJennaRegisterThibault();
		System.out.println("Success!");
	}

	private void letJenniferOrderStefTest() throws Exception {
		LoginController lc = loginUser("Jennifer", hospital.getCampus("Campus 2"));
		ConsultPatientFileController cpfc = openPatientFile((Doctor) lc.getUserIN(), "Stefaan", lc);
		OrderMedicalTestController omtc = new OrderMedicalTestController(lc, cpfc);
		BloodAnalysisFactory bloodFac = getBloodFactory(omtc, "Adrenaline", 3, (PatientFile) cpfc.getPatientFile());
		orderMedicalTestFor(omtc, (PatientFile) cpfc.getPatientFile(), bloodFac);
		logoutUser(lc, cpfc);
	}
	
	private void createAppointmentForWouter() throws Exception {
		LoginController lc = loginUser("Jenna", hospital.getCampus("Campus 1"));
		CreateAppointmentController cac = new CreateAppointmentController(lc);
		DoctorIN joe = UserFilter.SpecificDoctorFilter(hospital.getUserManager().getAllUserINs(), "Joe");
		cac.scheduleNewAppointment(joe, PatientFileFilter(cac.getAllPatientFiles(), "Wouter"));
		//TODO: check of de appointment in wouter zijn campus gescheduled wordt en niet op campus2!
		logoutUser(lc, null);
	}
	
	private void letJoanneOrderDieterTreatment() throws Exception {
		LoginController lc = loginUser("Joanne", hospital.getCampus("Campus 1"));
		ConsultPatientFileController cpfc = openPatientFile((Doctor) lc.getUserIN(), "Dieter", lc);
		PrescribeTreatmentController ptc = new PrescribeTreatmentController(lc, cpfc);

		// should have just one diagnose, so this is fine
		DiagnoseIN diag = ptc.getAllPossibleDiagnosis().iterator().next(); 
		SurgeryFactory surgFac = getSurgeryFactory(ptc, "Brain surgery", (PatientFile) cpfc.getPatientFile(), diag);
		prescribeTreatmentFor(ptc, diag, surgFac);
		logoutUser(lc, cpfc);
	}

	private void letJeffreyEnterResults() throws Exception {
		LoginController lc = loginUser("Jeffrey", hospital.getCampus("Campus 1"));
		EnterMedicaltestResultController emrc = new EnterMedicaltestResultController(lc);
		Collection<TaskIN> tasks = emrc.getMedicalTests();
		TaskIN curTask;
		Iterator<TaskIN> it = tasks.iterator();
		while(it.hasNext()) {
			curTask = it.next();
			//curTask.get();
		}
	}

	private void letJensDischargeStefAndDieter() throws Exception {
		
	}

	private void letJoyRegisterPatients() throws Exception {
		
	}

	private void letJennaRegisterThibault() throws Exception {
		
	}

	private LoginController loginUser(String name, Location location) throws Exception {
		LoginController lc = new LoginController(this.hospital);
		lc.logIn(lc.getSpecificDoctor(name), (CampusIN) location);
		return lc;
	}

	/**
	 * Breaks given controllers.
	 */
	private void logoutUser(LoginController lc, ConsultPatientFileController cpfc) {
		lc = null;
		cpfc = null;
	}

	private ConsultPatientFileController openPatientFile(Doctor doc, String patientName, LoginController lc)
			throws InvalidHospitalException, InvalidLoginControllerException {
		ConsultPatientFileController cpfc = new ConsultPatientFileController(lc);
		PatientFileIN patient = PatientFileFilter(cpfc.getAllPatientFiles(), patientName);
		cpfc.openPatientFile(patient);
		return cpfc;
	}

	private void orderMedicalTestFor(OrderMedicalTestController omtc, PatientFile pf, MedicalTestFactory factory)
			throws Exception {
		omtc.addMedicaltest(factory);
	}

	private void prescribeTreatmentFor(PrescribeTreatmentController ptc, DiagnoseIN diag, TreatmentFactory factory)
			throws Exception {
		ptc.addTreatment(diag, factory);
	}

	private BloodAnalysisFactory getBloodFactory(OrderMedicalTestController omtc, String focus, int number,
			PatientFile pf) {
		Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof BloodAnalysisFactory) {
				((BloodAnalysisFactory) curFac).setFocus(focus);
				((BloodAnalysisFactory) curFac).setNumberOfAnalysis(number);
				curFac.setPatientFile(pf);
				return (BloodAnalysisFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no BloodAnalysisFactory exists?");
	}

	private SurgeryFactory getSurgeryFactory(PrescribeTreatmentController ptc, String description,
			PatientFile patientFile, DiagnoseIN diagnose) {
		Collection<TreatmentFactory> facs = ptc.getTreatmentFactories();
		for (TreatmentFactory curFac : facs) {
			if (curFac instanceof SurgeryFactory) {
				((SurgeryFactory) curFac).setDescription(description);
				curFac.setDiagnose(diagnose);
				return (SurgeryFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no SurgeryFactory exists?");
	}

	private UltraSoundScanFactory getUltraSoundScanFactory(OrderMedicalTestController omtc, String focus,
			boolean recVid, boolean recImg, PatientFile pf) {
		Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof UltraSoundScanFactory) {
				((UltraSoundScanFactory) curFac).setFocus(focus);
				((UltraSoundScanFactory) curFac).setRecordImages(recImg);
				((UltraSoundScanFactory) curFac).setRecordVid(recVid);
				curFac.setPatientFile(pf);
				return (UltraSoundScanFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no UltraSoundScanFactory exists?");
	}

	private XRayScanFactory getXRayScanFactory(OrderMedicalTestController omtc, String bodyPart, int number,
			float zoom, PatientFile pf) {
		Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof XRayScanFactory) {
				((XRayScanFactory) curFac).setBodyPart(bodyPart);
				((XRayScanFactory) curFac).setNumberOfNeededImages(number);
				((XRayScanFactory) curFac).setZoomLevel(zoom);
				curFac.setPatientFile(pf);
				return (XRayScanFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no XRayScanFactory exists?");
	}

	private void advanceTime(long amountOfTimeToAdvance) throws Exception {
		LoginController lc = loginUser("admin", hospital.getCampus("Campus 1"));
		AdvanceTimeController atc = new AdvanceTimeController(lc);
		atc.setNewSystemTime(new HospitalDate(atc.getTime().getTimeSinceStart() + amountOfTimeToAdvance));
	}

	public PatientFileIN PatientFileFilter(Collection<PatientFileIN> patients, String name) {
		for (PatientFileIN u : patients)
			if (u.getPatientIN().getName().equals(name))
				return u;
		return null;
	}

}