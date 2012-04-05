package scheduler.requirements;

import scheduler.HospitalDate;
import system.Location;
import warehouse.Warehouse;

public interface Requirement
{
	public int getAmount();
	
	public boolean isMetBy(Requirable requirable);
	
	/**
	 * @return True if the requirement can't be met by advancing the time. 
	 */
	public boolean isCrucial();
	
	public boolean isMetOn(HospitalDate hospitalDate, Location location);
	
	public void collect(Warehouse warehouse);
	
	public boolean backToBack();
	
	public boolean isMarkedForDeletion();
}