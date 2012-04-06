package medicaltest;

import java.util.Collection;
import java.util.LinkedList;
import machine.UltraSoundScanner;
import patient.PatientFile;
import result.Result;
import result.ResultFactory;
import result.UltraSoundScanResult;
import result.UltraSoundScanResultFactory;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import users.Nurse;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;

/**
 * This class represents an ultrasound scan test.
 */
public class UltraSoundScan extends MedicalTest
{
	private final String focus_;
	private final boolean recordVid_;
	private final boolean recordImages_;
	/**
	 * The duration of an UltraSoundScan.
	 */
	public final static long DURATION_ = 30 * HospitalDate.ONE_MINUTE;

	/**
	 * The default constructor. Package visible since it should only be used by
	 * the factories.
	 * 
	 * @param patientFile
	 *            The patientFile for which this cast is intended.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @param focus
	 *            The focus of this ultrasound scan.
	 * @param recordVid
	 *            The record video of this ultrasound scan.
	 * @param recordImages
	 *            The record images of this ultrasound scan.
	 */
	UltraSoundScan(PatientFile patientFile, HospitalDate creationDate, String focus, boolean recordVid,
			boolean recordImages) {
		super(patientFile, UltraSoundScan.DURATION_, creationDate);
		this.focus_ = focus;
		this.recordVid_ = recordVid;
		this.recordImages_ = recordImages;
	}

	/**
	 * Returns the focus of this ultrasound scan.
	 * 
	 * @return The focus of this ultrasound scan.
	 */
	public String getFocus() {
		return focus_;
	}

	/**
	 * Returns whether the ultrasound scan has a record video.
	 * 
	 * @return True if the ultrasound scan has a record video.
	 */
	public boolean hasVideoRecordingEnabled() {
		return recordVid_;
	}

	/**
	 * Returns whether the ultrasound scan has a record images.
	 * 
	 * @return True if the ultrasound scan has a record images.
	 */
	public boolean hasImageRecordingEnabled() {
		return recordImages_;
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
		requirements.add(new RequirementType<UltraSoundScanner>(UltraSoundScanner.class, false, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}

	/**
	 * @return An UltraSoundScanFactory.
	 */
	@Override
	public ResultFactory get() {
		return new UltraSoundScanResultFactory();
	}

	/**
	 * Gives a result, based on the information of the factory.
	 * 
	 * @param resultFactory
	 *            The factory the result will be based on.
	 * @return The Result based on the ResultFactory.
	 * @throws InvalidResultException
	 *             The given factory is not a UltraSoundScanFactory.
	 * @throws FactoryInstantiationException
	 *             The Factory was not ready yet.
	 */
	@Override
	public Result give(ResultFactory resultFactory) throws InvalidResultException, FactoryInstantiationException {
		Result result = resultFactory.create();
		if (!validResult(result))
			throw new InvalidResultException("The given factory is not a UltraSoundScanFactory.");
		setResult(result);
		return result;
	}

	/**
	 * Checks whether the given result is valid or not.
	 * 
	 * @param result
	 *            The result that has to be checked.
	 * @return True if the result is not null and is a UltraSoundScanResult.
	 */
	private boolean validResult(Result result) {
		return result != null && result instanceof UltraSoundScanResult;
	}
}