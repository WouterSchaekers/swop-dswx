package testscenarios;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTestFactory;
import medicaltest.UltraSoundScanFactory;
import medicaltest.XRayScanFactory;
import patient.Diagnose;
import patient.PatientFile;
import patient.PatientFileManager;
import result.BloodAnalysisResultFactory;
import result.MedicationResultFactory;
import result.SurgeryResultFactory;
import scheduler.HospitalDate;
import scheduler.tasks.Task;
import system.Hospital;
import system.Location;
import treatment.CastFactory;
import treatment.Medication;
import treatment.MedicationFactory;
import treatment.SurgeryFactory;
import treatment.Treatment;
import treatment.TreatmentFactory;
import ui.UserFilter;
import users.Doctor;
import users.Nurse;
import users.User;
import warehouse.item.ActivatedCarbon;
import warehouse.item.ActivatedCarbonType;
import warehouse.item.AsprinType;
import warehouse.item.MedicationType;
import warehouse.item.SleepingTabletsType;
import controllers.AdvanceTimeController;
import controllers.CheckinController;
import controllers.ConsultPatientFileController;
import controllers.CreateAppointmentController;
import controllers.DischargePatientController;
import controllers.EnterDiagnoseController;
import controllers.EnterMedicaltestResultController;
import controllers.EnterTreatmentResultController;
import controllers.EvaluateDiagnoseController;
import controllers.FillStockInWarehouseController;
import controllers.LoginController;
import controllers.OrderMedicalTestController;
import controllers.PrescribeTreatmentController;
import controllers.RegisterPatientController;
import controllers.interfaces.CampusIN;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.TaskIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**------------------------------------------------------------
 * Testscenario:
 * Wa nog moet gebeuren is:
 * - todo-methods vanaf letJeffreyEnterStefTreatmentResult();
 * - alles vanaf wouter die een appointment heeft geschreven.
 * ------------------------------------------------------------
 * Hospital:
 --------
 Admin: [HospitalAdmin]

 Campus 1: 
 * Nurses [Jenny, Jenna, Jeffrey]
 * Doctors [Jonathan, Jens, Jelle, Joanne]				11,B&F,B&F,12
 * Machines [3 XrayScanners, 1 UltraSoundScanner, 0 BloodAnalysers]
 * WarehouseAdministrator: [Greg]

 Campus 2:
 * Nurses [Joy, Janna]
 * Doctors [Joe, Jennifer]
 * Machines [0 XrayScanners, 0 UltraSoundScanner, 3 BloodAnalysers]
 * WarehouseAdministrator: [Geoff]

 Patient Files:
 --------------
 * Stefaan: Diagnose = "cancer" by Jonathan; needs second opinion from Jennifer; complaints = "sleepy" -> MedicalTest: BloodAnalysis (voor jonathan)
 -> MedicalTest: BloodAnalysis (voor jennifer)
 -> Disaprove, new diagnose = "stress"
 -> Treatment = medication
 -> Result = "all is well"
 -> na medication: discharge()

 * Dieter: Diagnose = "Hypochondria", by doctor Joanne = approved; complaints = "Feel sick ALL the time"
 -> Treatment = "Surgery" (brain)
 -> Result = "alles ok"
 -> discharge()

 * Wouter: was just registered in the hospital by nurse Jenna.
 -> createAppointment with doctor Joe
 -> MedicalTest: bloodanalysis
 -> MedicalTest: XRayScan
 -> Diagnose: "fractured arm", approved
 -> treatment: cast, 100 dagen
 -> Result = "ok"
 -> discharge()

 * Thibault : discharged
 -> MedicalTests: UltraSoundScan, XRayScan, BloodAnalysis
 -> Diagnosis: "Fatal new variant of pneumonia"
 -> Treatment: surgery "Lung transplant"
 ------------------------------------------

 * Thibault has entered the hospital at campus 1
 -> Complaints "stomach hurts, jaundice"
 -> nurse Janna checks in thibault because he's already been at the hospital before
 -> Appointment with Joe
 -> MedicalTest: UltraSoundScan
 -> MedicalTest: XRayScan

 -> Diagnose "liver is failing"
 -> Treatment: Surgery "liver transplant"
 //
 //* Jonathan wants to change his preference  to B&F
 //
 //* Jelle wants to change his preference to 12

 //* Greg delivers stock orders that have arrived

 * Hospital administrator adds more staff and machines for teh_lulz

 * Geoff places some orders

 * 5 new patients come into campus 1 and all have the same sympthoms
 -> "Pain in chest, difficulty breathing"
 -> 5 XRays, 5 UltraSounds
 -> Diagnose "Heavy metal poisoning"
 -> Treatments = Cast AND Medication
 -> Result: 2 overleven, 3 gaan dood
 */

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
		UserIN user = getStefaansTreatment();
		System.out.print("Nurse " + user.getName()
				+ " will now attempt to enter the result for the treatment for Stefaan... ");
		enterTreatResultForStefaansMedication(user, campus1());
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
		System.out.println("Nurse Jenna will now check in Thibault and create an appointment for him with doctor ");
		letJennaRegisterThibault();
		System.out.println("Success!\n");
		System.out.println("Advance time 10 minutes.");
		advanceTime(HospitalDate.ONE_HOUR * 10);
		System.out.println("Doctor joe visited thibault and decided to run some test.");
		runThibaultsTestsByJoe();
		System.out.println("Advance time 1 day.");
		advanceTime(HospitalDate.ONE_DAY);
		System.out.println("Doctor joe gives a diagnose for " + getThibault());
		giveDiagnoseForThibaultDoctorJoe();
		System.out.println("Five patients that had difficulty breading are now being taken care of. ");
		fiveSickPatientsArrive();
	}

	private void fiveSickPatientsArrive() throws Exception {
		Collection<String> patients = Arrays.asList("");
		Collection<String> newPatients = Arrays.asList("Peter", "Paul", "Petra", "Pauline", "Paula");
		List<String> doctors = Arrays.asList("Jonathan", "Jens", "Jelle", "Joanne");
		int i = 0;
		for (String patient : newPatients) {
			String doc=doctors.get(i%4);
			String diag="Heavy metal poisoning.";
			String complaints = "Pain in chest, difficulty breathing";
			DiagnoseIN diagn =addDiagnose(patient,doc,diag,complaints);
			addCastAndCarbonMedicationTreatments(patient,doc,diagn);
			System.out.println("");
			System.out.println("One day later..");
			advanceTime(HospitalDate.ONE_DAY);
			System.out.println("");
			i++;
		}
		
	}

	

	private void addCastAndCarbonMedicationTreatments(String patient, String doc,DiagnoseIN diag) throws Exception {
		LoginController lc = getUser(doc);
		PrescribeTreatmentController c = new PrescribeTreatmentController(lc, getPatient(lc,patient));
		DiagnoseIN selected = null;
		for(DiagnoseIN d:c.getAllPossibleDiagnosis())
			if(d.equals(diag))
				selected = d;
	CastFactory castf=get(	c.getTreatmentFactories(),CastFactory.class);
	castf.setBodyPart("chest");
	castf.setDuration(100);
	HospitalDate date =c.addTreatment(selected, castf);
	System.out.println("Succesfully added Cast for patient "+patient+ " at "+date.toString());
	MedicationFactory medfact =  get(c.getTreatmentFactories(),MedicationFactory.class);
	medfact.setDescription("Treating heavy metal poisoning.");
	medfact.setMedicationType(get(c.getAvailableMedications(),ActivatedCarbonType.class));
	medfact.setSensitive(false);
	 date =c.addTreatment(selected, medfact);
	System.out.println("Succesfully added Activated Carbon medication for patient "+patient+ " at "+date.toString());

	}

	private DiagnoseIN addDiagnose(String patient, String doc, String diag,String complaints) throws Exception {
		LoginController lc = getUser(doc);
		EnterDiagnoseController diagcontroller = new EnterDiagnoseController(lc, getPatient(lc,patient));
		DiagnoseIN diagn =diagcontroller.enterDiagnose(complaints, diag);
		System.out.println("Diagnose succesfully entered by doctor "+doc+" for patient: "+patient);
		System.out.println("Diagnose: "+diag);
		System.out.println("Complaint: "+complaints);
		return diagn;
	}

	private ConsultPatientFileController getPatient(LoginController lc, String patient) throws Exception {
	ConsultPatientFileController rv = new ConsultPatientFileController(lc);
	for(PatientFileIN file :rv.getAllPatientFiles())
		if(file.getPatientIN().getName().equals(patient))
		{
			rv.openPatientFile(file);
			return rv;
		}
		return null;
	}

	private void giveDiagnoseForThibaultDoctorJoe() throws Exception {
		LoginController joe = getUser(joe());
		ConsultPatientFileController thibault = openPatientFile((Doctor) joe.getUserIN(), getThibault(), joe);
		EnterDiagnoseController diagController = new EnterDiagnoseController(joe, thibault);
		DiagnoseIN diagnose = diagController.enterDiagnose("stomach hurts, jaundice", "liver is failing");
		System.out.println("Diagnose succesfully entered!");
		PrescribeTreatmentController treatcontroller = new PrescribeTreatmentController(joe, thibault);
		System.out.println("Adding treatment.");
		SurgeryFactory fact = get(treatcontroller.getTreatmentFactories(), SurgeryFactory.class);
		fact.setDescription("Liver transplant ");
		HospitalDate date = treatcontroller.addTreatment(diagnose, fact);
		System.out.println("Liver transplant surgery scheduled at:" + date.toString());
		
	}
	
	private String joe() {
		return "Joe";
	}

	private String greg() {
		return "Greg";
	}

	private void runThibaultsTestsByJoe() throws Exception {
		LoginController joe = getUser("Joe");
		ConsultPatientFileController file = openPatientFile((Doctor) joe.getUserIN(), getThibault(), joe);
		OrderMedicalTestController medTestC = new OrderMedicalTestController(joe, file);
		System.out.println("");
		UltraSoundScanFactory fact = get(medTestC.getMedicalTestFactories(), UltraSoundScanFactory.class);
		fact.setFocus("stomach");
		fact.setRecordImages(true);
		fact.setRecordVid(true);
		fact.setPatientFile(file.getPatientFile());
		HospitalDate data = medTestC.addMedicaltest(fact);
		System.out.println("Ultrasoundscan:");
		System.out.println("Focus: stomach");
		System.out.println("RecordImages:true");
		System.out.println("RecordVid:true");
		System.out.println("Scheduled at:" + data.toString());
		XRayScanFactory xrayfact = get(medTestC.getMedicalTestFactories(), XRayScanFactory.class);
		xrayfact.setBodyPart("Lower body");
		xrayfact.setNumberOfNeededImages(3);
		xrayfact.setZoomLevel(2.12f);
		xrayfact.setPatientFile(file.getPatientFile());
		data = medTestC.addMedicaltest(xrayfact);
		System.out.println("Xray:");
		System.out.println("Bodypart: lower body");
		System.out.println("zoomlevel: 2.12");
		System.out.println("number of images:3");
		System.out.println("Scheduled at:" + data.toString());
	}

	private String getThibault() {
		return "Thibault";
	}

	private CampusIN campus1() {
		return hospital.getCampus("Campus 1");
	}

	private User getStefaansTreatment() {
		PatientFileManager pfoc = hospital.getPatientFileManager();

		for (PatientFile file : pfoc.getAllPatientFiles())
			if (file.getPatientIN().getName().equals(stefaan()))
				for (Diagnose diagnose : file.getAllDiagnosis())
					for (Task<? extends Treatment> treatment : diagnose.getTreatments())
						if (treatment.getDescription() instanceof Medication)
							return get(treatment.getResources(), Nurse.class);

		return null;
	}

	private String stefaan() {
		return "Stefaan";
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
		LoginController lc = loginUser(
				UserFilter.SpecificDoctorFilter(hospital.getUserManager().getAllUserINs(), "Jonathan"),
				hospital.getCampus("Campus 1"));
		EvaluateDiagnoseController edc = new EvaluateDiagnoseController(lc);
		for (DiagnoseIN diag : edc.getPendingDiagnosis()) {
			edc.approveDiagnose(diag);
		}
		logoutUser(lc, null);
	}

	private void enterTreatResultForStefaansMedication(UserIN nurse, CampusIN campus) throws Exception {
		LoginController lc = loginUser(nurse, (Location) campus);
		EnterTreatmentResultController etrc = new EnterTreatmentResultController(lc);

		TaskIN treatment = null;
		for (TaskIN task : etrc.getTreatments())
			if (task.getDescription() instanceof Medication) {
				treatment = task;
			}
		MedicationResultFactory resultFac = (MedicationResultFactory) treatment.getResultFactory();
		resultFac.setAbnormalReaction(false);
		resultFac
				.setReport("Patient slept for a while and feels perfectly fine again. Is fit to work some more on his projects.");
		etrc.setResult(treatment, resultFac);
		logoutUser(lc, null);
	}

	private void letJensDischargeStefAndDieter() throws Exception {
		LoginController lc = loginUser(
				UserFilter.SpecificDoctorFilter(hospital.getUserManager().getAllUserINs(), "Jens"),
				hospital.getCampus("Campus 1"));
		ConsultPatientFileController cpfc = new ConsultPatientFileController(lc);
		cpfc.openPatientFile(PatientFileFilter(cpfc.getAllPatientFiles(), "Stefaan"));
		DischargePatientController dpc = new DischargePatientController(lc, cpfc);
		dpc.dischargePatient();
		cpfc = new ConsultPatientFileController(lc);
		cpfc.openPatientFile(PatientFileFilter(cpfc.getAllPatientFiles(), "Dieter"));
		dpc = new DischargePatientController(lc, cpfc);
		dpc.dischargePatient();
		logoutUser(lc, cpfc);
	}

	private void letJoyRegisterPatients() throws Exception {
		Collection<String> newPatients = Arrays.asList("Peter", "Paul", "Petra", "Pauline", "Paula");
		for (String patient : newPatients) {
			RegisterPatientController controller = new RegisterPatientController(getJoy());
			controller.registerNewPatient(patient);

		}

	}

	private LoginController getJoy() throws Exception {
		return getUser("Joy");
	}

	private LoginController getUser(String string) throws Exception {
		LoginController c = new LoginController(hospital);
		for (UserIN user : c.getAllUsers())
			if (user.getName().equals(string)) {
				c.logIn(user, campus1());
				return c;
			}
		return null;
	}

	private void letJennaRegisterThibault() throws Exception {
		System.out.println("\t Complaints: stomach hurts, jaundice");
		System.out.println("Nurse Jenna will now check in Thibault and create an appointment for him... ");
		CheckinController controller = new CheckinController(getUser("Jenna"));
		PatientFileIN thibault = getPatient("Thibault", controller);
		controller.checkIn(thibault);
		String doc = "Joe";
		System.out.println("Thibault succesfully checked in.");
		CreateAppointmentController appointmentController = new CreateAppointmentController(getUser("Jenna"));
		DoctorIN doctor = selectDoctor(appointmentController.getAllDoctors(), doc);
		HospitalDate date = appointmentController.scheduleNewAppointment(doctor, thibault);
		System.out.println("Appointment with doctor" + doc + " created at:" + date.toString());

	}

	private DoctorIN selectDoctor(Collection<DoctorIN> allDoctors, String string) {
		for (DoctorIN d : allDoctors)
			if (d.getName().equals(string))
				return d;
		return null;
	}

	private PatientFileIN getPatient(String string, CheckinController controller) {
		for (PatientFileIN file : controller.getAllPatientFiles())
			if (file.getPatientIN().getName().equals(string))
				return file;
		return null;
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

	// private UltraSoundScanFactory
	// getUltraSoundScanFactory(OrderMedicalTestController omtc, String focus,
	// boolean recVid, boolean recImg, PatientFile pf) {
	// Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
	// for (MedicalTestFactory curFac : facs) {
	// if (curFac instanceof UltraSoundScanFactory) {
	// ((UltraSoundScanFactory) curFac).setFocus(focus);
	// ((UltraSoundScanFactory) curFac).setRecordImages(recImg);
	// ((UltraSoundScanFactory) curFac).setRecordVid(recVid);
	// curFac.setPatientFile(pf);
	// return (UltraSoundScanFactory) curFac;
	// }
	// }
	// throw new
	// IllegalStateException("Apparently... no UltraSoundScanFactory exists?");
	// }
	//
	// private XRayScanFactory getXRayScanFactory(OrderMedicalTestController
	// omtc, String bodyPart, int number,
	// float zoom, PatientFile pf) {
	// Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
	// for (MedicalTestFactory curFac : facs) {
	// if (curFac instanceof XRayScanFactory) {
	// ((XRayScanFactory) curFac).setBodyPart(bodyPart);
	// ((XRayScanFactory) curFac).setNumberOfNeededImages(number);
	// ((XRayScanFactory) curFac).setZoomLevel(zoom);
	// curFac.setPatientFile(pf);
	// return (XRayScanFactory) curFac;
	// }
	// }
	// throw new
	// IllegalStateException("Apparently... no XRayScanFactory exists?");
	// }

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

	<T> T get(Collection<? extends Object> coll, Class<T> clazz) {
		for (Object t : coll)
			if (t.getClass().equals(clazz))
				return (T) t;
		return null;
	}
}