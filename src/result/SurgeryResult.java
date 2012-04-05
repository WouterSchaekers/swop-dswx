package result;

/**
 * Class representing a Surgery Result.
 */
public class SurgeryResult implements Result
{
	/**
	 * The report of the SurgeryResult.
	 */
	public final String report_;
	/**
	 * Information about the aftercare.
	 */
	public final String afterCare_;

	/**
	 * Default constructor. Package visible, since it should only be used by the
	 * factory.
	 * 
	 * @param report
	 *            The report of the SurgeryResult.
	 * @param afterCare
	 *            Information about the aftercare.
	 */
	SurgeryResult(String report, String afterCare) {
		this.report_ = report;
		this.afterCare_ = afterCare;
	}
}