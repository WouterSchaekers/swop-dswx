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
import exceptions.InvalidAmountException;
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

	/**
	 * Default constructor.
	 * 
	 * @param description
	 *            The description of the meds.
	 * @param sensitive
	 *            Whether or not the meds are sensitive.
	 * @throws InvalidHospitalDateException 
	 * @throws InvalidAmountException 
	 */
	public Medication(PatientFile patientFile, HospitalDate creationTime, MedicationType medicationType, Warehouse warehouse, String description, boolean sensitive) throws InvalidHospitalDateException {
		super(patientFile, HospitalDate.ONE_MINUTE * 20, creationTime);
		this.description_ = description;
		this.sensitive_ = sensitive;
		this.medicationType_ = medicationType;
		this.warehouse_ = warehouse;
	}

	/**
	 * @return Information about this medicinal treatment.
	 */
	public String information() {
		return toString() + ": " + this.description_ + " " + sensitiveText()
				+ ".";
	}

	/**
	 * @return A string about whether or not the medication is sensitive.
	 */
	public String sensitiveText() {
		return (this.sensitive_) ? "the medication is sensitive"
				: "the medication is not sensitive";
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

	@Override
	public boolean hasFinished() {
		return false;
	}

	@Override
	public Collection<Requirement> getAllRequirements() {
		Collection<Requirement> requirements = new LinkedList<Requirement>();
		requirements.add(new SpecificRequirement(this.patientFile_.getPatient(),false));
		requirements.add(new WarehouseItemCondition(this.medicationType_, this.warehouse_, 1));
		requirements.add(new RequirementType<Nurse>(Nurse.class, true, 1));
		return requirements;
	}
}