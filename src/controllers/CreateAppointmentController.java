package controllers;

import scheduler2.AppointmentDescription;
import system.Hospital;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class CreateAppointmentController extends
		NeedsLoginAndPatientFileController
{

	public CreateAppointmentController(Hospital hospital,
			LoginController controller, ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(hospital, controller, pfoc);
	}

	public void scheduleNewAppointment(AppointmentDescription appDisc) {
		// TODO: fix
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
