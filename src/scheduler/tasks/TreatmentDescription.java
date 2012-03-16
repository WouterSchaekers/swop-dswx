package scheduler.tasks;

import patient.PatientFile;
import scheduler.HospitalDate;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public abstract class TreatmentDescription extends TaskDescription
{

	public TreatmentDescription(PatientFile patientFile, long duration, HospitalDate creationTime)
			throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, duration, 0, creationTime);
	}
}