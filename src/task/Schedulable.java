package task;

import java.util.Date;
import scheduler.timetables.TimeTable;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(Date start, Date stop);

	public TimeTable getTimeTable();
	
}
