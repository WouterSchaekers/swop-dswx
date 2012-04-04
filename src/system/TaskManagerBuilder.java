package system;

import scheduler.tasks.TaskManager;

public class TaskManagerBuilder
{
	public TaskManager create(Hospital hospital)
	{
		return new TaskManager(hospital);
	}
}
