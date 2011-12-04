package task;

import java.util.Date;
import scheduler.timetables.TimeSlot;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(Date start, Date stop);

	public TimeSlot getTimeTable();
	
}
