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
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileOpenController;

@controllers.PUBLICAPI
public class CreateAppointmentController extends NeedsLoginController
{
	@controllers.PUBLICAPI
	public CreateAppointmentController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidPatientFileOpenController {
		super(lc);
	}

	/**
	 * @return The date on which the appointment has been scheduled.
	 * @throws CanNeverBeScheduledException
	 * @throws InvalidNameException 
	 */
	@controllers.PUBLICAPI
	public HospitalDate scheduleNewAppointment(DoctorIN doc, PatientFileIN patient)
			throws CanNeverBeScheduledException, InvalidNameException {
		AppointmentDescription description;
		try {
			description = new AppointmentDescription((Doctor) doc,(PatientFile) patient, hospital
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

	@controllers.PUBLICAPI
	public Collection<DoctorIN> getAllDoctors() {
		return UserFilter.DoctorFilter(new LinkedList<UserIN>(hospital.getUserManager().getAllUsers()));
	}
	public Collection<PatientFileIN> getAllPatientFiles()
	{
		return new ArrayList<PatientFileIN>( this.hospital.getPatientFileManager().getAllPatientFiles());
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
