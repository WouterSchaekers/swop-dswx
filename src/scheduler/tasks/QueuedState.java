package scheduler.tasks;

import java.util.Collection;
import scheduler.Schedulable;
import system.Location;

class QueuedState implements TaskState
{
	private TaskData data_;

	public QueuedState(TaskData taskData) {
		if (!isValidNewData(taskData))
			throw new IllegalArgumentException("Invalid data given to QueuedState!");
		this.data_ = taskData;
	}

	@Override
	public TaskData getData() {
		return this.data_;
	}

	@Override
	public TaskState nextState(TaskData data) {
		return new ScheduledState(data);
	}

	@Override
	public Location getLocation() {
		throw new IllegalStateException("Queued states do not have locations!");
	}

	@Override
	public Collection<Schedulable> getResources() {
		return data_.getAllAvailableResources();
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public boolean isQueued() {
		return true;
	}

	@Override
	public boolean isScheduled() {
		return false;
	}

	@Override
	public boolean isValidNewData(TaskData data) {
		return data != null && data.getDescription() != null;
	}
}
