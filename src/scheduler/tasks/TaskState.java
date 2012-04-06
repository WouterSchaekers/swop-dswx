package scheduler.tasks;

import java.util.Collection;
import scheduler.Schedulable;
import system.Location;

/**
 * Class representing the current State of a Task.
 */
public interface TaskState
{
	/**
	 * @return The data for this Task.
	 */
	public TaskData getData();

	/**
	 * Returns the Location of this Task.
	 */
	public Location getLocation();

	/**
	 * Returns the resources used for this Task.
	 */
	public Collection<Schedulable> getResources();

	/**
	 * Returns true if the Task is finished.
	 */
	public boolean isFinished();

	/**
	 * Returns true if the Task is queued.
	 */
	public boolean isQueued();

	/**
	 * Returns true if the Task is scheduled.
	 */
	public boolean isScheduled();

	/**
	 * Checks whether the given TaskData is valid.
	 * 
	 * @param data
	 *            The TaskData that has to be checked.
	 * @return True if the TaskData is valid.
	 */
	boolean isValidNewData(TaskData data);

	/**
	 * Cycles through to the next state: Queued -> Scheduled -> Finished
	 * 
	 * @param newData
	 *            A data object that contains the data for the new state.
	 */
	public TaskState nextState(TaskData data);
}