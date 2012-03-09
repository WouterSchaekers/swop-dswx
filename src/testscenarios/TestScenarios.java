package testscenarios;
import java.util.Collection;
import machine.MachineBuilder;
import org.junit.Test;
import scheduler2.AppointmentDescription;
import system.CampusPreference;
import system.Hospital;
import users.HospitalAdmin;
import controllers.AddHospitalEquipmentController;
import controllers.AddHospitalStaffController;
import controllers.CreateAppointmentController;
import controllers.LoginController;
import controllers.RegisterPatientController;
import exceptions.*;


public class TestScenarios
{

	/**
	 * Excuting:
	 * - Initialise the hospital with empty constructor.
	 * - Hospital admin logs in.
	 * - Hospital admin adds a doctor, a nurse and an XRayScanner to the hospital.
	 * - Hospital admin logs out
	 * - Nurse Jenny logs in.
	 * - Nurse Jenny registers a new patient: Dieter Geboers.
	 * - ...
	 * @throws InvalidPatientFileOpenController 
	 */
	@Test
	public void scenario1() throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException, InvalidLoginControllerException, InvalidHospitalException, InvalidLocationException, InvalidSerialException, InvalidPatientFileOpenController {
		System.out.print("Creating and intialising hospital... ");
		Hospital h = new Hospital();
		HospitalAdmin hadmin = h.getUserManager().createHospitalAdmin("admin");
		System.out.println("Success!");
		// Creating the login controller for the hadmin
		System.out.print("Logging in the hospital admin... ");
		LoginController lc = new LoginController(h);
		lc.logIn(hadmin);
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
		System.out.print("Creating appointment for Dieter Geboers with a doctor... ");
		CreateAppointmentController cac = new CreateAppointmentController(h, lc);
		// Nurse Jenny asks for a list of available doctors
		System.out.print("Dear Jenny, the following doctors are available ");cac.getAllDoctors());
		
		AppointmentDescription ad = new AppointmentDescription(doctor, patient);
		cac.scheduleNewAppointment(appDisc)
	}
	
	/**
	 * Excuting:
	 * -  
	 */
	@Test
	public void scenario2() throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException, InvalidLoginControllerException, InvalidHospitalException, InvalidLocationException, InvalidSerialException {

	}

}
