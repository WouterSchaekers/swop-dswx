package result;

import exceptions.FactoryInstantiationException;

public class BloodAnalysisResultFactory implements ResultFactory
{

	private Integer amountOfBlood_;
	private Integer whiteCellCount_;
	private Integer redCellCount_;
	private Integer plateletCount_;

	@Override
	public Result create() throws FactoryInstantiationException {
		if (!isReady())
			throw new FactoryInstantiationException("The BloodAnalysis Factory is not ready yet!");
		return new BloodAnalysisResult(this.amountOfBlood_, this.whiteCellCount_, this.redCellCount_,
				this.plateletCount_);
	}

	private boolean isReady() {
		if (!amountOfBloodReady())
			return false;
		if (!amountOfwhiteCellCountReady())
			return false;
		if (!amounOfRedCellCountReady())
			return false;
		if (!amountOfPlateletCountReady())
			return false;
		return true;
	}

	private boolean amountOfPlateletCountReady() {
		return this.amountOfBlood_ != null && this.amountOfBlood_ >= 0;
	}

	private boolean amounOfRedCellCountReady() {
		return this.whiteCellCount_ != null && this.whiteCellCount_ >= 0;
	}

	private boolean amountOfwhiteCellCountReady() {
		return this.redCellCount_ != null && this.redCellCount_ >= 0;
	}

	private boolean amountOfBloodReady() {
		return this.amountOfBlood_ != null && this.amountOfBlood_ >= 0;
	}

	public void setAmountOfBlood(Integer amountOfBlood) {
		this.amountOfBlood_ = amountOfBlood;
	}

	public void setWhiteCellCount(Integer whiteCellCount) {
		this.whiteCellCount_ = whiteCellCount;
	}

	public void setRedCellCount(Integer redCellCount) {
		this.redCellCount_ = redCellCount;
	}

	public void setPlateletCount(Integer plateletCount) {
		this.plateletCount_ = plateletCount;
	}
}