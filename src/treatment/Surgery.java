package treatment;

import java.util.Collection;
import java.util.LinkedList;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidResultException;
import patient.Diagnose;
import patient.PatientFile;
import result.Result;
import result.ResultFactory;
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

/**
 * This class represents a surgical treatment.
 */
public class Surgery extends Treatment
{
	private String description_;
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
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(), false));
		requirements.add(new WarehouseItemCondition(new MiscType(), 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		requirements.add(new DiagnoseCondition(diagnose_));
		return requirements;
	}

	@Override
	public ResultFactory get() {
		return new SurgeryResultFactory();
	}

	@Override
	public Result give(ResultFactory builder) throws InvalidResultException, FactoryInstantiationException {
		Result myresult = builder.create();
		if(!validResult(myresult))
			throw new InvalidResultException("invalid result");
		setResult(myresult);
		return myresult;
	}

	private boolean validResult(Result myresult) {
		// TODO Auto-generated method stub
		return false;
	}
}