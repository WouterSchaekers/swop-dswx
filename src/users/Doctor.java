package users;

import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import scheduler.StartTimePoint;
import scheduler.StopTimePoint;
import scheduler.TimeSlot;
import system.Location;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.UserFactoryIN;
import exceptions.InvalidLocationException;
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
	 * @throws InvalidLocationException
	 */
	Doctor(String name, Location location) throws InvalidNameException, InvalidLocationException {
		super(name, location);
		prefState_ = new BackAndForthState();
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException {
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate, duration);
	}

	@Override
	public Location getLocationAt(HospitalDate date) {
		if (prefState_.getLocationAt(date) == null)
			return location_;
		return prefState_.getLocationAt(date);
	}

	/**
	 * Changes the state of this doctor's preference to back and forth.
	 * 
	 * @throws InvalidTimeSlotException
	 *             The given TimeSlots are not valid.
	 */
	public void changePreferenceToBackAndForth() throws InvalidTimeSlotException {
		if (this.prefState_ instanceof BackAndForthState)
			return;
		PreferenceState newState = new BackAndForthState(prefState_.getSlots());
		this.prefState_ = newState;
	}

	/**
	 * Changes the state of this doctor's preference to selected.
	 * 
	 * @throws InvalidPreferenceException
	 * @throws InvalidTimeSlotException
	 *             The given TimeSlots are not valid.
	 */
	public void changePreferenceToSelected(LinkedList<Location> preferences) throws InvalidPreferenceException,
			InvalidTimeSlotException {
		if (prefState_ instanceof SelectedPreferenceState)
			return;
		if (preferences.size() != 2)
			throw new InvalidPreferenceException("Invalid preferences in new preference-state!");
		LinkedList<LocationTimeSlot> locSlots = new LinkedList<LocationTimeSlot>();

		for (int i = 0; i < preferences.size(); i++) {
			StartTimePoint start = new StartTimePoint(
					((((SchedulableUser.STOP_WORK_HOUR - SchedulableUser.START_WORK_HOUR) * i / preferences.size()) + SchedulableUser.START_WORK_HOUR) * HospitalDate.ONE_HOUR));
			StopTimePoint stop = new StopTimePoint(
					((((SchedulableUser.STOP_WORK_HOUR - SchedulableUser.START_WORK_HOUR) * (i + 1) / preferences
							.size()) + SchedulableUser.START_WORK_HOUR) * HospitalDate.ONE_HOUR - 1));
			TimeSlot timeSlot = new TimeSlot(start, stop);
			LocationTimeSlot locSlot = new LocationTimeSlot(timeSlot, preferences.get(i));
			locSlots.add(locSlot);
		}
		PreferenceState newState = new SelectedPreferenceState(locSlots, prefState_.getSlots());
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

	void nextState(PreferenceState preferenceState) {
		this.prefState_ = preferenceState;
	}

	public LinkedList<Location> getCurrentPreference() {
		return this.prefState_.getCurrentPreference();
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot, Location location) throws InvalidSchedulingRequestException {
		this.timeTable_.addTimeSlot(timeSlot);
		// XXX Dit mag en kan zo niet!!! Er moet een opsplitsing gemaakt worden
		// tussen de states! -> Oplossing = locationTimeTable in State steken.
		// this.locationTimeTable_.addLocationTimeSlot(new
		// LocationTimeSlot(timeSlot, location));
		// Dit is ondertussen al kindof gefixt.
	}

	@Override
	public UserFactoryIN getTypeIN() {

		return getType();
	}
}