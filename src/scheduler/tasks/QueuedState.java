package scheduler.tasks;

import java.util.Collection;
import scheduler.Schedulable;
import system.Location;

/**
 * Class representing the queued state of the Task.
 */
class QueuedState implements TaskState
{
	private TaskData data_;

	/**
	 * Default constructor.
	 */
	public QueuedState(TaskData taskData) {
		if (!isValidNewData(taskData))
			throw new IllegalArgumentException("Invalid data given to QueuedState!");
		this.data_ = taskData;
	}

	/**
	 * @return The data for this Task.
	 */
	@Override
	public TaskData getData() {
		return this.data_;
	}

	/**
	 * Cycles through to the next state: Queued -> Scheduled
	 * 
	 * @param newData
	 *            A data object that contains the data for the new state.
	 */
	@Override
	public TaskState nextState(TaskData data) {
		return new ScheduledState(data);
	}

	/**
	 * Returns the Location of this Task.
	 */
	@Override
	public Location getLocation() {
		throw new IllegalStateException("Queued states do not have locations!");
	}

	/**
	 * Returns the resources used for this Task.
	 */
	@Override
	public Collection<Schedulable> getResources() {
		return data_.getAllAvailableResources();
	}

	/**
	 * Returns false;
	 */
	@Override
	public boolean isFinished() {
		return false;
	}

	/**
	 * Returns true.
	 */
	@Override
	public boolean isQueued() {
		return true;
	}

	/**
	 * Returns false.
	 */
	@Override
	public boolean isScheduled() {
		return false;
	}

	/**
	 * Checks whether the given TaskData is valid.
	 * 
	 * @param data
	 *            The TaskData that has to be checked.
	 * @return True if the TaskData is valid.
	 */
	@Override
	public boolean isValidNewData(TaskData data) {
		return data != null && data.getDescription() != null;
	}
}