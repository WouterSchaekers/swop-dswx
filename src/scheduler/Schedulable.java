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
//TODO: zeg hier dingen over in het verslag, scheduleAt mag niet publiek zijn
	//maar java doet moeilijk! interfaces laten dit niet toe
	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException;

	public TimeTable getTimeTable() throws InvalidTimeSlotException;
	
	
	void scheduleAt(TimeSlot timeSlot)
			throws InvalidSchedulingRequestException;

	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate,
			HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidHospitalDateArgument;
	/**
	 * Sets the start time of the timetable to newdate, since everything before that point has already passed.
	 * @param newDate
	 */
	public void updateTimeTable(HospitalDate newDate);
	
	public boolean canTravel();
	
	public Location getLocation();
}