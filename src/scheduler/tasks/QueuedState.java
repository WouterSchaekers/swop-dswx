package scheduler.tasks;

class QueuedState implements TaskState
{
	private TaskData data_;
	
	public QueuedState(TaskData taskData) {
		if (taskData == null)
			throw new IllegalArgumentException(taskData + " cannot be null!");
		this.data_ = taskData;
	}

	@Override
	public boolean isValidNextState(TaskState nextState) {
		return nextState instanceof ScheduledState;
	}

	@Override
	public TaskData getData() {
		return this.data_;
	}

}
