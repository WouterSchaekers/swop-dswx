package scheduler.tasks;

import java.util.Collection;
import scheduler.Schedulable;
import system.Location;

class FinishedState implements TaskState
{
	private TaskData data_;

	public FinishedState(TaskData taskData) {
		if (!isValidNewData(taskData))
			throw new IllegalArgumentException("Invalid data given to FinishedState!");
		this.data_ = taskData;
	}

	@Override
	public TaskData getData() {
		return data_;
	}

	@Override
	public TaskState nextState(TaskData data) {
		return this;
	}

	@Override
	public Location getLocation() {
		return data_.getLocation();
	}

	@Override
	public Collection<Schedulable> getResources() {
		return data_.getResources();
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public boolean isQueued() {
		return false;
	}

	@Override
	public boolean isScheduled() {
		return false;
	}

	@Override
	public boolean isValidNewData(TaskData data) {
		return data != null && !data.getResources().isEmpty() && data.getLocation() != null
				&& data.getDescription() != null && data.getTimeSlot() != null;
	}
}
