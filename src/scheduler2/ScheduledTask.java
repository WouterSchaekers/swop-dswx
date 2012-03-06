package scheduler2;

import java.util.Collection;
import scheduler.HospitalDate;
import scheduler.task.Schedulable;

public class ScheduledTask extends Task
{

	ScheduledTask(TaskDescription description) 
	{
		super(description);
	}
	public Collection<Schedulable> getUsedResources()
	{
		return null;
		
	}
	public HospitalDate getStartDate()
	{
		return null;
	}
	public HospitalDate getEndDate()
	{
		return null;
	}
	
	
}
