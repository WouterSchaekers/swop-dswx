package scheduler.tasks;

import java.util.ArrayList;
import java.util.Collection;
import scheduler.Schedulable;
import scheduler.TimeLord;
import system.Hospital;
import system.Location;

/**
 * Use this object to transfer a bunch of data to the scheduler when you want to
 * schedule something.
 */
class SchedulingData
{
	
	private Collection<Schedulable> allSchedulables_;
	private Collection<Location> locations_;
	private TimeLord timeLord_;

	private TaskDescription unscheduledTask_;

	 private SchedulingData(Collection<Schedulable> schedulables, Collection<Location> locations, TimeLord timeLord,
			TaskDescription unscheduledTask) {
		this.allSchedulables_ = schedulables;
		this.locations_ = locations;
		this.timeLord_ = timeLord;
		this.unscheduledTask_ = unscheduledTask;
	}

	SchedulingData(TaskDescription description_, Hospital hospital) {
		this(hospital.getAllSchedulables(),new ArrayList<Location>(hospital.getAllCampusses()),hospital.getTimeKeeper(),description_);
	}

	/**
	 * @return the allSchedulables_
	 */
	 Collection<Schedulable> getAllResources() {
		return this.allSchedulables_;
	}
	
	 /**
	 * @return the task_
	 */
	 TaskDescription getDescription() {
		return this.unscheduledTask_;
	}

	Collection<Location> getLocations(){
		return this.locations_;
	}

	/**
	 * @return the timeLord_
	 */
	 TimeLord getTimeLord() {
		return this.timeLord_;
	}
	 
}