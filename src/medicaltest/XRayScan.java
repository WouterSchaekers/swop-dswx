package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import machine.XRayScanner;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public class XRayScan extends MedicalTest
{
	private final String bodypart_;
	private int num_;
	private float zoomlevel_;

	XRayScan(PatientFile patientFile, HospitalDate creationTime, String bodypart, int num, float zoomlevel) throws InvalidAmountException, InvalidHospitalDateException {
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
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(), false));
		requirements.add(new RequirementType<XRayScanner>(XRayScanner.class, false, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}

	@Override
	public void initTask() {
		// TODO Auto-generated method stub
		
	}
}