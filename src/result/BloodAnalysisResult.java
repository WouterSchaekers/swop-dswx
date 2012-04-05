package result;

/**
 * Class representing a BloodAnalysis Result.
 */
public class BloodAnalysisResult implements Result
{
	/**
	 * The amount of blood withdrawn.
	 */
	public final int amountOfBlood_;
	/**
	 * The amount of white cells.
	 */
	public final int whiteCellCount_;
	/**
	 * The amount of red cells.
	 */
	public final int redCellcount_;
	/**
	 * The amount of platelets.
	 */
	public final int plateletCount_;

	/**
	 * Default constructor. Package visible, since it should only be used by the
	 * factory.
	 * 
	 * @param amountOfBlood
	 *            The amount of blood withdrawn.
	 * @param whiteCellCount
	 *            The amount of white cells.
	 * @param redCellCount
	 *            The amount of red cells.
	 * @param plateletCount
	 *            The amount of platelets.
	 */
	BloodAnalysisResult(int amountOfBlood, int whiteCellCount, int redCellCount, int plateletCount) {
		this.amountOfBlood_ = amountOfBlood;
		this.whiteCellCount_ = whiteCellCount;
		this.redCellcount_ = redCellCount;
		this.plateletCount_ = plateletCount;
	}
}