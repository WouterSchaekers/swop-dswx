package scheduler.task;

import java.util.Date;
import exceptions.ImpossibleToScheduleException;
import scheduler.TimeSlot;
import scheduler.TimeTable;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(Date startDate, Date stopDate);

	public TimeTable getTimeTable();
	
	public void scheduleAt(TimeSlot timeSlot) throws ImpossibleToScheduleException;
	
}
