package controllers;

import java.util.Collection;
import patient.Patient;
import scheduler.HospitalDate;
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

	public CreateAppointmentController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidPatientFileOpenController {
		super(lc);
	}

	/**
	 * @return
	 * The date on which the appointment has been scheduled
	 */
	public HospitalDate scheduleNewAppointment(String doctorName, String patientName) {
		// TODO: fix
		AppointmentDescription ad = new AppointmentDescription(
				(Doctor) lc.getSpecificDoctor(doctorName),
				this.getPatient(patientName));
		return null;
	}

	public Collection<DoctorIN> getAllDoctors() {
		return null;
		// TODO: fix
	}

	private Patient getPatient(String name) {
		return null;
		// TODO: fix
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
