package scheduler2;

import java.util.ArrayList;
import java.util.Collection;
import patient.Patient;
import scheduler.task.Schedulable;
import users.Doctor;

public class AppointmentDescription extends TaskDescription
{

	private Doctor _doc;
	private Patient _patient;

	public AppointmentDescription(Doctor doctor, Patient patient) {
		_doc = doctor;
		_patient = patient;
	}

	@Override
	public Collection<Requirement> getAllRequireMents() {
		ArrayList<Requirement> requireMents = new ArrayList<Requirement>();
		requireMents.add(new SpecificResourceRequirement(_doc));
		return requireMents;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	public Doctor getDocor() {
		return _doc;
	}

	public Patient getPatient() {
		return _patient;
	}

}
