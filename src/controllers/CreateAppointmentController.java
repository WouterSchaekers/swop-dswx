package controllers;

import java.util.Collection;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.tasks.AppointmentDescription;
import scheduler.tasks.Task;
import users.Doctor;
import users.Nurse;
import users.User;
import users.UserFilter;
import controllers.interfaces.DoctorIN;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileOpenController;

public class CreateAppointmentController extends NeedsLoginController
{
	public CreateAppointmentController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidPatientFileOpenController {
		super(lc);
	}

	/**
	 * @return The date on which the appointment has been scheduled.
	 * @throws CanNeverBeScheduledException
	 * @throws InvalidNameException 
	 */
	public HospitalDate scheduleNewAppointment(String doctorName, String patientName)
			throws CanNeverBeScheduledException, InvalidNameException {
		AppointmentDescription description;
		try {
			description = new AppointmentDescription((Doctor) (UserFilter.SpecificDoctorFilter(hospital
					.getUserManager().getAllUsers(), doctorName)), this.getPatient(patientName), hospital
					.getTimeKeeper().getSystemTime());
		} catch (InvalidHospitalDateException e) {
			throw new Error(e);
		}
		Task<?> t = hospital.getTaskManager().add(description);
		hospital.getTaskManager().update(t, null);
		if (t.isScheduled())
			return t.getDate();
		throw new IllegalStateException("This should not happen... Appointment was not scheduled but queued!");
	}

	public Collection<DoctorIN> getAllDoctors() {
		return UserFilter.DoctorFilter(hospital.getUserManager().getAllUsers());
	}

	private PatientFile getPatient(String name) throws InvalidNameException {

		return hospital.getPatientFileManager().getPatientFileFrom(name);

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
