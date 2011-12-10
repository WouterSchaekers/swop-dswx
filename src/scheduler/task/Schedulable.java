package scheduler.task;

import scheduler.Date;
import exceptions.ImpossibleToScheduleException;
import scheduler.TimeSlot;
import scheduler.TimeTable;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(Date startDate, Date stopDate)throws ImpossibleToScheduleException;

	public TimeTable getTimeTable();
	
	public void scheduleAt(TimeSlot timeSlot) throws ImpossibleToScheduleException;
	
}
