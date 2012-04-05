package treatment;

import java.util.Collection;
import java.util.LinkedList;
import patient.Diagnose;
import patient.PatientFile;
import result.CastResult;
import result.CastResultFactory;
import result.Result;
import result.ResultFactory;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import scheduler.requirements.WarehouseItemCondition;
import users.Nurse;
import warehouse.item.PlasterType;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;

/**
 * This class represents a treatment by cast.
 */
public class Cast extends Treatment
{
	private String bodyPart_;
	private int castDuration_;
	/**
	 * The duration of a cast.
	 */
	public final static long DURATION_ = 2 * HospitalDate.ONE_HOUR;

	/**
	 * Default constructor. Package visible since it should only be used by the
	 * factories.
	 * 
	 * @param patientFile
	 *            The patientFile for which this cast is intended.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @param bodyPart
	 *            The bodypart on which the cast needs to be cast onto.
	 * @param castDuration
	 *            The duration of this treatment.
	 * @param diagnose
	 *            The diagnose of this cast.
	 */
	Cast(PatientFile patientFile, HospitalDate creationDate, String bodyPart, int castDuration, Diagnose diagnose) {
		super(patientFile, diagnose, DURATION_, creationDate);
		this.bodyPart_ = bodyPart;
		this.castDuration_ = castDuration;
	}

	/**
	 * @return The duration of the cast.
	 */
	@Basic
	public int getCastDuration() {
		return castDuration_;
	}

	/**
	 * @return The bodypart.
	 */
	@Basic
	public String getBodyPart() {
		return bodyPart_;
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
		requirements.add(new WarehouseItemCondition(new PlasterType(), 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}

	/**
	 * @return A CastResultFactory.
	 */
	@Override
	public ResultFactory get() {

		return new CastResultFactory();
	}

	/**
	 * Gives a result, based on the information of the factory.
	 * 
	 * @param resultFactory
	 *            The factory the result will be based on.
	 * @return The Result based on the ResultFactory.
	 * @throws InvalidResultException
	 *             The given factory is not a CastResultFactory.
	 * @throws FactoryInstantiationException
	 *             The Factory was not ready yet.
	 */
	@Override
	public Result give(ResultFactory resultFactory) throws InvalidResultException, FactoryInstantiationException {
		Result result = resultFactory.create();
		if (!validResult(result))
			throw new InvalidResultException("The given factory is not a CastResultFactory.");
		setResult(result);
		return result;
	}

	/**
	 * Checks whether the given result is valid or not.
	 * 
	 * @param result
	 *            The result that has to be checked.
	 * @return True if the result is not null and is a CastResult.
	 */
	private boolean validResult(Result result) {
		return result != null && result instanceof CastResult;
	}
}