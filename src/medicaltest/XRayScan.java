package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import machine.XRayScanner;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class XRayScan extends MedicalTest
{
	public final static long DURATION = 15 * HospitalDate.ONE_MINUTE;
	private final String bodypart;
	private int num;
	private float zoomlevel;

	XRayScan(PatientFile patientFile, HospitalDate creationTime, String bodypart, int num, float zoomlevel)
			throws InvalidNameException, InvalidDurationException,
			InvalidTimeSlotException, InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, DURATION, creationTime);
		this.bodypart = bodypart;
		this.num = num;
		this.zoomlevel = zoomlevel;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getZoomlevel() {
		return zoomlevel;
	}

	public void setZoomlevel(float zoomlevel) {
		this.zoomlevel = zoomlevel;
	}

	public String getBodypart() {
		return bodypart;
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<XRayScanner>(XRayScanner.class));
		return requirements;
	}

	@Override
	public Collection<Requirement> getExecutors() {
		Collection<Requirement> executors = new LinkedList<Requirement>();
		executors.add(new RequirementType<Nurse>(Nurse.class));
		return executors;
	}
}