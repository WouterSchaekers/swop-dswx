package scheduler;

import java.util.Collection;
import scheduler.tasks.UnscheduledTask;
import system.Location;

/**
 * Use this object to transfer a bunch of data to the scheduler when you want to
 * schedule something.
 */
public class SchedulingData
{
	private Collection<Schedulable> allSchedulables_;
	private Collection<Location> locations_;
	private TimeLord timeLord_;
	private UnscheduledTask unscheduledTask_;

	public SchedulingData(Collection<Schedulable> schedulables, Collection<Location> locations, TimeLord timeLord,
			UnscheduledTask unscheduledTask) {
		this.allSchedulables_ = schedulables;
		this.locations_ = locations;
		this.timeLord_ = timeLord;
		this.unscheduledTask_ = unscheduledTask;
	}

	/**
	 * @return the allSchedulables_
	 */
	public Collection<Schedulable> getAllResources() {
		return this.allSchedulables_;
	}
	
	public Collection<Location> getLocations(){
		return this.locations_;
	}

	/**
	 * @return the timeLord_
	 */
	public TimeLord getTimeLord() {
		return this.timeLord_;
	}

	/**
	 * @return the task_
	 */
	public UnscheduledTask getUnscheduledTask() {
		return this.unscheduledTask_;
	}
}