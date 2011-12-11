package scheduler.task;

import java.util.*;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import scheduler.TimeSlot;
import scheduler.TimeTable;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(Date startDate, Date stopDate)throws InvalidSchedulingRequestException, InvalidTimeSlotException;

	public TimeTable getTimeTable() throws InvalidTimeSlotException;
	
	public void scheduleAt(TimeSlot timeSlot) throws InvalidSchedulingRequestException;
	
}
