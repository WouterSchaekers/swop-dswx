package testscenarios;

import static org.junit.Assert.fail;
import java.util.Collection;
import machine.MachineBuilder;
import org.junit.Test;
import scheduler.HospitalDate;
import system.Hospital;
import users.HospitalAdmin;
import controllers.AddHospitalEquipmentController;
import controllers.AddHospitalStaffController;
import controllers.CreateAppointmentController;
import controllers.LoginController;
import controllers.RegisterPatientController;
import controllers.interfaces.DoctorIN;
import exceptions.DischargePatientException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidAmountException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;
import exceptions.InvalidResourceException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class TestScenarios
{

	// maybe using strings as a key for patient files isn't the best way of
	// doing things...
	// then again we prefer not passing any objects from the domain layer to the
	// ui.

	/**
	 * A basic scenario where only one of each exists and only basic use cases
	 * are walked through.
	 * 
	 * <p>
	 * <b><u>.::Excuting::.</u></b>
	 * </p>
	 * <li>Initialise the hospital with empty constructor. <li>Hospital admin
	 * logs in. <li>Hospital admin adds a doctor, a nurse and an XRayScanner to
	 * the hospital. <li>Hospital admin logs out <li>Nurse Jenny logs in. <li>
	 * Nurse Jenny registers a new patient: Dieter Geboers. <li>Nurse Jenny asks
	 * the system for available doctors. <li>Nurse Jenny tells the system that
	 * she wants to schedule an appointment for Dieter Geboers with doctor
	 * Jonathan <br>
	 * <br>
	 * Scenario ends here.
	 */
	@Test
	public void scenario1() throws UserAlreadyExistsException,
			InvalidNameException, InvalidTimeSlotException,
			InvalidLoginControllerException, InvalidHospitalException,
			InvalidLocationException, InvalidSerialException,
			InvalidPatientFileOpenController {
		fail("Controllerlayer not implemented fully yet (this is supposed to happen.");
		System.out.print("Creating and intialising hospital... ");
		Hospital h = new Hospital();
		HospitalAdmin ha = new HospitalAdmin("admin");
		System.out.println("Success!");

		// Creating the login controller for the hadmin
		System.out.print("Logging in the hospital admin... ");
		LoginController lc = new LoginController(h);
		lc.logIn(ha);
		System.out.println("Success!\n");

		// the hadmin add XRayScanner, nurse, doctor,
		System.out.print("Adding a new nurse: Jenny...");
		AddHospitalStaffController ahsc = new AddHospitalStaffController(lc);
		ahsc.addNurse("Jenny", "Campus 1");
		System.out
				.print(" Nurse added successfully!\nAdding a new doctor: Jonathan...");
		ahsc.addDoctor("Jonathan", "Campus 1");
		System.out
				.print(" Doctor added successfully!\nAdding a new x-ray scanner...");
		AddHospitalEquipmentController ahec = new AddHospitalEquipmentController(
				lc);

		Collection<MachineBuilder> mbs = ahec.getAllMachineBuilders();
		for (MachineBuilder mb : mbs)
			if (mb.toString().equals("XrayScanner")) {
				ahec.createMachine(mb, 523, "Location X", h);
				break;
			}
		System.out.println(" X-ray scanner created successfully!");
		System.out.print("Logging out hospital admin...");
		lc = null;
		System.out.println("Hospital admin logged out successfully!\n");

		// TODO: adapt code so that it works with the Patient-class.
		System.out.println("A new patient has arrived: Dieter Geboers.\n");

		System.out.print("Logging in Jenny... ");
		// TODO: list scheduled tasks in which Jenny is involved in that still
		// have to be executed by her.
		lc = new LoginController(h);
		lc.logIn(lc.getSpecificNurse("Jenny"));
		System.out.println("Succes!");
		System.out.print("Registering new patient in the hospital... ");
		RegisterPatientController rpc = new RegisterPatientController(lc);
		rpc.registerNewPatient("Dieter Geboers");
		System.out
				.println("Dieter Geboers's patient file was created and added to the hospital's database successfully!");

		// schedule een appointment voor dieter met doctor jonathan
		System.out
				.println("Creating appointment for Dieter Geboers with a doctor... ");
		CreateAppointmentController cac = new CreateAppointmentController(lc);
		// Nurse Jenny asks for a list of available doctors
		System.out
				.print("Dear Jenny, the following doctors are available in this hospital: ");

		for (DoctorIN d : cac.getAllDoctors())
			System.out.println(d.getName());

		System.out.println(" Nurse Jenny selected doctor Jonathan.");
		System.out
				.print("Creating appointment for Dieter Geboers with doctor Jonathan... ");

		HospitalDate appDate = cac.scheduleNewAppointment("Jonathan",
				"Dieter Geboers");
		System.out.println("Appointment has been created successfully at "
				+ appDate);
		System.out.println("\n\nThis Scenario has been completed.\n\n");
	}

	/**
	 * <p>
	 * This scenario is the sequel to scenario 1. 2 new nurses have been added.
	 * There also are a blood analyser and an ultrasoundscanner available.
	 * </p>
	 * 
	 * <p>
	 * Doctor Jonathan has seen patient Dieter and has decided the best course
	 * of action is to experiment on him with 2 medical tests to determine the
	 * diagnose...
	 * </p>
	 * 
	 * <p>
	 * <b><u>.::Excuting::.</u></b>
	 * </p>
	 * <li>Doctor Jonathan logs in. <li>Doctor Jonathan opens the patient file
	 * of Dieter. <li>Doctor Jonathan creates a bloodanalysis for Dieter that
	 * focusses on Infections and has 5 iterations. <li>Doctor Jonathan creates
	 * an x-ray scan for Dieter that takes 3 images of Dieter's head and has
	 * zoom level 2.2. <li>Doctor Jonathan logs out. <li>The hospital
	 * administrator logs in. <li>The hospital administrator advances the time
	 * by 10 hours. <li>The hospital admin logs out. <li>Nurse Jenna logs in.
	 * <li>Nurse Jenna requests a list of unfinished medical tests. <li>Nurse
	 * Jenna enters the result of the blood analysis of Dieter. <li>Nurse Jenna
	 * enters the result of the x-ray scan of Dieter. <li>Nurse Jenna logs out.
	 * <li>Doctor Jonathan logs in. <li>Doctor Jonathan consults the patient
	 * file of Dieter. <li>Doctor Jonathan enters the diagnose for Dieter:
	 * "Hypochondriasis". <li>Doctor Jonathan discharges Dieter. <li>Doctor
	 * Jonathan logs out. <br>
	 * <br>
	 * The scenario ends here.
	 */
	@Test
	public void scenario2() throws UserAlreadyExistsException,
			InvalidNameException, InvalidTimeSlotException,
			InvalidLoginControllerException, InvalidHospitalException,
			InvalidLocationException, InvalidSerialException,
			InvalidPatientFileOpenController, InvalidPatientFileException,
			InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException, InvalidHospitalDateArgument,
			FactoryInstantiationException, InvalidDiagnoseException,
			InvalidDoctorException, DischargePatientException {
		/** Setting up the system **/
		//TODO: fix so that this works like the controllers do.....
//		Hospital h = new Hospital();
//		HospitalAdmin ha = new HospitalAdmin("admin");
//		LoginController lc = new LoginController(h);
//		lc.logIn(ha);
//		Nurse jen = new Nurse("Jenny");
//		Nurse joy = new Nurse("Joy");
//		Nurse jenna = new Nurse("Jenna");
//		Doctor jona = new Doctor("Jonathan");
//		h.getUserManager().addUser(jona, whereabouts)
//		h.getUserManager().createAndAddNurse("Jenny");
//		h.getUserManager().createAndAddNurse("Joy");
//		h.getUserManager().createAndAddNurse("Jenna");
//		h.getUserManager().createAndAddDoctor("Jonathan");
//		h.getMachinePool().createBloodAnalyser(0, "cool");
//		h.getMachinePool().createUltraSoundScanner(1, "cool");
//		h.getMachinePool().createXrayScanner(2, "cool2");
//		h.getPatientFileManager().registerPatient("Dieter Geboers");
//		lc = new LoginController(h);
//		/** End setting up the system **/
//
//		System.out.print("Logging in as doctor Jonathan.. ");
//		lc.logIn(lc.getSpecificDoctor("Jonathan"));
//		System.out.println("Success!");
//
//		System.out
//				.println(" Doctor Jonathan has requested to open the patient file of Dieter Geboers.");
//		System.out.print("Opening patient file... ");
//		ConsultPatientFileController cpfc = new ConsultPatientFileController(lc);
//		for (PatientFileIN pf : cpfc.getActivePatientFiles())
//			if (pf.getName().equals("Dieter Geboers")) {
//				cpfc.openPatientFile(pf);
//				System.out.println("PatientFile was opened successfully!");
//			}
//		OrderMedicalTestController omtc = new OrderMedicalTestController(lc,
//				cpfc);
//		Collection<MedicalTestFactory> mtf = omtc.getMedicalTestFactories();
//		for (MedicalTestFactory mf : mtf) {
//			if (mf instanceof BloodAnalysisFactory) {
//				System.out
//						.println(" Doctor Jonathan has specified the following focus: \"Infections\".");
//				((BloodAnalysisFactory) mf).setFocus("Infections");
//				System.out
//						.println(" Doctor Jonathan has specified the following amount of analysises: 5");
//				((BloodAnalysisFactory) mf).setNumberOfAnalysis(5);
//				HospitalDate d = omtc.addMedicaltest(mf.create());
//				System.out
//						.println("Blood analysisis has been successfully created!");
//
//				if (d == null)
//					System.out
//							.println("The bloodanalysis has not been scheduled yet.");
//				else
//					System.out
//							.println("The blood analysis was scheduled to go through on "
//									+ d);
//			}
//		}
//
//		System.out
//				.println(" Doctor Jonathan requested to perform an x-ray scan on Dieter.");
//		for (MedicalTestFactory mf : mtf) {
//			if (mf instanceof XRayScanFactory) {
//				System.out
//						.println(" Doctor Jonathan has specified the following body part: \"Head\".");
//				((XRayScanFactory) mf).setBodyPart("Head");
//				System.out
//						.println(" Doctor Jonathan has specified the following amount of images: 3");
//				((XRayScanFactory) mf).numberOfNeededImages(3);
//				System.out
//						.println(" Doctor Jonathan has specified the following zoomlevel: 2.2");
//				((XRayScanFactory) mf).setZoomLevel((float) 2.2);
//				HospitalDate d = omtc.addMedicaltest(mf.create());
//				System.out.println("X-ray scan has been successfully created!");
//
//				if (d == null)
//					System.out
//							.println("The x-ray scan has not been scheduled yet.");
//				else
//					System.out
//							.println("The x-ray scan was scheduled to go through on "
//									+ d);
//			}
//		}
//		System.out.print("Logging out doctor Jonathan... ");
//		lc = new LoginController(h);
//		System.out.println("Success!\n\n");
//		System.out.print("Logging in the hospital admin... ");
//		lc.logIn(h.getHospitalAdmin());
//		System.out.println("Success!");
//		System.out
//				.println("\n The hospital admin has requested to advance the time by 10 hours.");
//		System.out.print("\nAdvancing system time by 10 hours... ");
//		AdvanceTimeController atc = new AdvanceTimeController(lc);
//		atc.setNewSystemTime(new HospitalDate(h.getSystemTime().getSystemTime()
//				.getTimeSinceStart()
//				+ HospitalDate.ONE_HOUR * 10));
//		System.out.println("Success!");
//		System.out.println(" The hospital admin has requested to log out.");
//		System.out.print("Logging out hospital admin... ");
//		lc = new LoginController(h);
//		System.out.println("Success!\n\n");
//		System.out.print("Logging in Nurse Jenna... ");
//		lc.logIn(lc.getSpecificNurse("Jenna"));
//		System.out.println("Success!\n");
//
//		System.out
//				.println(" Nurse Jenna has requested a list of all unfinished medical tests.");
//		EnterMedicaltestResultController emrc = new EnterMedicaltestResultController(
//				lc);
//
//		// TODO: do this properly
//		// FYI: it's just a concept ^-'
//		LinkedList<MedicalTest> mtests = emrc.getAllMedicalTests();
//		LinkedList<MedicalTest> unfinishedMtests = new LinkedList<MedicalTest>();
//		System.out
//				.println("Dear nurse Jenna, the following medical tests are unfinished and require a result:");
//
//		for (MedicalTest mt : mtests)
//			if (!mt.hasResult()) {
//				System.out.println("* " + mt.toString());
//				unfinishedMtests.add(mt);
//			}
//
//		System.out
//				.println(" Nurse Jenna has selected the blood analysis of Dieter Geboers.");
//		System.out
//				.println(" Nurse Jenna has submitted the following details: ");
//		String report = "* amount of blood drawn: 0.5 litre\n"
//				+ "* red cell count: 9001" + "* white cell count: 1337"
//				+ "* platelet count: 1234567890";
//		System.out.println(report);
//		emrc.addResultTo(report, 0);
//
//		System.out
//				.println(" Nurse Jenna has selected the x-ray scan of Dieter Geboers.");
//		System.out
//				.println(" Nurse Jenna has submitted the following details: ");
//		report = "* abnormalities: none\n * images taken: 3";
//		System.out.println(report);
//		emrc.addResultTo(report, 0);
//		lc = null;
//		System.out.println("Nurse Jenna was logged out.\n");
//
//		System.out.print("Logging in doctor Jonathan... ");
//		lc = new LoginController(h);
//		System.out.println("Success!");
//		System.out
//				.println(" Doctor Jonathan has requested to consult the patient file of Dieter.");
//		System.out.print("Opening patient file... ");
//		cpfc = new ConsultPatientFileController(lc);
//		for (PatientFileIN pf : cpfc.getActivePatientFiles())
//			if (pf.getName().equals("Dieter Geboers")) {
//				cpfc.openPatientFile(pf);
//				System.out.println("PatientFile was opened successfully!");
//			}
//
//		System.out
//				.println(" Doctor Jonathan has requested to enter a diagnose for Dieter Geboers's patient file");
//		EnterDiagnoseController edc = new EnterDiagnoseController(lc, cpfc);
//		System.out
//				.println(" Doctor Jonathan has entered the following diagnose \"Hypochondriasis\".\nDoctor Jonathan has indicated this diagnose does not need a second opinion from another doctor.");
//		edc.enterDiagnose("Hypochondriasis");
//		System.out.println("The diagnose has been entered successfully!");
//		System.out
//				.println(" Doctor Jonathan has requested to discharge patient Dieter Geboers.");
//		System.out.print("Discharging Dieter Geboers... ");
//		DischargePatientController dpc = new DischargePatientController(lc,
//				cpfc);
//		dpc.dischargePatient();
//		System.out.println("Success!");
//		lc = null;
//		System.out.println("Doctor Jonathan has been logged out.");
	}
}