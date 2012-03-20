package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import machine.XRayScanner;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public class XRayDescription extends MedicalTestDescription
{

	public XRayDescription(PatientFile patientFile, HospitalDate creationTime) throws InvalidAmountException,
			InvalidHospitalDateException {
		super(patientFile, 15*HospitalDate.ONE_MINUTE, creationTime);
	}

	@Override
	public Collection<Requirement> getAllOtherRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<XRayScanner>(XRayScanner.class));
		return requirements;
	}
	
	@Override
	public Requirement getExecutor() {
		return new RequirementType<Nurse>(Nurse.class);
	}
}