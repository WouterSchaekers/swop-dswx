package scheduler;

public class Task
{
	protected final TaskDescription _description;
	public Task(TaskDescription description)
	{
		_description = description;
	}
	
	public TaskDescription getDescription()
	{
		return _description;
	}
}