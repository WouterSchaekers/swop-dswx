package users;

import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import system.Location;

abstract class PreferenceState
{
	public abstract Location getLocationAt(HospitalDate date);

	public abstract LinkedList<LocationTimeSlot> getSlots();
	
	public abstract void scheduleTaskAt(LocationTimeSlot slot);

	public abstract LinkedList<Location> getCurrentPreference();
}
