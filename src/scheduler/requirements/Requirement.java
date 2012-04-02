package scheduler.requirements;

import scheduler.HospitalDate;

public interface Requirement
{
	public int getAmount();
	
	public boolean isMetBy(Requirable requirable);
	
	/**
	 * @return True if the requirement can't be met by advancing the time. 
	 */
	public boolean isCrucial();
	
	public boolean isMetOn(HospitalDate hospitalDate);
	
	public void collect();
	
	public boolean backToBack();
	
	public boolean isMarkedForDeletion();
}