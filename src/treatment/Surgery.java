package treatment;

import java.util.Collection;
import java.util.LinkedList;
import patient.Diagnose;
import patient.PatientFile;
import result.Result;
import result.ResultFactory;
import result.SurgeryResult;
import result.SurgeryResultFactory;
import scheduler.HospitalDate;
import scheduler.requirements.DiagnoseCondition;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import scheduler.requirements.WarehouseItemCondition;
import users.Nurse;
import warehouse.item.MiscType;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;

/**
 * This class represents a surgical treatment.
 */
public class Surgery extends Treatment
{
	private String description_;
	/**
	 * The duration of a Surgery.
	 */
	public final static long DURATION_ = HospitalDate.ONE_MINUTE * 180;

	/**
	 * Default constructor. Package visible since it should only be used by the
	 * factories.
	 * 
	 * @param patientFile
	 *            The patientFile for which this cast is intended.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @param description
	 *            The description of the surgery.
	 * @param diagnose
	 *            The diagnose of this cast.
	 */
	Surgery(PatientFile patientFile, HospitalDate creationDate, String description, Diagnose diagnose) {
		super(patientFile, diagnose, DURATION_, creationDate);
		this.description_ = description;
	}

	/**
	 * @return The description of the surgery.
	 */
	@Basic
	public String getDescription() {
		return description_;
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
		requirements.add(new WarehouseItemCondition(new MiscType(), 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, 1));
		requirements.add(new DiagnoseCondition(diagnose_));
		return requirements;
	}

	/**
	 * @return A SurgeryResultFactory.
	 */
	@Override
	public ResultFactory getResultFactory() {
		return new SurgeryResultFactory();
	}

	/**
	 * Gives a result, based on the information of the factory.
	 * 
	 * @param resultFactory
	 *            The factory the result will be based on.
	 * @return The Result based on the ResultFactory.
	 * @throws InvalidResultException
	 *             The given factory is not a SurgeryResultFactory.
	 * @throws FactoryInstantiationException
	 *             The Factory was not ready yet.
	 */
	@Override
	public Result give(ResultFactory resultFactory) throws InvalidResultException, FactoryInstantiationException {
		Result result = resultFactory.create();
		if (!validResult(result))
			throw new InvalidResultException("The given factory is not a SurgeryResultFactory.");
		setResult(result);
		return result;
	}

	/**
	 * Checks whether the given result is valid or not.
	 * 
	 * @param result
	 *            The result that has to be checked.
	 * @return True if the result is not null and is a SurgeryResult.
	 */
	private boolean validResult(Result result) {
		return result != null && result instanceof SurgeryResult;
	}
}