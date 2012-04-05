package result;

/**
 * Class representing a XRayScan Result.
 */
public class XRayScanResult implements Result
{

	/**
	 * The abnormalities that were found.
	 */
	public final String abnormalities_;
	/**
	 * The amount of images taken.
	 */
	public final Integer numberOfImages_;

	/**
	 * Default constructor. Package visible, since it should only be used by the
	 * factory.
	 * 
	 * @param abnomalities
	 *            The abnormalities that were found.
	 * @param numberOfImages
	 *            The amount of images taken.
	 */
	XRayScanResult(String abnormalities, Integer numberOfImages) {
		this.abnormalities_ = abnormalities;
		this.numberOfImages_ = numberOfImages;
	}
}