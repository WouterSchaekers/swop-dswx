package result;

/**
 * Class representing a Cast Result.
 */
public class CastResult implements Result
{
	/**
	 * The report of the CastResult.
	 */
	public final String report_;

	/**
	 * Default constructor. Package visible, since it should only be used by the
	 * factory.
	 * 
	 * @param report
	 *            The report of the CastResult.
	 */
	CastResult(String report) {
		this.report_ = report;
	}
}