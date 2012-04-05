package result;

import exceptions.FactoryInstantiationException;

public class CastResultFactory implements ResultFactory
{
	private String report_;

	@Override
	public Result create() throws FactoryInstantiationException {
		if (!isReady())
			throw new FactoryInstantiationException("The Cast Factory is not ready yet!");
		return new CastResult(this.report_);
	}

	public void setReport(String report) {
		this.report_ = report;
	}

	private boolean isReady() {
		return reportReady();
	}

	private boolean reportReady() {
		return this.report_ != null && !this.report_.isEmpty();
	}
}