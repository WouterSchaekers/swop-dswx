package controllers;

import java.util.Collection;
import patient.Patient;
import scheduler2.AppointmentDescription;
import system.Hospital;
import users.Doctor;
import users.Nurse;
import users.User;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class CreateAppointmentController extends NeedsLoginController
{

	public CreateAppointmentController(Hospital hospital,
			LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidPatientFileOpenController {
		super(hospital, controller);
	}

	public void scheduleNewAppointment(DoctorIN d, String patientName) {
		// TODO: fix
		AppointmentDescription ad = new AppointmentDescription((Doctor) d, p);
	}

	public Collection<DoctorIN> getAllDoctors() {
		return null;
		//TODO: fix
	}
	
	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
