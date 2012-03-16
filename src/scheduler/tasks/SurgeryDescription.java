package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.ResourceRequirement;
import scheduler.requirements.SpecificResourceRequirement;
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
		requirements.add(new SpecificResourceRequirement(this.patientFile_.getPatient()));
		requirements.add(new ResourceRequirement<MiscType>(MiscType.class));
		requirements.add(new ResourceRequirement<Nurse>(Nurse.class));
		return requirements;
	}
}