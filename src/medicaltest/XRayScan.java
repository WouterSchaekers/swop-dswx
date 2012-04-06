package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import machine.XRayScanner;
import patient.PatientFile;
import result.Result;
import result.ResultFactory;
import result.XRayScanResult;
import result.XRayScanResultFactory;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;

/**
 * This class represents an xray scan test.
 */
public class XRayScan extends MedicalTest
{
	private final String bodypart_;
	private int num_;
	private float zoomlevel_;
	/**
	 * The duration of an XRayScan.
	 */
	public final static long DURATION_ = 15 * HospitalDate.ONE_MINUTE;

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
		super(patientFile, XRayScan.DURATION_, creationDate);
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
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient()));
		requirements.add(new RequirementType<XRayScanner>(XRayScanner.class, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, 1));
		return requirements;
	}

	/**
	 * @return A XRayScanResultFactory.
	 */
	@Override
	public ResultFactory getResultFactory() {
		return new XRayScanResultFactory();
	}

	/**
	 * Gives a result, based on the information of the factory.
	 * 
	 * @param resultFactory
	 *            The factory the result will be based on.
	 * @return The Result based on the ResultFactory.
	 * @throws InvalidResultException
	 *             The given factory is not a XRayScanFactory.
	 * @throws FactoryInstantiationException
	 *             The Factory was not ready yet.
	 */
	@Override
	public Result give(ResultFactory resultFactory) throws FactoryInstantiationException, InvalidResultException {
		Result result = resultFactory.create();
		if (!validResult(result))
			throw new InvalidResultException("The given factory is not a XRayScanFactory.");
		setResult(result);
		return result;
	}

	/**
	 * Checks whether the given result is valid or not.
	 * 
	 * @param result
	 *            The result that has to be checked.
	 * @return True if the result is not null and is a XRayScanResult.
	 */
	private boolean validResult(Result result) {
		return result != null && result instanceof XRayScanResult;
	}
}