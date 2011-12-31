package treatment;

import controllers.interfaces.SurgeryIN;
import result.Result;
import scheduler.HospitalDate;
import exceptions.InvalidDescriptionException;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents a surgical treatment.
 */
public class Surgery extends Treatment implements SurgeryIN
{
	public static final String TREATMENTNAME = "Surgery";
	private String description = "";

	/**
	 * Default constructor.
	 * 
	 * @param description
	 *            The description of this surgery.
	 * @throws InvalidDescriptionException 
	 */
	public Surgery(String description) throws InvalidDescriptionException {
		super(HospitalDate.ONE_MINUTE*180);
		setDescription(description);
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

	@Override
	public void setResult(Result r) {
		
	}

	@Override
	public boolean hasFinished() {
		return false;
	}
		
}
