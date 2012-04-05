package result;

import exceptions.FactoryInstantiationException;

/**
 * Class representing a BloodAnalysis Factory.
 */
public class BloodAnalysisResultFactory implements ResultFactory
{

	private Integer amountOfBlood_;
	private Integer whiteCellCount_;
	private Integer redCellCount_;
	private Integer plateletCount_;

	/**
	 * Creates and returns a Result.
	 * 
	 * @return The result.
	 * @throws FactoryInstantiationException
	 *             The factory was not fully instantiated yet.
	 */
	@Override
	public Result create() throws FactoryInstantiationException {
		if (!isReady())
			throw new FactoryInstantiationException("The BloodAnalysisResultFactory is not ready yet!");
		return new BloodAnalysisResult(this.amountOfBlood_, this.whiteCellCount_, this.redCellCount_,
				this.plateletCount_);
	}

	/**
	 * @return True if the amount of blood, the amount of white and red
	 *         cellcount and the amount of platelet are ready.
	 */
	private boolean isReady() {
		if (!amountOfBloodReady())
			return false;
		if (!amountOfWhiteCellCountReady())
			return false;
		if (!amounOfRedCellCountReady())
			return false;
		if (!amountOfPlateletCountReady())
			return false;
		return true;
	}

	/**
	 * @return True if the amount of platelet is not null and positive.
	 */
	private boolean amountOfPlateletCountReady() {
		return this.amountOfBlood_ != null && this.amountOfBlood_ >= 0;
	}

	/**
	 * @return True if the red cell count is not null and positive.
	 */
	private boolean amounOfRedCellCountReady() {
		return this.whiteCellCount_ != null && this.whiteCellCount_ >= 0;
	}

	/**
	 * @return True if the white cell count is not null and positive.
	 */
	private boolean amountOfWhiteCellCountReady() {
		return this.redCellCount_ != null && this.redCellCount_ >= 0;
	}

	/**
	 * @return True if the amount of blood is not null and positive.
	 */
	private boolean amountOfBloodReady() {
		return this.amountOfBlood_ != null && this.amountOfBlood_ >= 0;
	}

	/**
	 * Sets the amount of blood.
	 * 
	 * @param amountOfBlood
	 *            The amount of blood that has to be set.
	 */
	public void setAmountOfBlood(Integer amountOfBlood) {
		this.amountOfBlood_ = amountOfBlood;
	}

	/**
	 * Sets the white cell count.
	 * 
	 * @param whiteCellCount
	 *            The white cell count that has to be set.
	 */
	public void setWhiteCellCount(Integer whiteCellCount) {
		this.whiteCellCount_ = whiteCellCount;
	}

	/**
	 * Sets the red cell count.
	 * 
	 * @param redCellCount
	 *            The red cell count that has to be set.
	 */
	public void setRedCellCount(Integer redCellCount) {
		this.redCellCount_ = redCellCount;
	}

	/**
	 * Sets the platelet count
	 * 
	 * @param plateletCount
	 *            The palatelet count that has to be set.
	 */
	public void setPlateletCount(Integer plateletCount) {
		this.plateletCount_ = plateletCount;
	}
}