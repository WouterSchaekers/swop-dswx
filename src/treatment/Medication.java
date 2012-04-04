package treatment;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import scheduler.requirements.RequirementType;
import scheduler.requirements.SpecificRequirement;
import scheduler.requirements.WarehouseItemCondition;
import users.Nurse;
import warehouse.Warehouse;
import warehouse.item.MedicationType;
import controllers.interfaces.MedicationIN;
import exceptions.InvalidHospitalDateException;

/**
 * This class represent treatment by medication.
 */
public class Medication extends Treatment implements MedicationIN
{
	private String description_;
	private boolean sensitive_;
	private MedicationType medicationType_;
	private Warehouse warehouse_;
	public final static long DURATION_ = HospitalDate.ONE_MINUTE * 20;

	/**
	 * Default constructor.
	 * 
	 * @param patientFile
	 *            The patientFile.
	 * @param creationDate
	 *            The date on which this description has been created.
	 * @param medicationType
	 *            The type of the medication.
	 * @param warehouse
	 *            The warehouse where this medication comes from.
	 * @param description
	 *            The description of the medication.
	 * @param sensitive
	 *            Boolean that says whether it is sensitive or not.
	 * @throws InvalidHospitalDateException
	 *             The given creationDate was not valid.
	 */
	public Medication(PatientFile patientFile, HospitalDate creationDate, MedicationType medicationType,
			Warehouse warehouse, String description, boolean sensitive) {
		super(patientFile, DURATION_, creationDate);
		this.description_ = description;
		this.sensitive_ = sensitive;
		this.medicationType_ = medicationType;
		this.warehouse_ = warehouse;
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
	 * Returns all the requirements that are needed to forfill this task.
	 * 
	 * @return All the requirements that are needed to forfill this task.
	 */
	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(), false));
		requirements.add(new WarehouseItemCondition(this.medicationType_, this.warehouse_, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}
}