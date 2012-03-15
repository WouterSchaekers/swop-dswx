package scheduler;

import java.util.Collection;
import java.util.LinkedList;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;
import patient.PatientFile;
import users.Doctor;

public class AppointmentDescription extends TaskDescription
{
	private Doctor doctor;
	
	public AppointmentDescription(Doctor _doctor, PatientFile _patientFile, long _duration, HospitalDate _creationTime) throws InvalidAmountException, InvalidHospitalDateException {
		super(_patientFile, _duration, 0, true, _creationTime);
		this.doctor = _doctor;
	}
	
	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificResourceRequirement(doctor));
		return requirements;
	}

	public Doctor getDocor() {
		return doctor;
	}

	@Override
	public boolean canBeScheduled() {
		return true;
	}
}