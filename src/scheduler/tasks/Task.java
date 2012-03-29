package scheduler.tasks;

/**
 * Represents a task that can be scheduled, completed or queued.
 */
public final class Task<T extends TaskDescription>
{
	private final T description_;
	private TaskState myState_;
	
	Task(T description) {
		description_ = description;
	}

	public final T getDescription() {
		return description_;
	}

	void setState(TaskState nextState) throws IllegalStateException {
		if(myState_.isValidNextState(nextState))
			this.myState_ = nextState;
		else throw new IllegalStateException("Invalid state given to setState!");
	}

	TaskData getData() {
		return this.myState_.getData();
	}

	boolean before(Task<?> otherTask) {
		return this.getData().getTimeSlot().getStartPoint().getHospitalDate()
				.before(otherTask.getData().getTimeSlot().getStartPoint().getHospitalDate());
	}

}