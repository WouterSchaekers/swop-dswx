package treatment;

import scheduler.HospitalDate;
import controllers.interfaces.MedicationIN;

/**
 * This class represent treatment by medication.
 */
public abstract class Medication extends Treatment implements MedicationIN
{
	private String description = "";
	private boolean sensitive = false;

	/**
	 * Default constructor.
	 * 
	 * @param description
	 *            The description of the meds.
	 * @param sensitive
	 *            Whether or not the meds are sensitive.
	 */
	public Medication(String description, boolean sensitive) {
		super(HospitalDate.ONE_MINUTE * 20);
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
		return description;
	}

	/**
	 * This method allows to change the description of this treatment.
	 * 
	 * @param description
	 *            The new description for this medicinal treatment.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return True if this is a sensitive medication.
	 */
	public boolean isSensitive() {
		return sensitive;
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
		this.sensitive = sensitive;
	}

	@Override
	public boolean hasFinished() {
		return this.result != null;
	}
}
