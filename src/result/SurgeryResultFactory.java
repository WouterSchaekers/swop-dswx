package result;

import exceptions.FactoryInstantiationException;

public class SurgeryResultFactory implements ResultFactory
{
	private String report_;
	private String afterCare_;

	@Override
	public Result create() throws FactoryInstantiationException {
		if (!isReady())
			throw new FactoryInstantiationException("The Medication Factory is not ready yet!");
		return new SurgeryResult(this.report_, this.afterCare_);
	}

	public void setReport(String report) {
		this.report_ = report;
	}

	public void setAfterCare(String afterCare) {
		this.afterCare_ = afterCare;
	}

	private boolean isReady() {
		return reportReady() && afterCareReady();
	}

	private boolean reportReady() {
		return this.report_ != null && !this.report_.isEmpty();
	}

	private boolean afterCareReady() {
		return this.afterCare_ != null && !this.afterCare_.isEmpty();
	}
}