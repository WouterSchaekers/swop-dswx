package task;

import java.util.Date;
import scheduler.TimeSlot;
import scheduler.TimeTable;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(Date start, Date stop);

	public TimeTable getTimeTable();
	
	public void scheduleAt(TimeSlot t);
	
}
