package scheduler.tasks;
 /**
  * NEVER EXTEND THIS CLASS
  * 
  */
public abstract class FinishedTask<T extends TaskDescription> extends Task<T>
{

	public FinishedTask(T description) {
		super(description);
	}

}
