package treatment;

import java.util.Collection;
import patient.Diagnose;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.tasks.Task;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.TaskDescriptionWithPatientFile;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.TreatmentIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidReportException;

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment extends TaskDescriptionWithPatientFile implements TreatmentIN
{

	public Treatment(PatientFile patientFile, long duration, HospitalDate creationTime) throws InvalidAmountException,
			InvalidHospitalDateException {
		super(patientFile, duration, 0, creationTime);
	}

	@Override
	public <T extends TaskDescription> void deInit(Task<T> task) {
		Collection<DiagnoseIN> diags = this.patientFile_.getAllDiagnosis();
		for (DiagnoseIN d : diags) {
			if (d.getTreatments().contains(task.getDescription())) {
				((Diagnose) d).removeTreatment(task);
			}
		}
		throw new IllegalArgumentException(
				"Error! PatientFile does not contain the diagnose for the treatment that's being deinitialised.!");
	}

	@Override
	public abstract Collection<Requirement> getAllRequirements();

	@Override
	public Result getResult() {
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends TaskDescription> void initTask(Task<T> task) {
		Collection<DiagnoseIN> diags = this.patientFile_.getAllDiagnosis();
		for (DiagnoseIN d : diags) {
			if (d.getTreatments().contains(task.getDescription())) {
				((Diagnose) d).addTreatment((Task<? extends Treatment>) task);
				return;
			}
		}
		throw new IllegalArgumentException(
				"Error! PatientFile does not contain the diagnose for the treatment created!");
	}

	@Override
	public boolean needsResult() {
		return result == null;
	}

	@Override
	public void setResult(String result) {
		try {
			this.result = new Result(result);
		} catch (InvalidReportException e) {
			throw new Error(e);
		}
	}

}