package task;

import java.util.Date;
import scheduler.timetables.TimeTable;
import scheduler.timetables.TimeSlot;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(Date start, Date stop);

	public TimeTable getTimeTable();
	
	public void scheduleAt(TimeSlot t);
	
}
