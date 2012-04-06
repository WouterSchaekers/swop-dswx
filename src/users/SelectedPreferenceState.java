package users;

import java.util.LinkedList;
import exceptions.InvalidTimeSlotException;
import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import scheduler.LocationTimeTable;
import system.Location;

/**
 * Represents the choice of a Doctor to have a selected preference for campuses.
 */
class SelectedPreferenceState extends PreferenceState
{
	LocationTimeTable tasks_;
	LocationTimeTable pref_;

	/**
	 * Constructor without tasks.
	 * 
	 * @throws InvalidTimeSlotException
	 *             The given TimeSlots are not valid.
	 */
	public SelectedPreferenceState(LinkedList<LocationTimeSlot> preferences) throws InvalidTimeSlotException {
		this.pref_ = new LocationTimeTable(preferences);
		this.tasks_ = new LocationTimeTable();
	}

	/**
	 * Default constructor.
	 * 
	 * @param preferences
	 *            The preferences of the doctor. Should be 2 timeslots long.
	 * @param slots
	 *            Slots carried over by a change of state: from back&forth to
	 *            this
	 * @throws InvalidTimeSlotException
	 *             The given TimeSlots are not valid.
	 */
	public SelectedPreferenceState(LinkedList<LocationTimeSlot> preferences, LinkedList<LocationTimeSlot> slots)
			throws InvalidTimeSlotException {
		if (preferences.size() != 2)
			throw new IllegalArgumentException("Invalid preference!");
		this.pref_ = new LocationTimeTable(new LinkedList<LocationTimeSlot>(preferences));
		this.tasks_ = new LocationTimeTable(new LinkedList<LocationTimeSlot>(slots));
	}

	@Override
	public Location getLocationAt(HospitalDate hospitalDate) {
		Location location = tasks_.getLocationAt(hospitalDate);
		if (location == null)
			return pref_.getPreferedLocationAt(hospitalDate);
		return location;
	}

	@Override
	public LinkedList<LocationTimeSlot> getSlots() {
		return tasks_.getSlots();
	}

	@Override
	public void scheduleTaskAt(LocationTimeSlot slot) {
		tasks_.addLocationTimeSlot(slot);
	}

	@Override
	public LinkedList<Location> getCurrentPreference() {
		LinkedList<Location> rv = new LinkedList<Location>();
		for (LocationTimeSlot tsl : pref_.getSlots())
			rv.add(tsl.getLocation());
		return rv;
	}
}