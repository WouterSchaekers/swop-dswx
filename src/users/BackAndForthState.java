package users;

import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import scheduler.LocationTimeTable;
import system.Location;

class BackAndForthState extends PreferenceState
{
	LocationTimeTable locationTimeTablePreference_;
	
	/**
	 * Initialise a new BackAndForthState without any Tasks.
	 */
	public BackAndForthState() {
		this.locationTimeTablePreference_ = new LocationTimeTable(new LinkedList<LocationTimeSlot>());
	}
	
	/**
	 * Initialise a new BackAndForthState with a set of already created slots.
	 * @param tasks
	 */
	public BackAndForthState(LinkedList<LocationTimeSlot> tasks) {
		this.locationTimeTablePreference_ = new LocationTimeTable(tasks);
	}
	
	@Override
	public Location getLocationAt(HospitalDate date) {
		return this.locationTimeTablePreference_.getLocationAt(date);
	}

	@Override
	public LinkedList<LocationTimeSlot> getSlots() {
		return locationTimeTablePreference_.getSlots();
	}

	@Override
	public void scheduleTaskAt(LocationTimeSlot slot) {
		locationTimeTablePreference_.addLocationTimeSlot(slot);
	}

}
