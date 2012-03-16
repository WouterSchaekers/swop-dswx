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
	
	public AppointmentDescription(Doctor doctor, PatientFile patientFile, HospitalDate creationTime) throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, 30*HospitalDate.ONE_MINUTE, HospitalDate.ONE_HOUR, creationTime);
		this.doctor = doctor;
	}
	
	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificSchedulableResourceRequirement(doctor));
		return requirements;
	}

	public Doctor getDocor() {
		return doctor;
	}
}