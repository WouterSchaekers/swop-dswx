package scheduler.tasks;

public abstract class Task
{
	protected final TaskDescription description_;

	public Task(TaskDescription description) {
		description_ = description;
	}

	public final TaskDescription getDescription() {
		return description_;
	}
}