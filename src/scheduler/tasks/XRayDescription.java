package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import machine.XRayScanner;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.ResourceRequirement;
import scheduler.requirements.SpecificResourceRequirement;
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
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificResourceRequirement(this.patientFile_.getPatient()));
		requirements.add(new ResourceRequirement<Nurse>(Nurse.class));
		requirements.add(new ResourceRequirement<XRayScanner>(XRayScanner.class));
		return requirements;
	}
}