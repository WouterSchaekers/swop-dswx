package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import machine.XRayScanner;
import patient.PatientFile;
import result.Result;
import result.ResultFactory;
import result.XRayScanResultBuilder;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;

/**
 * This class represents an xray scan test.
 */
public class XRayScan extends MedicalTest
{
	private final String bodypart_;
	private int num_;
	private float zoomlevel_;

	/**
	 * The default constructor. Package visible since it should only be used by
	 * the factories.
	 * 
	 * @param patientFile
	 *            The patientFile for which this cast is intended.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @param bodyPart
	 *            The bodypart of which the xray scan has to be taken from.
	 * @param num
	 *            The amount of images needed.
	 * @param zoomlevel
	 *            The level of zoom.
	 */
	XRayScan(PatientFile patientFile, HospitalDate creationDate, String bodypart, int num, float zoomlevel) {
		super(patientFile, 15 * HospitalDate.ONE_MINUTE, creationDate);
		this.bodypart_ = bodypart;
		this.num_ = num;
		this.zoomlevel_ = zoomlevel;
	}

	/**
	 * Returns the amount of images needed.
	 * 
	 * @return The amount of images needed.
	 */
	public int getNum() {
		return num_;
	}

	/**
	 * Returns the level of zoom.
	 * 
	 * @return The level of zoom.
	 */
	public float getZoomlevel() {
		return zoomlevel_;
	}

	/**
	 * Returns the bodypart of which the xray scan has to be taken from.
	 * 
	 * @return The bodypart of which the xray scan has to be taken from.
	 */
	public String getBodypart() {
		return bodypart_;
	}

	/**
	 * Returns all the requirements that are needed to forfill this task.
	 * 
	 * @return All the requirements that are needed to forfill this task.
	 */
	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(), false));
		requirements.add(new RequirementType<XRayScanner>(XRayScanner.class, false, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}

	@Override
	public ResultFactory get() {
		return new XRayScanResultBuilder();
	}

	@Override
	public Result give(ResultFactory builder) {
		// TODO Auto-generated method stub
		return null;
	}
}