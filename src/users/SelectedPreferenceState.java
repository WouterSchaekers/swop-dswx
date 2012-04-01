package users;

import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import scheduler.LocationTimeTablePreference;
import system.Location;

class SelectedPreferenceState extends PreferenceState
{
	LocationTimeTablePreference locationTimeTablePreference_;
	
	public SelectedPreferenceState(LinkedList<LocationTimeSlot> locationTimeSlots){
		this.locationTimeTablePreference_ = new LocationTimeTablePreference(locationTimeSlots);
	}
	
	@Override
	public Location getLocationAt(HospitalDate hospitalDate) {
		return this.locationTimeTablePreference_.getLocationAt(hospitalDate);
	}

}