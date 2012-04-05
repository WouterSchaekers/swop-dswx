package treatment;

import java.util.Collection;
import java.util.LinkedList;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import scheduler.requirements.WarehouseItemCondition;
import users.Nurse;
import warehouse.item.MedicationType;

/**
 * This class represent treatment by medication.
 */
public class Medication extends Treatment
{
	private String description_;
	private boolean sensitive_;
	private MedicationType medicationType_;
	public final static long SENSITIVE_DURATION_ = HospitalDate.ONE_MINUTE * 20;
	public final static long NON_SENSITIVE_DURATION_ = HospitalDate.ONE_MINUTE * 10;

	/**
	 * Default constructor. Package visible since it should only be used by the
	 * factories.
	 * 
	 * @param patientFile
	 *            The patientFile.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @param medicationType
	 *            The type of the medication.
	 * @param description
	 *            The description of the medication.
	 * @param sensitive
	 *            Boolean that says whether it is sensitive or not.
	 * @param diagnose
	 *            The diagnose of this cast.
	 */
	Medication(PatientFile patientFile, HospitalDate creationDate, MedicationType medicationType, String description,
			boolean sensitive, Diagnose diagnose) {
		super(patientFile, diagnose, getDuration(sensitive), creationDate);
		this.description_ = description;
		this.sensitive_ = sensitive;
		this.medicationType_ = medicationType;
	}

	/**
	 * @return Information about this medication.
	 */
	public String information() {
		return toString() + ": " + this.description_ + " " + sensitiveText() + ".";
	}

	/**
	 * @return A string about whether or not the medication is sensitive.
	 */
	public String sensitiveText() {
		return (this.sensitive_) ? "the medication is sensitive" : "the medication is not sensitive";
	}

	/**
	 * This method allows to change if the medication is sensitive or not.
	 * 
	 * @param sensitive
	 *            The new state of sensitivity.
	 */
	public void setSensitive(boolean sensitive) {
		this.sensitive_ = sensitive;
	}

	/**
	 * Returns the duration of the medication based on the sensitivity.
	 * 
	 * @param sensitive
	 *            Boolean whether it is sensitive or not.
	 * @return The duration of the medication based on the sensitivity.
	 */
	private static long getDuration(boolean sensitive) {
		if (sensitive)
			return Medication.SENSITIVE_DURATION_;
		else
			return Medication.NON_SENSITIVE_DURATION_;
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
		requirements.add(new WarehouseItemCondition(this.medicationType_, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}
}