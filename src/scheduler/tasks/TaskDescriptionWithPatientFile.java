package scheduler.tasks;

import patient.PatientFile;
import scheduler.HospitalDate;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalDateException;

/**
 * This class is an abstract representation of a TaskDescriptionWithPatientFile.
 * The TaskDescriptionWithPatientFile is a TaskDescription with a patientFile.
 */
public abstract class TaskDescriptionWithPatientFile extends TaskDescription
{
	protected final PatientFile patientFile_;

	/**
	 * Default constructor of the TaskDescriptionWithPatientFile.
	 * 
	 * @param patientFile
	 *            The patientFile.
	 * @param duration
	 *            The duration of the task.
	 * @param extraTime
	 *            The amount of time this task need to be scheduled later than
	 *            the current time.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @throws InvalidHospitalDateException
	 *             The given creationDate was not valid.
	 */
	public TaskDescriptionWithPatientFile(PatientFile patientFile, long duration, long extraTime,
			HospitalDate creationDate) throws InvalidHospitalDateException {
		super(duration, extraTime, creationDate);
		this.patientFile_ = patientFile;
	}

	/**
	 * Returns the patientFileIN.
	 * 
	 * @return The patientFileIN.
	 */
	public PatientFileIN getPatientFile() {
		return patientFile_;
	}
}