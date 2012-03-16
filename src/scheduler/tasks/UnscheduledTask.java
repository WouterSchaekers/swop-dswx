package scheduler.tasks;

public abstract class UnscheduledTask extends Task
{
	public UnscheduledTask(TaskDescription description) 
	{
		super(description);
	}
}