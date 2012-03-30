package scheduler.tasks;

import java.util.Collection;
import scheduler.Schedulable;
import system.Location;

class ScheduledState implements TaskState
{
	private TaskData data_;

	public ScheduledState(TaskData taskData) {
		if (!isValidNewData(taskData))
			throw new IllegalArgumentException("Invalid data given to ScheduledState!");
		this.data_ = taskData;
	}

	@Override
	public TaskData getData() {
		return data_;
	}

	@Override
	public TaskState nextState(TaskData data) {
		return new FinishedState(data);
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
		return false;
	}

	@Override
	public boolean isQueued() {
		return false;
	}

	@Override
	public boolean isScheduled() {
		return true;
	}

	@Override
	public boolean isValidNewData(TaskData data) {
		return data != null && data.getDescription() != null
				&& data.getDescription().equals(this.data_.getDescription());
	}

}
