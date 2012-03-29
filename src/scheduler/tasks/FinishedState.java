package scheduler.tasks;

class FinishedState implements TaskState
{
	private TaskData data_;

	public FinishedState(TaskData taskData) {
		if (taskData == null)
			throw new IllegalArgumentException(taskData + " cannot be null!");
		this.data_ = taskData;
	}

	@Override
	public boolean isValidNextState(TaskState nextState) {
		return false; // last state
	}

	@Override
	public TaskData getData() {
		return data_;
	}

}
