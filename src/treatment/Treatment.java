package treatment;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.tasks.TaskDescription;
import users.Nurse;
import controllers.interfaces.TreatmentIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidResultException;

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment extends TaskDescription implements TreatmentIN
{
	protected Result result_;

	/**
	 * Default constructor.
	 * 
	 * @param treatmentName
	 *            The name of this treatment.
	 * @throws InvalidHospitalDateException
	 * @throws InvalidAmountException
	 */
	public Treatment(PatientFile patientFile_, long duration_, HospitalDate creationTime_) throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile_, duration_, 0, creationTime_);
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
	public Collection<Requirement> getExecutors() {
		Collection<Requirement> executors = new LinkedList<Requirement>();
		executors.add(new RequirementType<Nurse>(Nurse.class));
		return executors;
	}
}