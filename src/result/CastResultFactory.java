package result;

import exceptions.FactoryInstantiationException;

/**
 * Class representing a BloodAnalysis Factory.
 */
@result.ResutlsAPI
public class CastResultFactory implements ResultFactory
{
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
			throw new FactoryInstantiationException("The CastResultFactory is not ready yet!");
		return new CastResult(this.report_);
	}

	/**
	 * Sets the report of the CastResult.
	 * 
	 * @param report
	 *            The report of the CastResult.
	 */
	@result.ResutlsAPI
	public void setReport(String report) {
		this.report_ = report;
	}

	/**
	 * @return True if the report is ready.
	 */
	private boolean isReady() {
		return reportReady();
	}

	/**
	 * @return True if the report is not null and not empty.
	 */
	private boolean reportReady() {
		return this.report_ != null && !this.report_.isEmpty();
	}
}