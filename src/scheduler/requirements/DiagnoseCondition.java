package scheduler.requirements;

import patient.Diagnose;
import scheduler.HospitalDate;

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
	public boolean isMetOn(HospitalDate hospitalDate) {
		return this.diagnose_.isApproved();
	}

	@Override
	public void collect() {
	}
	
	@Override
	public boolean backToBack() {
		return false;
	}
	
	public int getAmount(){
		return 1;
	}
}