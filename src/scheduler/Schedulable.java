package scheduler;

import scheduler.requirements.Requirable;
import system.Location;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This interface is implemented by all things that can be scheduled.
 */
public interface Schedulable extends Requirable
{
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException;

	public TimeTable getTimeTable();

	void scheduleAt(TimeSlot timeSlot, Location location) throws InvalidSchedulingRequestException;

	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidHospitalDateArgument;

	/**
	 * Sets the start time of the timetable to newdate, since everything before
	 * that point has already passed.
	 * 
	 * @param newDate
	 */
	public void updateTimeTable(HospitalDate newDate);
	
	public boolean canTravel();
	
	public boolean mustBeBackToBack();
	
	public Location getLocationAt(HospitalDate hospitalDate);
}