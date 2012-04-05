package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.tasks.AppointmentDescription;
import scheduler.tasks.Task;
import ui.UserFilter;
import users.Doctor;
import users.Nurse;
import users.User;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * Use to create appointments for patients who have just walked into the
 * hospital.
 */
@controllers.PUBLICAPI
public class CreateAppointmentController extends NeedsLoginController
{
	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that wants to create an
	 *            appointment for a patient.
	 * @throws InvalidLoginControllerException
	 *             If the given login controller is not one from a nurse or is
	 *             invalid in any other way.
	 * @throws InvalidHospitalException
	 * @see HospitalController
	 * @see NeedsLoginController
	 */
	@controllers.PUBLICAPI
	public CreateAppointmentController(LoginController loginController) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(loginController);
	}

	/**
	 * Allows you to schedule a new appointment for the given patient and the
	 * given doctor.
	 * 
	 * @return The date on which the appointment has been scheduled.
	 * @throws CanNeverBeScheduledException
	 *             If there are not enough doctors present in the hospital to
	 *             fulfill the scheduling request.
	 */
	@controllers.PUBLICAPI
	public HospitalDate scheduleNewAppointment(DoctorIN doctor, PatientFileIN patient)
			throws CanNeverBeScheduledException {
		AppointmentDescription description;
		description = new AppointmentDescription((Doctor) doctor, (PatientFile) patient, hospital.getTimeKeeper()
				.getSystemTime());
		Task<?> t = hospital.getTaskManager().add(description);

		if (t.isScheduled())
			return t.getDate();
		throw new IllegalStateException(
				"This should not happen... the created appointment was not scheduled but queued!");
	}

	/**
	 * @return A collection of all doctors working in this hospital.
	 */
	@controllers.PUBLICAPI
	public Collection<DoctorIN> getAllDoctors() {
		return UserFilter.DoctorFilter(new LinkedList<UserIN>(hospital.getUserManager().getAllUsers()));
	}

	/**
	 * @return A collection of all patient files that are stored in this
	 *         hospital.
	 */
	public Collection<PatientFileIN> getAllPatientFiles() {
		return new ArrayList<PatientFileIN>(this.hospital.getPatientFileManager().getAllPatientFiles());
	}

	/**
	 * @return True if the given user is a nurse.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
