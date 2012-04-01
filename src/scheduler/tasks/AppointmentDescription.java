package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Doctor;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public class AppointmentDescription extends TaskDescriptionWithPatientFile
{
	private Doctor doctor;

	public AppointmentDescription(Doctor doctor, PatientFile patientFile, HospitalDate creationTime)
			throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, 30 * HospitalDate.ONE_MINUTE, HospitalDate.ONE_HOUR, creationTime);
		this.doctor = doctor;
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(), false));
		requirements.add(new RequirementType<Doctor>(Doctor.class, true, 1));
		return requirements;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	@Override
	public <T extends TaskDescription> void initTask(Task<T> task) {
		; // do nothing
	}

	@Override
	public <T extends TaskDescription> void deInit(Task<T> task) {
		; // do nothing
	}

	@Override
	public boolean needsResult() {
		return false;
	}

	@Override
	public void setResult(String result) {
		; // do nothing
	}

	@Override
	public Result getResult() {
		return null;
	}
}