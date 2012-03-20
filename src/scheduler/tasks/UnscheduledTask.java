package scheduler.tasks;

import system.Hospital;

public abstract class UnscheduledTask extends Task
{	
	public UnscheduledTask(TaskDescription description) 
	{
		super(description);
	}
	
	public boolean canBeScheduled(Hospital hospital){return false;}
}