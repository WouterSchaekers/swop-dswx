package scheduler.tasks;

public abstract class Task<T extends TaskDescription>
{
	protected final T description_;

	public Task(T description) {
		description_ = description;
	}

	public final T getDescription() {
		return description_;
	}
}