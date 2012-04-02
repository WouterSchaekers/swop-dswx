package scheduler.requirements;

import scheduler.HospitalDate;

public interface Requirement
{
	public int getAmount();
	
	public boolean isMetBy(Requirable requirable);
	
	public boolean isCrucial();
	
	public boolean isMetOn(HospitalDate hospitalDate);
	
	public void collect();
	
	public boolean backToBack();
	
	public boolean isMarkedForDeletion();
}