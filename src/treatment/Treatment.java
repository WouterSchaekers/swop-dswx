package treatment;

import java.util.Collection;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.TaskDescriptionWithPatienFile;
import controllers.interfaces.TreatmentIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidResultException;

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment extends TaskDescriptionWithPatienFile implements TreatmentIN
{
	protected Result result_;

	public Treatment(PatientFile patientFile, long duration, HospitalDate creationTime) throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, duration, 0, creationTime);
	}

	public void setResult(Result r) throws InvalidResultException {
		if(! this.isValidResult(result_))
			throw new InvalidResultException("Invalid result given to Treatment");
		this.result_ = r;
	}
	
	/**
	 * @return True if r is a valid Result.
	 */
	private boolean isValidResult(Result r) {
		return r != null;
	}
	
	@Override
	public abstract Collection<Requirement> getAllRequirements();
}