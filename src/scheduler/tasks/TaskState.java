package scheduler.tasks;

public interface TaskState
{
	public boolean isValidNextState(TaskState nextState);

	public TaskData getData();
}
