package controllers;

import java.util.Collection;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.tasks.AppointmentDescription;
import users.Doctor;
import users.Nurse;
import users.User;
import users.UserFilter;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class CreateAppointmentController extends NeedsLoginController
{

	public CreateAppointmentController(LoginController lc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(lc);
	}

	/**
	 * @return The date on which the appointment has been scheduled
	 */
	public HospitalDate scheduleNewAppointment(String doctorName,
			String patientName) {
		// TODO: fix
		try {
			new AppointmentDescription(
					(Doctor) (UserFilter.SpecificDoctorFilter(hospital
							.getUserManager().getAllUsers(), doctorName)),
					this.getPatient(patientName), null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Collection<DoctorIN> getAllDoctors() {

		return null;
		// TODO: fix
	}

	private PatientFile getPatient(String name) {
		return null;
		// TODO: fix
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
