package scheduler.requirements;

import scheduler.HospitalDate;

public interface Requirement
{
	public int getAmount();
	
	public boolean isMetBy(Requirable requirable);
	
	public boolean isMetOn(HospitalDate hospitalDate);
	
	public void collect();
	
	public boolean backToBack();
}