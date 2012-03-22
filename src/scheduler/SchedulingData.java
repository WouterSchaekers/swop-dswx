package scheduler;

import java.util.Collection;
import scheduler.tasks.UnscheduledTask;

public class SchedulingData
{
	private Collection<Schedulable> allSchedulables_;
	private TimeLord timeLord_;
	private UnscheduledTask task_;

	public SchedulingData(Collection<Schedulable> schedulables, TimeLord tl,
			UnscheduledTask task) {
		this.allSchedulables_ = schedulables;
		this.timeLord_ = tl;
		this.task_ = task;
	}

	/**
	 * @return the allSchedulables_
	 */
	public Collection<Schedulable> getAllSchedulables() {
		return allSchedulables_;
	}

	/**
	 * @return the timeLord_
	 */
	public TimeLord getTimeLord() {
		return timeLord_;
	}

	/**
	 * @return the task_
	 */
	public UnscheduledTask getTask() {
		return task_;
	}

	/**
	 * @param task_
	 *            the task_ to set
	 */
	public void setTask_(UnscheduledTask task_) {
		this.task_ = task_;
	}
}
