package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import machine.XRayScanner;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public class XRayScan extends MedicalTest
{
	private final String bodypart_;
	private int num_;
	private float zoomlevel_;

	XRayScan(PatientFile patientFile, HospitalDate creationTime, String bodypart, int num, float zoomlevel)
			throws InvalidNameException, InvalidDurationException,
			InvalidTimeSlotException, InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, 15 * HospitalDate.ONE_MINUTE, creationTime);
		this.bodypart_ = bodypart;
		this.num_ = num;
		this.zoomlevel_ = zoomlevel;
	}
	
	public int getNum() {
		return num_;
	}

	public void setNum(int num) {
		this.num_ = num;
	}

	public float getZoomlevel() {
		return zoomlevel_;
	}

	public void setZoomlevel(float zoomlevel) {
		this.zoomlevel_ = zoomlevel;
	}

	public String getBodypart() {
		return bodypart_;
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<XRayScanner>(XRayScanner.class));
		return requirements;
	}
}