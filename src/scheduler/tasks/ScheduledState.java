package scheduler.tasks;

class ScheduledState implements TaskState
{
	private TaskData data_;

	public ScheduledState(TaskData taskData) {
		if (taskData == null)
			throw new IllegalArgumentException(taskData + " cannot be null!");
		this.data_ = taskData;
	}

	@Override
	public boolean isValidNextState(TaskState nextState) {
		return nextState instanceof FinishedState;
	}

	@Override
	public TaskData getData() {
		return data_;
	}

}
