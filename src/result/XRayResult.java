package result;

import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidReportException;

/**
 * This class represent the result of an XRayScan.
 */
public class XRayResult extends Result
{

	private int amountOfImages;

	/**
	 * Default constructor. Will initialise all fields.
	 * 
	 * @param report
	 *            The report of the XRay scan.
	 * @param amountOfImages
	 *            The amount of images that have been taken.
	 * @throws InvalidReportException
	 * @throws InvalidAmountException
	 */
	public XRayResult(String report, int amountOfImages)
			throws InvalidReportException, InvalidAmountException {
		super(report);
		if (super.canhaveAsAmount(amountOfImages))
			this.amountOfImages = amountOfImages;
		else
			throw new InvalidAmountException(
					"Invalid amount given to XRayResult!");
	}

	@Basic
	public int getAmountOfImages() {
		return this.amountOfImages;
	}

}
