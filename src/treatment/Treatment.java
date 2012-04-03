package treatment;

import java.util.Collection;
import patient.Diagnose;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.tasks.Task;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.TaskDescriptionWithPatientFile;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.TreatmentIN;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidReportException;

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment extends TaskDescriptionWithPatientFile implements TreatmentIN
{

	/**
	 * Default constructor of Treatment.
	 * 
	 * @param patientFile
	 *            The patientFile.
	 * @param duration
	 *            The duration of the task.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @throws InvalidHospitalDateException
	 *             The given creationDate was not valid.
	 */
	public Treatment(PatientFile patientFile, long duration, HospitalDate creationDate)
			throws InvalidHospitalDateException {
		super(patientFile, duration, 0, creationDate);
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
		Collection<DiagnoseIN> diags = this.patientFile_.getAllDiagnosisIN();
		for (DiagnoseIN d : diags) {
			if (d.getTreatmentsIN().contains(task.getDescription())) {
				((Diagnose) d).removeTreatment(task);
				return;
			}
		}
		throw new IllegalArgumentException(
				"Error! PatientFile does not contain the diagnose for the treatment that's being deinitialised.!");
	}

	/**
	 * 
	 */
	@Override
	public Result getResult() {
		return result_;
	}

	public void setResult(String result) {
		try {
			this.result_ = new Result(result);
		} catch (InvalidReportException e) {
			throw new Error(e);
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public <T extends TaskDescription> void initTask(Task<T> task) {
		Collection<DiagnoseIN> diags = this.patientFile_.getAllDiagnosisIN();
		for (DiagnoseIN d : diags) {
			if (d.getTreatmentsIN().contains(task.getDescription())) {
				((Diagnose) d).addTreatment((Task<? extends Treatment>) task);
				return;
			}
		}
		throw new IllegalArgumentException(
				"Error! PatientFile does not contain the diagnose for the treatment created!");
	}

	@Override
	public boolean needsResult() {
		return result_ == null;
	}
}