package scheduler.tasks;

import java.util.Collection;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import system.Whereabouts;

public class ScheduledTask extends Task
{
	Collection<Schedulable> resources_;
	TimeSlot timeSlot_;
	private Whereabouts _location;

	ScheduledTask(TaskDescription description,
			Collection<Schedulable> resources, TimeSlot timeSlot, Whereabouts location) {
		super(description);
		this.resources_ = resources;
		this.timeSlot_ = timeSlot;
		this._location = location;
	}

	public Collection<Schedulable> getUsedResources() {
		return this.resources_;
	}

	public HospitalDate getStartDate() {
		return this.timeSlot_.getStartPoint().getHospitalDate();
	}

	public HospitalDate getStopDate() {
		return this.timeSlot_.getStopPoint().getHospitalDate();
	}
	
	public Whereabouts getLocation(){
		return this._location;
	}
	
	public TimeSlot getTimeSlot(){
		return this.timeSlot_;
	}
}