package medicaltest;

import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.tasks.Task;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.TaskDescriptionWithPatientFile;
import controllers.interfaces.MedicalTestIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidReportException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest extends TaskDescriptionWithPatientFile implements MedicalTestIN
{
	protected Result result;

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
	public MedicalTest(PatientFile patientFile_, long duration_, HospitalDate creationTime_)
			throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile_, duration_, 0, creationTime_);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends TaskDescription> void deInit(Task<T> task) {
		this.patientFile_.removeTest((Task<? extends MedicalTest>) task);
	}

	public Result getResult() {
		return this.result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends TaskDescription> void initTask(Task<T> task) {
		this.patientFile_.addMedicalTest((Task<? extends MedicalTest>) task);
	}

	public boolean needsResult() {
		return result == null;
	}

	public void setResult(String result) {
		try {
			this.result = new Result(result);
		} catch (InvalidReportException e) {
			throw new Error(e);
		}
	}

}