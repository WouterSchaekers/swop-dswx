package scheduler;

import java.util.Collection;
import scheduler.tasks.UnscheduledTask;

/**
 * Use this object to transfer a bunch of data to the scheduler when you want to
 * schedule something.
 */
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
}
