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

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment extends TaskDescriptionWithPatientFile
{

	protected final Diagnose diagnose_;
	private Result result_;
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
	 *            The diagnose of this treatment.
	 */
	public Treatment(PatientFile patientFile, Diagnose diagnose, long duration, HospitalDate creationDate) {
		super(patientFile, duration, 0, creationDate);
		this.diagnose_ = diagnose;
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
			if (d.equals(diagnose_)) {
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
	 * Returns the observables that observe this treatment.
	 */
	@Override
	public Collection<Observable> getObservables() {
		ArrayList<Observable> observables = new ArrayList<Observable>();
		observables.add(diagnose_);
		return observables;
	}
	protected final void setResult(Result result)
	{
		result_=result;
	}
	public final Result getResult(){return result_;}
}