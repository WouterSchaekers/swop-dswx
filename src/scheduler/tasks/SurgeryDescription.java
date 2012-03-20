package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import warehouse.item.MiscType;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public class SurgeryDescription extends TreatmentDescription
{
	public SurgeryDescription(PatientFile patientFile, HospitalDate creationTime)
			throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, 3*HospitalDate.ONE_HOUR, creationTime);
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<MiscType>(MiscType.class));
		return requirements;
	}
	
	@Override
	public Requirement getExecutor() {
		return new RequirementType<Nurse>(Nurse.class);
	}
}