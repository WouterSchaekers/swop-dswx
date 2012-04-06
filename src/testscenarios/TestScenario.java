package testscenarios;

import java.util.Collection;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTestFactory;
import patient.Diagnose;
import patient.PatientFile;
import result.BloodAnalysisResultFactory;
import result.SurgeryResultFactory;
import scheduler.HospitalDate;
import system.Hospital;
import system.Location;
import treatment.MedicationFactory;
import treatment.SurgeryFactory;
import treatment.TreatmentFactory;
import ui.UserFilter;
import users.Doctor;
import warehouse.item.MedicationType;
import warehouse.item.SleepingTabletsType;
import controllers.AdvanceTimeController;
import controllers.ConsultPatientFileController;
import controllers.CreateAppointmentController;
import controllers.DischargePatientController;
import controllers.EnterMedicaltestResultController;
import controllers.EnterTreatmentResultController;
import controllers.EvaluateDiagnoseController;
import controllers.LoginController;
import controllers.OrderMedicalTestController;
import controllers.PrescribeTreatmentController;
import controllers.interfaces.CampusIN;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.TaskIN;
import controllers.interfaces.UserIN;
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
		System.out.println("Success!\n");
		System.out.print("Nurse Jenna will now attempt to create an appointment with doctor Joe for Wouter... ");
		createAppointmentForWouter();
		System.out.println("Success!\n");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 30 minutes... ");
		advanceTime(HospitalDate.ONE_MINUTE * 30);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime() + "\n");
		System.out.print("Joanne will now attempt to prescribe a treatment for Dieter... ");
		letJoanneOrderDieterTreatment();
		System.out.println("Succes!\n");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 49 hours... ");
		advanceTime(HospitalDate.ONE_HOUR * 49);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime() + "\n");
		System.out.print("Nurse Jeffrey will now enter the results of the finished treatments and tests... ");
		letJeffreyEnterResults();
		System.out.println("Success!\n");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 10 minutes... ");
		advanceTime(HospitalDate.ONE_MINUTE * 10);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime() + "\n");
		System.out.print("Doctor Jennifer will now attempt to enter a new diagnose with treatment for Stefaan... ");
		letJenniferEnterDiagStef();
		System.out.println("Succes!\n");
		System.out.print("Doctor Jonathan will now attempt to approve the replacement diagnose for Stefaan... ");
		letJonathanApproveDiagStef();
		System.out.println("Success!\n");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 30 hours... ");
		advanceTime(HospitalDate.ONE_HOUR * 30);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime() + "\n");
		System.out.print("Nurse Jeffrey will now attempt to enter the result for the treatment for Stefaan... ");
		letJeffreyEnterStefTreatmentResult();
		System.out.println("Success!\n");
		System.out.print("Doctor Jens will now attempt to discharge Stefaan and Dieter... ");
		letJensDischargeStefAndDieter();
		System.out.println("Success!\n");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 10 hours... ");
		advanceTime(HospitalDate.ONE_HOUR * 10);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime() + "\n");
		System.out.println("\n Five new patients have arrived on Campus 2: Peter, Paul, Petra, Pauline and Paula.");
		System.out.println("\t Complaints: Pain in chest and difficulty breathing.\n");
		System.out.print("Nurse Joy will now attempt to register each of these new patients and create appointments for them... ");
		letJoyRegisterPatients();
		System.out.println("Success!\n");
		System.out.print("HospitalAdmin is trying to advance the hospital time by 45 minutes... ");
		advanceTime(HospitalDate.ONE_MINUTE * 45);
		System.out.println("Success! New system time is now " + hospital.getTimeKeeper().getSystemTime() + "\n");
		System.out.println("\n Thibault has arrived on Campus 1.");
		System.out.println("\t Complaints: stomach hurts, jaundice");
		System.out.println("Nurse Jenna will now check in Thibault and create an appointment for him... ");
		letJennaRegisterThibault();
		System.out.println("Success!\n");
	}

	private void letJenniferOrderStefTest() throws Exception {
		LoginController lc = loginUser(
				UserFilter.SpecificDoctorFilter(hospital.getUserManager().getAllUserINs(), "Jennifer"),
				hospital.getCampus("Campus 2"));
		ConsultPatientFileController cpfc = openPatientFile((Doctor) lc.getUserIN(), "Stefaan", lc);
		OrderMedicalTestController omtc = new OrderMedicalTestController(lc, cpfc);
		BloodAnalysisFactory bloodFac = getBloodFactory(omtc, "Adrenaline", 3, (PatientFile) cpfc.getPatientFile());
		orderMedicalTestFor(omtc, (PatientFile) cpfc.getPatientFile(), bloodFac);
		logoutUser(lc, cpfc);
	}

	private void createAppointmentForWouter() throws Exception {
		LoginController lc = loginUser(
				UserFilter.SpecificNurseFilter(hospital.getUserManager().getAllUserINs(), "Jenna"),
				hospital.getCampus("Campus 1"));
		CreateAppointmentController cac = new CreateAppointmentController(lc);
		DoctorIN joe = UserFilter.SpecificDoctorFilter(hospital.getUserManager().getAllUserINs(), "Joe");
		cac.scheduleNewAppointment(joe, PatientFileFilter(cac.getAllPatientFiles(), "Wouter"));
		logoutUser(lc, null);
	}

	private void letJoanneOrderDieterTreatment() throws Exception {
		LoginController lc = loginUser(
				UserFilter.SpecificDoctorFilter(hospital.getUserManager().getAllUserINs(), "Joanne"),
				hospital.getCampus("Campus 1"));
		ConsultPatientFileController cpfc = openPatientFile((Doctor) lc.getUserIN(), "Dieter", lc);
		PrescribeTreatmentController ptc = new PrescribeTreatmentController(lc, cpfc);

		// should have just one diagnose, so this is fine
		DiagnoseIN diag = ptc.getAllPossibleDiagnosis().iterator().next();
		SurgeryFactory surgFac = getSurgeryFactory(ptc, "Brain surgery", (PatientFile) cpfc.getPatientFile(), diag);
		prescribeTreatmentFor(ptc, diag, surgFac);
		logoutUser(lc, cpfc);
	}

	private void letJeffreyEnterResults() throws Exception {
		LoginController lc = loginUser(
				UserFilter.SpecificNurseFilter(hospital.getUserManager().getAllUserINs(), "Jeffrey"),
				hospital.getCampus("Campus 1"));
		EnterMedicaltestResultController emrc = new EnterMedicaltestResultController(lc);
		EnterTreatmentResultController etrc = new EnterTreatmentResultController(lc);
		// should be okay as there should only be one test and one treatment
		TaskIN tests = emrc.getMedicalTests().iterator().next();
		TaskIN treatment = etrc.getTreatments().iterator().next();
		BloodAnalysisResultFactory bloodResultFac = (BloodAnalysisResultFactory) tests.getResultFactory();
		bloodResultFac.setAmountOfBlood(2);
		bloodResultFac.setPlateletCount(500);
		bloodResultFac.setRedCellCount(1337);
		bloodResultFac.setWhiteCellCount(9001);
		emrc.setResult(tests, bloodResultFac);
		SurgeryResultFactory surgeryResultFac = (SurgeryResultFactory) treatment.getResultFactory();
		surgeryResultFac.setAfterCare("Treat with extreme caution. Do not approach without a blow torch");
		surgeryResultFac.setReport("Everything went fine. Patient has a new frontal lobe");
		etrc.setResult(treatment, surgeryResultFac);
		logoutUser(lc, null);
	}
	
	private void letJenniferEnterDiagStef() throws Exception {
		// disapprove cancer and create new diag
		LoginController lc = loginUser(
				UserFilter.SpecificDoctorFilter(hospital.getUserManager().getAllUserINs(), "Jennifer"),
				hospital.getCampus("Campus 2"));
		ConsultPatientFileController cpfc = openPatientFile((Doctor) lc.getUserIN(), "Stefaan", lc);
		EvaluateDiagnoseController edc = new EvaluateDiagnoseController(lc);
		Diagnose d = null;
		for (DiagnoseIN diag : edc.getPendingDiagnosis()) {
			d = (Diagnose) edc.disapproveDiagnose(diag, "Stressed out");
		}
		PrescribeTreatmentController ptc = new PrescribeTreatmentController(lc, cpfc);
		prescribeTreatmentFor(ptc, d,
				getMedicationFactory(ptc, "Sleeping pills to relax", d, new SleepingTabletsType(), false));
		logoutUser(lc, cpfc);
	}
	
	private void letJonathanApproveDiagStef() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private void letJeffreyEnterStefTreatmentResult() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private void letJensDischargeStefAndDieter() throws Exception {
		LoginController lc = loginUser(
				UserFilter.SpecificDoctorFilter(hospital.getUserManager().getAllUserINs(), "Jens"),
				hospital.getCampus("Campus 1"));
		ConsultPatientFileController cpfc = new ConsultPatientFileController(lc);
		cpfc.openPatientFile(PatientFileFilter(cpfc.getAllPatientFiles(), "Stefaan"));
		DischargePatientController dpc = new DischargePatientController(lc, cpfc);
		dpc.dischargePatient();
		cpfc.openPatientFile(PatientFileFilter(cpfc.getAllPatientFiles(), "Dieter"));
		dpc = new DischargePatientController(lc, cpfc);
		dpc.dischargePatient();
		logoutUser(lc, cpfc);
	}

	private void letJoyRegisterPatients() throws Exception {
		//TODO
	}

	private void letJennaRegisterThibault() throws Exception {
		//TODO
	}

	private LoginController loginUser(UserIN user, Location location) throws Exception {
		LoginController lc = new LoginController(this.hospital);
		lc.logIn(user, (CampusIN) location);
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

	private MedicationFactory getMedicationFactory(PrescribeTreatmentController ptc, String description,
			DiagnoseIN diagnose, MedicationType itemType, boolean sensitive) {
		Collection<TreatmentFactory> facs = ptc.getTreatmentFactories();
		for (TreatmentFactory curFac : facs) {
			if (curFac instanceof MedicationFactory) {
				curFac.setDiagnose(diagnose);
				((MedicationFactory) curFac).setDescription(description);
				((MedicationFactory) curFac).setMedicationType(itemType);
				((MedicationFactory) curFac).setSensitive(sensitive);
				return (MedicationFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no SurgeryFactory exists?");
	}

//	private UltraSoundScanFactory getUltraSoundScanFactory(OrderMedicalTestController omtc, String focus,
//			boolean recVid, boolean recImg, PatientFile pf) {
//		Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
//		for (MedicalTestFactory curFac : facs) {
//			if (curFac instanceof UltraSoundScanFactory) {
//				((UltraSoundScanFactory) curFac).setFocus(focus);
//				((UltraSoundScanFactory) curFac).setRecordImages(recImg);
//				((UltraSoundScanFactory) curFac).setRecordVid(recVid);
//				curFac.setPatientFile(pf);
//				return (UltraSoundScanFactory) curFac;
//			}
//		}
//		throw new IllegalStateException("Apparently... no UltraSoundScanFactory exists?");
//	}
//
//	private XRayScanFactory getXRayScanFactory(OrderMedicalTestController omtc, String bodyPart, int number,
//			float zoom, PatientFile pf) {
//		Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
//		for (MedicalTestFactory curFac : facs) {
//			if (curFac instanceof XRayScanFactory) {
//				((XRayScanFactory) curFac).setBodyPart(bodyPart);
//				((XRayScanFactory) curFac).setNumberOfNeededImages(number);
//				((XRayScanFactory) curFac).setZoomLevel(zoom);
//				curFac.setPatientFile(pf);
//				return (XRayScanFactory) curFac;
//			}
//		}
//		throw new IllegalStateException("Apparently... no XRayScanFactory exists?");
//	}

	private void advanceTime(long amountOfTimeToAdvance) throws Exception {
		LoginController lc = loginUser(
				(UserIN) UserFilter.HospitalAdminFilter(hospital.getUserManager().getAllUserINs()),
				hospital.getCampus("Campus 1"));
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