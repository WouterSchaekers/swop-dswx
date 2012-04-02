package scheduler.requirements;

import scheduler.HospitalDate;

public class SpecificRequirement implements Requirement
{
	private Requirable requirable_;
	private boolean backToBack_;

	public SpecificRequirement(Requirable requirable, boolean backToBack) {
		requirable_ = requirable;
		backToBack_ = backToBack;
	}

	@Override
	public boolean isMetBy(Requirable requirable) {
		return requirable_.equals(requirable);
	}
	
	@Override
	public boolean isCrucial(){
		return true;
	}

	@Override
	public boolean isMetOn(HospitalDate hospitalDate) {
		return false;
	}

	@Override
	public void collect() {
	}

	@Override
	public boolean backToBack() {
		return backToBack_;
	}
	
	public int getAmount(){
		return 1;
	}

	@Override
	public boolean isMarkedForDeletion() {
		return false;
	}
}