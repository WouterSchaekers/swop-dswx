package treatment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import patient.Diagnose;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.tasks.Task;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.TaskDescriptionWithPatientFile;
import warehouse.Warehouse;
import exceptions.InvalidReportException;

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment extends TaskDescriptionWithPatientFile
{

	protected final Diagnose diagnose_;
	protected final Warehouse warehouse_;

	/**
	 * Default constructor of Treatment.
	 * 
	 * @param patientFile
	 *            The patientFile.
	 * @param duration
	 *            The duration of the task.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @param diagnose
	 *            The diagnose of this cast.
	 * @param warehouse
	 *            The warehouse where the materials for this cast will come
	 *            from.
	 */
	public Treatment(PatientFile patientFile, Diagnose diagnose, long duration, HospitalDate creationDate,
			Warehouse warehouse) {
		super(patientFile, duration, 0, creationDate);
		this.diagnose_ = diagnose;
		this.warehouse_ = warehouse;
	}

	/**
	 * Returns the Result of the Treatment.
	 * 
	 * @return The Result of the Treatment.
	 */
	@Override
	public Result getResult() {
		return result_;
	}

	/**
	 * Sets the result of the Description.
	 * 
	 * @param result
	 *            The result of the Description.
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
	 * Initializes the task.
	 * 
	 * @param task
	 *            The task that has to be initialized.
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public <T extends TaskDescription> void initTask(Task<T> task) {
		Collection<Diagnose> diags = this.patientFile_.getAllDiagnosis();
		for (Diagnose d : diags) {
			if (d == diagnose_) {
				d.addTreatmentTask((Task<? extends Treatment>) task);
				return;
			}
		}
		throw new IllegalArgumentException(
				"Error! PatientFile does not contain the diagnose for the treatment created!");
	}

	/**
	 * Deinitializes the task. All the diagnoses of the patientFile that have
	 * the given task will be removed from the diagnose.
	 * 
	 * @param task
	 *            The task that has to be deinitialized.
	 */
	@Override
	public <T extends TaskDescription> void deInit(Task<T> task) {
		Collection<Diagnose> diags = this.patientFile_.getAllDiagnosis();
		for (Diagnose d : diags) {
			if (d.getTreatments().contains(task.getDescription())) {
				((Diagnose) d).removeTreatment(task);
				return;
			}
		}
		throw new IllegalArgumentException(
				"Error! PatientFile does not contain the diagnose for the treatment that's being deinitialised.!");
	}

	/**
	 * Returns whether the Description needs a result.
	 * 
	 * @return True if the Description needs a result.
	 */
	@Override
	public boolean needsResult() {
		return result_ == null;
	}

	/**
	 * Returns the observables that observe this treatment.
	 */
	@Override
	public Collection<Observable> getObservables() {
		ArrayList<Observable> observables = new ArrayList<Observable>();
		observables.add(diagnose_);
		observables.add(warehouse_);
		return observables;
	}
}