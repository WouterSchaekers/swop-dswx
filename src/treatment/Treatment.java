package treatment;

import java.util.Collection;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.tasks.TaskDescriptionWithPatientFile;
import controllers.interfaces.TreatmentIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidReportException;

/**
 * This class is the superclass of all treatments.
 */
public abstract class Treatment extends TaskDescriptionWithPatientFile implements TreatmentIN
{

	public Treatment(PatientFile patientFile, long duration, HospitalDate creationTime) throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, duration, 0, creationTime);
	}
	
	@Override
	public abstract Collection<Requirement> getAllRequirements();
	

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

	@Override
	public Result getResult() {
		return result;
	}

}