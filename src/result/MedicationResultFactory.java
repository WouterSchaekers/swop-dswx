package result;

import exceptions.FactoryInstantiationException;


public class MedicationResultFactory implements ResultFactory
{
	private boolean abnormalReaction_;
	private boolean abnormalReactionSet_;
	private String report_;

	@Override
	public Result create() throws FactoryInstantiationException {
		if (!isReady())
			throw new FactoryInstantiationException("The Cast Factory is not ready yet!");
		return new MedicationResult(this.abnormalReaction_, this.report_);
	}
	
	public void setAbnormalReaction(boolean abnormalReaction){
		this.abnormalReaction_ = abnormalReaction;
		this.abnormalReactionSet_ = true;
	}
	
	public void setReport(String report) {
		this.report_ = report;
	}

	private boolean isReady() {
		return abnormalReactionReady() && reportReady();
	}
	
	private boolean abnormalReactionReady(){
		return this.abnormalReactionSet_;
	}

	private boolean reportReady() {
		return this.report_ != null && !this.report_.isEmpty();
	}
}