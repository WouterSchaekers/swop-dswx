package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.tasks.Task;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.TaskDescriptionWithPatientFile;
import exceptions.InvalidReportException;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest extends TaskDescriptionWithPatientFile
{
	protected Result result_;

	/**
	 * Default constructor.
	 * 
	 * @param patientFile
	 *            The patientFile for which this cast is intended.
	 * @param duration
	 *            The duration of this medical test.
	 * @param creationDate
	 *            The date on which this description has been created.
	 */
	MedicalTest(PatientFile patientFile, long duration, HospitalDate creationDate) {
		super(patientFile, duration, 0, creationDate);
	}

	/**
	 * Initializes the MedicalTest.
	 * 
	 * @param task
	 *            The task that has to be initialized.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends TaskDescription> void initTask(Task<T> task) {
		this.patientFile_.addMedicalTest((Task<? extends MedicalTest>) task);
	}

	/**
	 * @throws IllegalStateException
	 * when called. Medical tests should never be deinitialised.
	 */
	@Override
	public <T extends TaskDescription> void deInit(Task<T> task) {
		throw new IllegalStateException("Medical tests should NEVER be deinitialised!!");
	}

	/**
	 * @return The result.
	 */
	public Result getResult() {
		return this.result_;
	}

	/**
	 * Returns whether the MedicalTest needs a result.
	 * 
	 * @return True if the MedicalTest needs a result.
	 */
	@Override
	public boolean needsResult() {
		return result_ == null;
	}

	/**
	 * Sets the result of the MedicalTest.
	 * 
	 * @param result
	 *            The result of the MedicalTest.
	 */
	@Override
	public void setResult(String result) {
		try {
			this.result_ = new Result(result);
		} catch (InvalidReportException e) {
			throw new Error(e);
		}
	}

	/**
	 * Returns the observables that observe this medical test.
	 */
	@Override
	public Collection<Observable> getObservables() {
		return new LinkedList<Observable>();
	}
}