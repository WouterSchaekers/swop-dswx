package treatment;

import scheduler.HospitalDate;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.SurgeryIN;
import exceptions.InvalidDescriptionException;

/**
 * This class represents a surgical treatment.
 */
public class Surgery extends Treatment implements SurgeryIN
{
	private String description = "";

	/**
	 * Default constructor.
	 * 
	 * @param description
	 *            The description of this surgery.
	 * @throws InvalidDescriptionException
	 */
	public Surgery(String description) throws InvalidDescriptionException {
		super(HospitalDate.ONE_MINUTE * 180);
		setDescription(description);
	}

	@Basic
	public String getDescription() {
		return description;
	}

	@Basic
	public void setDescription(String description)
			throws InvalidDescriptionException {
		if (!isValidDescription(description))
			throw new InvalidDescriptionException(
					"Invalid description given in setDescription() of Surgery!");
		this.description = description;
	}

	/**
	 * @return True if b is a valid description.
	 */
	private boolean isValidDescription(String d) {
		return !d.equals("");
	}

	@Override
	public boolean hasFinished() {
		return false;
	}

}
