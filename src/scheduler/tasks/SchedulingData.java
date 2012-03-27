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
public class SchedulingData
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

	public SchedulingData(TaskDescription description_, Hospital hospital) {
		this(getAllCollections(hospital),new ArrayList<Location>(hospital.getAllCampusses()),hospital.getSystemTime(),description_);
	}

	private static Collection<Schedulable> getAllCollections(Hospital hospital) {
		// TODO Auto-generated method stub
		return null;
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
	public TaskDescription getDescription() {
		return this.unscheduledTask_;
	}
}