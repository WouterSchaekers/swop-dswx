package users;

import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import scheduler.StartTimePoint;
import scheduler.StopTimePoint;
import scheduler.TimeSlot;
import system.Hospital;
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
	 * Initialises a new Doctor. The default preference state is back and forth.
	 * 
	 * @throws InvalidNameException
	 *             The given name is not valid.
	 * @throws InvalidLocationException
	 *             The given Location is not valid.
	 */
	Doctor(String name, Location location) throws InvalidNameException, InvalidLocationException {
		super(name, location);
		prefState_ = new BackAndForthState();
	}
	
	/**
	 * Returns the first free slot on a certain Location between two given
	 * HospitalDates with a given duration.
	 * 
	 * @param location
	 *            The Location on which the first slot needs to come from.
	 * @param startDate
	 *            The TimeSlot must lay behind this HospitalDate.
	 * @param stopDate
	 *            The TimeSlot must lay before this HospitalDate.
	 * @param duration
	 *            The minimum duration of the TimeSlot.
	 * @return The biggest possible TimeSlot at the given Location, between the
	 *         two given HospitalDates with a minimum duration.
	 * @throws InvalidSchedulingRequestException
	 *             The StopDate may not be behind the StopDate.
	 */
	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException {
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate, duration);
	}

	/**
	 * Returns the Location of the Schedulable at a certain HospitalDate.
	 * 
	 * @param hospitalDate
	 *            The HospitalDate on which the Location of the Schedulable must
	 *            be known.
	 * @return The Location of the Schedulable on the given HospitalDate.
	 */
	@Override
	public Location getLocationAt(HospitalDate date) {
		if (prefState_.getLocationAt(date) == null)
			return location_;
		return prefState_.getLocationAt(date);
	}

	/**
	 * Changes the state of this Doctor's preference to back and forth.
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
	 *             The size of the preferences is not two.
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

	/**
	 * @return True if the Schedulable can travel between different Locations.
	 */
	@Override
	public boolean canTravel() {
		return true;
	}

	/**
	 * @return A DoctorFactory.
	 */
	@Override
	public UserFactory getType() {
		return new DoctorFactory();
	}

	/**
	 * Sets the next State.
	 * 
	 * @param preferenceState
	 *            The State that will be set.
	 */
	void nextState(PreferenceState preferenceState) {
		this.prefState_ = preferenceState;
	}

	/**
	 * @return The current preference.
	 */
	public LinkedList<Location> getCurrentPreference() {
		return this.prefState_.getCurrentPreference();
	}

	/**
	 * Schedules the Doctor on the given TimeSlot and Location.
	 * 
	 * @param timeSlot
	 *            The TimeSlot on which the Schedulable has to be scheduled on.
	 * @param location
	 *            The Location on which the Schedulable has to be scheduled at.
	 * @throws InvalidSchedulingRequestException
	 *             The Schedulable can not be scheduled at the given TimeSlot
	 *             and given Location.
	 */
	@Override
	public void scheduleAt(TimeSlot timeSlot, Location location) throws InvalidSchedulingRequestException {
		this.timeTable_.addTimeSlot(timeSlot);
		this.prefState_.scheduleTaskAt(new LocationTimeSlot(timeSlot, location));
	}

	@Override
	public UserFactoryIN getTypeIN() {

		return getType();
	}
}