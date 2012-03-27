package scheduler.tasks;

public class FinishedTask<T extends TaskDescription> extends Task<T>
{

	public FinishedTask(T description) {
		super(description);
	}

}
