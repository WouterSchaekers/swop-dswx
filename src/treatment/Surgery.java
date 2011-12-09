package treatment;

import exceptions.InvalidDescriptionException;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents a surgical treatment.
 */
public class Surgery extends Treatment
{
	public static final String TREATMENTNAME = "Surgery";
	private String description = "";

	/**
	 * Default constructor.
	 * 
	 * @param description
	 *            The description of this surgery.
	 */
	public Surgery(String description) {
		super(TREATMENTNAME);
	}

	@Basic
	public String getDescription() {
		return description;
	}

	@Basic
	public void setDescription(String description) throws InvalidDescriptionException {
		if(!isValidDescription(description))
			throw new InvalidDescriptionException("Invalid description given in setDescription() of Surgery!");
		this.description = description;
	}

	/**
	 * @return True if b is a valid description.
	 */
	private boolean isValidDescription(String d) {
		return !d.equals("");
	}
		
}
