package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import warehouse.item.PlasterType;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public class CastDescription extends TreatmentDescription
{
	public CastDescription(PatientFile patientFile, long duration,
			long extraTime, HospitalDate creationTime)
			throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, duration, creationTime);
	}

	@Override
	public Collection<Requirement> getAllOtherRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<Nurse>(Nurse.class));
		requirements.add(new RequirementType<PlasterType>(PlasterType.class));
		return requirements;
	}
}