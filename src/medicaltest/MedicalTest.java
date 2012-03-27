package medicaltest;

import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.tasks.TaskDescriptionWithPatienFile;
import controllers.interfaces.MedicalTestIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidResultException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest extends TaskDescriptionWithPatienFile implements MedicalTestIN
{
	private Result result;

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            The name of this medical test.
	 * @param duration
	 *            The duration of this medical test.
	 * @throws InvalidDurationException
	 *             If(!isValidDuration(duration))
	 * @throws InvalidHospitalDateException 
	 * @throws InvalidAmountException 
	 * @throws InvalidTimeSlotException
	 */
	public MedicalTest(PatientFile patientFile_, long duration_, HospitalDate creationTime_) throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile_, duration_, 0, creationTime_);
	}

	/**
	 * @return True if r is a valid Result.
	 */
	private boolean isValidResult(Result r) {
		return r != null;
	}

	public void setResult(Result r) throws InvalidResultException {
		if(! this.isValidResult(r))
			throw new InvalidResultException("Invalid result for medical test!");
		this.result = r;
	}
	
	public Result getResult(){
		return this.result;
	}

	public boolean hasFinished() {
		return this.result != null;
	}
}