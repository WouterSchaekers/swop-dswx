package scheduler.tasks;

import java.util.Collection;
import scheduler.Schedulable;
import system.Location;

public interface TaskState
{
	public TaskData getData();

	public Location getLocation();

	public Collection<Schedulable> getResources();

	public boolean isFinished();

	public boolean isQueued();

	public boolean isScheduled();

	boolean isValidNewData(TaskData data);

	public TaskState nextState(TaskData data);

}
