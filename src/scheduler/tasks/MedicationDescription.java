package scheduler.tasks;

import patient.PatientFile;
import scheduler.HospitalDate;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public abstract class MedicationDescription extends TaskDescription
{

	public MedicationDescription(PatientFile patientFile, long duration,
			long extraTime, HospitalDate creationTime)
			throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, duration, extraTime, creationTime);
	}
}