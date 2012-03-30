package scheduler.tasks;

import patient.PatientFile;
import scheduler.HospitalDate;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public abstract class TaskDescriptionWithPatientFile extends TaskDescription
{
	protected final PatientFile patientFile_;

	public TaskDescriptionWithPatientFile(PatientFile patientFile, long duration, long extraTime,
			HospitalDate creationDate) throws InvalidAmountException, InvalidHospitalDateException {
		super(duration, extraTime, creationDate);
		this.patientFile_ = patientFile;
	}

}
