package result;

import exceptions.FactoryInstantiationException;

/**
 * Class representing a SurgeryResult Factory.
 */
@result.ResutlsAPI
public class SurgeryResultFactory implements ResultFactory
{
	private String report_;
	private String afterCare_;

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
			throw new FactoryInstantiationException("The SurgeryResultFactory is not ready yet!");
		return new SurgeryResult(this.report_, this.afterCare_);
	}

	/**
	 * Sets the report of the SurgeryResult.
	 * 
	 * @param report
	 *            The report of the SurgeryResult.
	 */
	@result.ResutlsAPI
	public void setReport(String report) {
		this.report_ = report;
	}

	/**
	 * Sets the information about the afterCare.
	 * 
	 * @param afterCare
	 *            The information about the afterCare.
	 */
	@result.ResutlsAPI
	public void setAfterCare(String afterCare) {
		this.afterCare_ = afterCare;
	}

	/**
	 * @return True if the report and the afterCare are ready.
	 */
	private boolean isReady() {
		return reportReady() && afterCareReady();
	}

	/**
	 * @return True if the report is not null and not empty.
	 */
	private boolean reportReady() {
		return this.report_ != null && !this.report_.isEmpty();
	}

	/**
	 * @return True if the afterCare is not null and not empty.
	 */
	private boolean afterCareReady() {
		return this.afterCare_ != null && !this.afterCare_.isEmpty();
	}
}