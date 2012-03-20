package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import machine.UltraSoundScanner;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public class UltraSoundDescription extends MedicalTestDescription
{
	public UltraSoundDescription(PatientFile patientFile, HospitalDate creationTime) throws InvalidAmountException,
			InvalidHospitalDateException {
		super(patientFile, 30*HospitalDate.ONE_MINUTE, creationTime);
	}

	@Override
	public Collection<Requirement> getAllOtherRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<Nurse>(Nurse.class));
		requirements.add(new RequirementType<UltraSoundScanner>(UltraSoundScanner.class));
		return requirements;
	}
}