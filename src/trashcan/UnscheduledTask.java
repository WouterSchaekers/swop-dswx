package scheduler.tasks;

import java.util.Collection;
import scheduler.Schedulable;
import scheduler.TimeLord;
import system.Hospital;
import system.Location;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidSchedulingRequestException;

class UnscheduledTask<T extends TaskDescription> extends Task<T>
{
	private Hospital hospital_;

	/**
	 * Creates an unscheduled task in your hospital.
	 * 
	 * @param description
	 */
	UnscheduledTask(T description, Hospital hospital) {
		super(description);
		this.hospital_ = hospital;
	}

	public ScheduledTask<?> scheduleIn(Hospital hospital)
			throws InvalidSchedulingRequestException,
			CanNeverBeScheduledException {
		return new Scheduler().schedule(this);
	}
	
	public Collection<Location> getLocations(){
		return this.hospital_.getAllLocations();
	}
	
	public Collection<Schedulable> getAllResources(){
		return this.hospital_.getAllSchedulables();
	}
	
	public TimeLord getTimeLord(){
		return this.hospital_.getTimeKeeper();
	}
}