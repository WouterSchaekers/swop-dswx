package treatment;

import patient.PatientFile;
import scheduler.HospitalDate;
import controllers.interfaces.MedicationIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

/**
 * This class represent treatment by medication.
 */
public abstract class Medication extends Treatment implements MedicationIN
{
	private String description_ = "";
	private boolean sensitive_ = false;

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
	public Medication(PatientFile patientFile, HospitalDate creationTime, String description, boolean sensitive) throws InvalidAmountException, InvalidHospitalDateException {
		super(patientFile, HospitalDate.ONE_MINUTE * 20, creationTime);
		setDescription(description);
		setSensitive(sensitive);
	}

	/**
	 * @return Information about this medicinal treatment.
	 */
	public String information() {
		return toString() + ": " + getDescription() + " " + sensitiveText()
				+ ".";
	}

	/**
	 * @return The description of this medicinal treatment.
	 */
	public String getDescription() {
		return description_;
	}

	/**
	 * This method allows to change the description of this treatment.
	 * 
	 * @param description
	 *            The new description for this medicinal treatment.
	 */
	public void setDescription(String description) {
		this.description_ = description;
	}

	/**
	 * @return True if this is a sensitive medication.
	 */
	public boolean isSensitive() {
		return sensitive_;
	}

	/**
	 * @return A string about whether or not the medication is sensitive.
	 */
	public String sensitiveText() {
		return (isSensitive()) ? "the medication is sensitive"
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
}