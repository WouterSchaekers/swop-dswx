package scheduler.tasks;

import java.util.Collection;
import scheduler.Schedulable;
import system.Location;

/**
 * Class representing the finished state of the Task.
 */
class FinishedState implements TaskState
{
	private TaskData data_;

	/**
	 * Default constructor.
	 */
	public FinishedState(TaskData taskData) {
		if (!isValidNewData(taskData))
			throw new IllegalArgumentException("Invalid data given to FinishedState!");
		this.data_ = taskData;
	}

	/**
	 * @return The data for this Task.
	 */
	@Override
	public TaskData getData() {
		return data_;
	}

	/**
	 * Cycles through to the next state: Finished -> Finished
	 * 
	 * @param newData
	 *            A data object that contains the data for the new state.
	 */
	@Override
	public TaskState nextState(TaskData data) {
		return this;
	}

	/**
	 * Returns the Location of this Task.
	 */
	@Override
	public Location getLocation() {
		return data_.getLocation();
	}

	/**
	 * Returns the resources used for this Task.
	 */
	@Override
	public Collection<Schedulable> getResources() {
		return data_.getResources();
	}

	/**
	 * Returns true;
	 */
	@Override
	public boolean isFinished() {
		return true;
	}

	/**
	 * Returns false;
	 */
	@Override
	public boolean isQueued() {
		return false;
	}

	/**
	 * Returns false;
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
		return data != null && !data.getResources().isEmpty() && data.getLocation() != null
				&& data.getDescription() != null && data.getTimeSlot() != null;
	}
}