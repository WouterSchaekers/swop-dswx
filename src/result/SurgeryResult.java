package result;

import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidReportException;
import exceptions.InvalidSpecialCareException;

/**
 * This class represents the result after a surgery.
 */
public class SurgeryResult extends Result
{
	private String specialCare;

	/**
	 * Default constructor. Will initialise all fields.
	 * 
	 * @see Result
	 * @param specialCare
	 *            What kind of special care the patient who has had surgery
	 *            should receive.
	 * @throws InvalidReportException
	 * @throws InvalidSpecialCareException
	 */
	public SurgeryResult(String report, String specialCare)
			throws InvalidReportException, InvalidSpecialCareException {
		super(report);
		if (!super.canHaveAsDetail(specialCare))
			throw new InvalidSpecialCareException(
					"Invalid special care given to surgery result!");
	}

	@Basic
	public String getSpecialCare() {
		return this.specialCare;
	}

}
