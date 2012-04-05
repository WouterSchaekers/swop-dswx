package result;

import exceptions.FactoryInstantiationException;

public class BloodAnalysisResultFactory implements ResultFactory
{


	private Integer amountOfBlood;
	private Integer whiteCellCount;
	private Integer redCellCount;
	private Integer plateletCount;

	@Override
	public Result create() throws FactoryInstantiationException {
		if(!isReady())
			throw new FactoryInstantiationException("Bloodanalysis ");
		return new BloodAnalysisResult(amountOfBlood, whiteCellCount, redCellCount, plateletCount);
	}

	private boolean isReady() {
		if(!amountOfBloodReady())
			return false;
		if(!amountOfwhiteCellCountReady())
			return false;
		if(!amounOfRedCellCountReady())
			return false;
		if(!amountOfplateletCountReady())
			return false;
		return true;
	}

	private boolean amountOfplateletCountReady() {
		return amountOfBlood!=null&&amountOfBlood>=0;
	}

	private boolean amounOfRedCellCountReady() {
		return whiteCellCount!=null&&whiteCellCount>=0;
	}

	private boolean amountOfwhiteCellCountReady() {
		return redCellCount!=null&&redCellCount>=0;
	}

	private boolean amountOfBloodReady() {
		return amountOfBlood!=null&&amountOfBlood>=0;
	}

	public void setAmountOfBlood(Integer amountOfBlood) {
		this.amountOfBlood = amountOfBlood;
	}

	public void setWhiteCellCount(Integer whiteCellCount) {
		this.whiteCellCount = whiteCellCount;
	}

	public void setRedCellCount(Integer redCellCount) {
		this.redCellCount = redCellCount;
	}

	public void setPlateletCount(Integer plateletCount) {
		this.plateletCount = plateletCount;
	}
}
