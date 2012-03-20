package scheduler.requirements;

import patient.Diagnose;

public class DiagnoseCondition implements Requirement
{
	private Diagnose diagnose_;
	public DiagnoseCondition(Diagnose diagnose){
		this.diagnose_ = diagnose;
	}
	@Override
	public boolean isMetBy(Requirable requirable) {
		return false;
	}

	@Override
	public boolean isMet() {
		return this.diagnose_.isApproved();
	}

	@Override
	public void collect() {
	}
}