package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.SpecificResourceRequirement;
import users.Doctor;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

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
		requirements.add(new SpecificResourceRequirement(doctor));
		return requirements;
	}

	public Doctor getDocor() {
		return doctor;
	}
}