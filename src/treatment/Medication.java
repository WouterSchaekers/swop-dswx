package treatment;

import result.Result;
import scheduler.HospitalDate;
import warehouse.Expirable;
import warehouse.MedicationType;
import warehouse.WarehouseItem;

/**
 * This class represent treatment by medication.
 */
public abstract class Medication extends Treatment implements Expirable,WarehouseItem
{
	// the name of each treatment will be final and will be used later on to
	// determine which treatment a Treatment is.
	private String description = ""; // the description of the meds
	private boolean sensitive = false; // whether or not if the medication is
										// sensitive
	public final HospitalDate expiryDate;
	public final MedicationType medicationType;

	/**
	 * Default constructor.
	 * 
	 * @param description
	 *            The description of the meds.
	 * @param sensitive
	 *            Whether or not the meds are sensitive.
	 */
	public Medication(String description, boolean sensitive, HospitalDate expiryDate, MedicationType medicationType) {
		super(HospitalDate.ONE_MINUTE*20);
		this.expiryDate = expiryDate;
		this.medicationType = medicationType;
		setDescription(description);
		setSensitive(sensitive);
	}

	/**
	 * @return Information about this medicinal treatment.
	 */
	public String information() {
		return toString()+ ": " + getDescription() + " " + sensitiveText()
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
	
	public HospitalDate getExpiryDate(){
		return this.expiryDate;
	}
	
	public boolean hasPassedDate(HospitalDate date){
		if(this.expiryDate.before(date)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setResult(Result r) {
		//TODO implement 
	}
}
