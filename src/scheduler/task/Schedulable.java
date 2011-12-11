package scheduler.task;

import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler.TimeTable;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate)throws InvalidSchedulingRequestException, InvalidTimeSlotException;

	public TimeTable getTimeTable() throws InvalidTimeSlotException;
	
	public void scheduleAt(TimeSlot timeSlot) throws InvalidSchedulingRequestException;
	
}
