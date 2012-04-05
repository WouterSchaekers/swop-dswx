package result;

import exceptions.FactoryInstantiationException;

/**
 * Class representing a MedicationResult Factory.
 */
public class MedicationResultFactory implements ResultFactory
{
	private boolean abnormalReaction_;
	private boolean abnormalReactionSet_;
	private String report_;

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
			throw new FactoryInstantiationException("The MedicationResultFactory is not ready yet!");
		return new MedicationResult(this.abnormalReaction_, this.report_);
	}

	/**
	 * Sets the reaction of the Medication.
	 * 
	 * @param abnormalReaction
	 *            The reaction of the Medication.
	 */
	public void setAbnormalReaction(boolean abnormalReaction) {
		this.abnormalReaction_ = abnormalReaction;
		this.abnormalReactionSet_ = true;
	}

	/**
	 * Sets the report of the Medication.
	 * 
	 * @param report
	 *            The report of the Medication.
	 */
	public void setReport(String report) {
		this.report_ = report;
	}

	/**
	 * @return True if the abnormality and report are ready.
	 */
	private boolean isReady() {
		return abnormalReactionReady() && reportReady();
	}

	/**
	 * @return True if the abnormality is set.
	 */
	private boolean abnormalReactionReady() {
		return this.abnormalReactionSet_;
	}

	/**
	 * @return True if the report is not null and not empty.
	 */
	private boolean reportReady() {
		return this.report_ != null && !this.report_.isEmpty();
	}
}