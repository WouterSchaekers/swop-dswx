package scheduler2;

public class Task
{
	private final TaskDescription _description;
	public Task(TaskDescription description)
	{
		_description=description;
	}
	public TaskDescription getDescription()
	{
		return _description;
	}
	
}
