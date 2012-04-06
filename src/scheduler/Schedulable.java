package scheduler;

import scheduler.requirements.Requirable;
import system.Location;
import exceptions.InvalidSchedulingRequestException;

/**
 * This interface is implemented by all things that can be scheduled.
 */
public interface Schedulable extends Requirable
{
	/**
	 * Checks whether the Schedulable can be Scheduled on a given TimeSlot.
	 * 
	 * @param startDate
	 *            The HospitalDate on which the TimeSlot starts.
	 * @param stopDate
	 *            The HospitalDate on which the TimeSlot stops.
	 * @return True if the Schedulable is free between the given HospitalDates.
	 */
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate);

	/**
	 * @return The TimeTable of the Schedulable.
	 */
	public TimeTable getTimeTable();

	/**
	 * Schedules the Schedulable on the given TimeSlot and Location.
	 * 
	 * @param timeSlot
	 *            The TimeSlot on which the Schedulable has to be scheduled on.
	 * @param location
	 *            The Location on which the Schedulable has to be scheduled at.
	 * @throws InvalidSchedulingRequestException
	 *             The Schedulable can not be scheduled at the given TimeSlot
	 *             and given Location.
	 */
	void scheduleAt(TimeSlot timeSlot, Location location) throws InvalidSchedulingRequestException;

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
	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException;

	/**
	 * Updates this TimeTable, so it stays clean. All the TimeSlots that are
	 * finished will be deleted.
	 * 
	 * @param newDate
	 *            The HospitalDate on which all the TimeSlots will have to be
	 *            checked against.
	 */
	public void updateTimeTable(HospitalDate newDate);

	/**
	 * @return True if the Schedulable can travel between different Locations.
	 */
	public boolean canTravel();

	/**
	 * @return True if the Schedulalbe must be scheduled back to back.
	 */
	public boolean mustBeBackToBack();

	/**
	 * Returns the Location of the Schedulable at a certain HospitalDate.
	 * 
	 * @param hospitalDate
	 *            The HospitalDate on which the Location of the Schedulable must
	 *            be known.
	 * @return The Location of the Schedulable on the given HospitalDate.
	 */
	public Location getLocationAt(HospitalDate hospitalDate);
}