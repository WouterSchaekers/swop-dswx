package scheduler.task;

import java.util.*;
import exceptions.InvalidSchedulingRequestException;
import scheduler.TimeSlot;
import scheduler.TimeTable;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(Date startDate, Date stopDate)throws InvalidSchedulingRequestException;

	public TimeTable getTimeTable();
	
	public void scheduleAt(TimeSlot timeSlot) throws InvalidSchedulingRequestException;
	
}
