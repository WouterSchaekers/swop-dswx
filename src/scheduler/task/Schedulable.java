package scheduler.task;

import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler.TimeTable;

/**
 * This interface is implemented by all things that can be scheduled.
 */
public interface Schedulable
{

	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException;

	public TimeTable getTimeTable() throws InvalidTimeSlotException;

	public void scheduleAt(TimeSlot timeSlot)
			throws InvalidSchedulingRequestException;

	public TimeSlot getFirstFreeSlotBetween(HospitalDate startDate,
			HospitalDate stopDate, long duration) throws InvalidSchedulingRequestException, InvalidTimeSlotException, InvalidHospitalDateArgument;
}
