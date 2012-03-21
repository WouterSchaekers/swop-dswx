package scheduler.tasks;

public class Task
{
	protected final TaskDescription description_;

	public Task(TaskDescription description) {
		description_ = description;
	}

	public TaskDescription getDescription() {
		return description_;
	}
}