package testscenarios;
import static org.junit.Assert.*;
import java.util.Collection;
import machine.MachineBuilder;
import org.junit.Test;
import scheduler.HospitalDate;
import system.CampusPreference;
import system.Hospital;
import controllers.AddHospitalEquipmentController;
import controllers.AddHospitalStaffController;
import controllers.ConsultPatientFileController;
import controllers.CreateAppointmentController;
import controllers.LoginController;
import controllers.RegisterPatientController;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileOpenController;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;


public class TestScenarios
{

	/**
	 * A basic scenario where only one of each exists.
	 * 
	 * Excuting:
	 * - Initialise the hospital with empty constructor.
	 * - Hospital admin logs in.
	 * - Hospital admin adds a doctor, a nurse and an XRayScanner to the hospital.
	 * - Hospital admin logs out
	 * - Nurse Jenny logs in.
	 * - Nurse Jenny registers a new patient: Dieter Geboers.
	 * - Nurse Jenny asks the system for available doctors.
	 * - Nurse Jenny tells the system that she wants to schedule an appointment for Dieter Geboers with doctor Jonathan
	 * - Scenario ends here.
	 * @throws InvalidPatientFileOpenController 
	 */
	@Test
	public void scenario1() throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException, InvalidLoginControllerException, InvalidHospitalException, InvalidLocationException, InvalidSerialException, InvalidPatientFileOpenController {
		fail("Controllerlayer not implemented fully yet (this is supposed to happen.");
		System.out.print("Creating and intialising hospital... ");
		Hospital h = new Hospital();
		System.out.println("Success!");
		
		// Creating the login controller for the hadmin
		System.out.print("Logging in the hospital admin... ");
		LoginController lc = new LoginController(h);
		lc.logIn(h.getHospitalAdmin());
		System.out.println("Success!\n");
		
		// the hadmin add XRayScanner, nurse, doctor, 
		System.out.print("Adding a new nurse: Jenny...");
		AddHospitalStaffController ahsc = new AddHospitalStaffController(lc, h);
		ahsc.addNurse("Jenny");
		System.out.print(" Nurse added successfully!\nAdding a new doctor: Jonathan...");
		ahsc.addDoctor("Jonathan", new CampusPreference());
		System.out.print(" Doctor added successfully!\nAdding a new x-ray scanner...");
		AddHospitalEquipmentController ahec = new AddHospitalEquipmentController(lc, h);
		
		Collection<MachineBuilder> mbs = ahec.getAllMachineBuilders();
		for(MachineBuilder mb: mbs)
			if(mb.toString().equals("XrayScanner")) {
				ahec.createMachine(mb, 523, "Location X");
				break;
			}
		System.out.println(" X-ray scanner created successfully!");
		System.out.print("Logging out hospital admin...");
		lc = null;
		System.out.println("Hospital admin logged out successfully!\n");
		
		//TODO: adapt code so that it works with the Patient-class.
		System.out.println("A new patient has arrived: Dieter Geboers.\n");
		
		System.out.print("Logging in Jenny... ");
		//TODO: list scheduled tasks in which Jenny is involved in that still have to be executed by her.
		lc = new LoginController(h);
		lc.logIn(lc.getSpecificNurse("Jenny"));
		System.out.println("Succes!");
		System.out.print("Registering new patient in the hospital... ");
		RegisterPatientController rpc = new RegisterPatientController(lc, h);
		rpc.registerNewPatient(h, "Dieter Geboers");
		System.out.println("Dieter Geboers's patient file was created and added to the hospital's database successfully!");
		
		// schedule een appointment voor dieter met doctor jonathan
		System.out.println("Creating appointment for Dieter Geboers with a doctor... ");
		CreateAppointmentController cac = new CreateAppointmentController(h, lc);
		// Nurse Jenny asks for a list of available doctors
		System.out.print("Dear Jenny, the following doctors are available in this hospital: ");
		
		for(DoctorIN d : cac.getAllDoctors())
			System.out.println(d.getName());
		
		System.out.println(" Nurse Jenny selected doctor Jonathan.");
		System.out.print("Creating appointment for Dieter Geboers with doctor Jonathan... ");
		
		HospitalDate appDate = cac.scheduleNewAppointment("Jonathan", "Dieter Geboers");
		System.out.println("Appointment has been created successfully at " + appDate);
		System.out.println("\n\nThis Scenario has been completed.\n\n");
	}
	
	/**
	 * This scenario is the sequel to scenario 1. 2 new nurses have been added.
	 * There also are a blood analyser and an ultrasoundscanner available.
	 * 
	 * Doctor Jonathan has seen patient Dieter and has decided the best course
	 * of action is to experiment on him with 3 medical tests to determine the
	 * diagnose. 
	 * 
	 * Excuting: 
	 * - Doctor Jonathan logs in.
	 * - Doctor Jonathan opens the patient file of Dieter.
	 * - Doctor Jonathan creates 
	 * @throws InvalidPatientFileOpenController
	 */
	@Test
	public void scenario2() throws UserAlreadyExistsException,
			InvalidNameException, InvalidTimeSlotException,
			InvalidLoginControllerException, InvalidHospitalException,
			InvalidLocationException, InvalidSerialException,
			InvalidPatientFileOpenController {
		/** Setting up the system **/
		Hospital h = new Hospital();
		LoginController lc = new LoginController(h);
		lc.logIn(h.getHospitalAdmin());
		h.getUserManager().createAndAddNurse("Jenny");
		h.getUserManager().createAndAddNurse("Joy");
		h.getUserManager().createAndAddNurse("Jenna");
		h.getUserManager().createAndAddDoctor("Jonathan");
		h.getMachinePool().createBloodAnalyser(0, "cool");
		h.getMachinePool().createUltraSoundScanner(1, "cool");
		h.getMachinePool().createXrayScanner(2, "cool2");
		h.getPatientFileManager().registerPatient("Dieter Geboers");
		lc = new LoginController(h);
		/** End setting up the system **/
		
		System.out.print("Logging in as doctor Jonathan.. ");
		lc.logIn(lc.getSpecificDoctor("Jonathan"));
		System.out.println("Success!");
		
		System.out.println("Doctor Jonathan has requested to open the patient file of Dieter Geboers.");
		System.out.print("Opening patient file... ");
		ConsultPatientFileController cpfc = new ConsultPatientFileController(h, lc);
		cpfc.
	}

}
