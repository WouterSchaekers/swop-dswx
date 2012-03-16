package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.ResourceRequirement;
import warehouse.item.PlasterType;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public class CastDescription extends MedicationDescription
{

	public CastDescription(PatientFile patientFile, long duration,
			long extraTime, HospitalDate creationTime)
			throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, duration, extraTime, creationTime);
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new ResourceRequirement<PlasterType>(PlasterType.class));
		return requirements;
	}
}