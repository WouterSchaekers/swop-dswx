package result;

public class BloodAnalysisResult implements Result
{
	public final int amountOfBlood_;
	public final int whiteCellCount_;
	public final int redCellcount_;
	public final int plateletCount_;

	BloodAnalysisResult(int amountOfBlood, int whiteCellCount, int redCellCount, int plateletCount) {
		this.amountOfBlood_ = amountOfBlood;
		this.whiteCellCount_ = whiteCellCount;
		this.redCellcount_ = redCellCount;
		this.plateletCount_ = plateletCount;
	}
}