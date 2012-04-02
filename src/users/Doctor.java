package users;

import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import scheduler.TimeSlot;
import system.Location;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidNameException;
import exceptions.InvalidPreferenceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * Represents a Doctor in the hospital.
 */
public class Doctor extends SchedulableUser implements DoctorIN
{
	private PreferenceState prefState_;

	/**
	 * Initialises a new Doctor. Default preference state = back and forth.
	 * 
	 * @throws InvalidNameException
	 */
	Doctor(String name, Location preference) throws InvalidNameException {
		super(name, preference);
		prefState_ = new BackAndForthState(new LinkedList<LocationTimeSlot>());
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate, duration);
	}

	@Override
	public Location getLocationAt(HospitalDate date) {
		return prefState_.getLocationAt(date);
	}

	/**
	 * Changes the state of this doctor's preference to back and forth.
	 */
	public void changePreferenceToBackAndForth() {
		if(this.prefState_ instanceof BackAndForthState)
			return;
		PreferenceState newState = new BackAndForthState(prefState_.getSlots());
		this.prefState_ = newState;
	}

	/**
	 * Changes the state of this doctor's preference to selected.
	 * @throws InvalidPreferenceException
	 */
	public void changePreferenceToSelected(LinkedList<LocationTimeSlot> preferences) throws InvalidPreferenceException {
		if(prefState_ instanceof SelectedPreferenceState)
			return;
		if (preferences.size() != 2)
			throw new InvalidPreferenceException("Invalid preferences in new preference-state!");
		PreferenceState newState = new SelectedPreferenceState(preferences, prefState_.getSlots());
		this.prefState_ = newState;
	}

	@Override
	public boolean canTravel() {
		return true;
	}

	@Override
	public UserFactory getType() {
		return new DoctorFactory();
	}
	
	void nextState(PreferenceState preferenceState){
		this.prefState_ = preferenceState;
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot, Location location) throws InvalidSchedulingRequestException {
		this.timeTable_.addTimeSlot(timeSlot);
		// Dit mag en kan zo niet!!! Er moet een opsplitsing gemaakt worden
		// tussen de states! -> Oplossing = locationTimeTable in State steken.
		//this.locationTimeTable_.addLocationTimeSlot(new LocationTimeSlot(timeSlot, location));
		//Dit is ondertussen al kindof gefixt.
	}
}