package result;

/**
 * Class representing a Medication Result.
 */
public class MedicationResult implements Result
{
	/**
	 * Boolean whether the Medication triggered an abnormal reaction.
	 */
	public final boolean abnormalReaction_;
	/**
	 * The report of the Medication.
	 */
	public final String report_;

	/**
	 * Default constructor. Package visible, since it should only be used by the
	 * factory.
	 * 
	 * @param abnormalReaction
	 *            Boolean whether the Medication triggered an abnormal reaction.
	 * @param report
	 *            The report of the Medication.
	 * 
	 */
	MedicationResult(boolean abnormalReaction, String report) {
		this.abnormalReaction_ = abnormalReaction;
		this.report_ = report;
	}
}