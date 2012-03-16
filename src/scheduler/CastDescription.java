package scheduler;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import treatment.Cast;
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
		requirements.add(new ResourceRequirement<Cast>(Cast.class));
		return requirements;
	}
}