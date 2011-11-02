package medicaltest;

/**
 * This class represents a surgical treatment.
 */
public class Surgery extends Treatment
{
	// the name of each treatment will be final and will be used later on to determine which treatment a Treatment is.
	public static final String TREATMENTNAME = "Surgery";
	private String description; // a description of the surgery

	/**
	 * Default constructor.
	 * @param description
	 * The description of this surgery.
	 */
	public Surgery(String description){
		super(TREATMENTNAME, 180);
	}

	/**
	 * @return The description of this surgery.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method can change the description of this surgery.
	 * @param description
	 * The new description of this surgery.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
