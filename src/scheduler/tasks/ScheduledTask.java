package scheduler.tasks;

import java.util.Collection;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import system.Location;

public class ScheduledTask extends Task
{
	private Collection<Schedulable> resources_;
	private TimeSlot timeSlot_;
	private Location location_;

	ScheduledTask(TaskDescription description,
			Collection<Schedulable> resources, TimeSlot timeSlot, Location location) {
		super(description);
		this.resources_ = resources;
		this.timeSlot_ = timeSlot;
		this.location_ = location;
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
	
	public Location getLocation(){
		return this.location_;
	}
	
	public TimeSlot getTimeSlot(){
		return this.timeSlot_;
	}
	
	public boolean before(ScheduledTask otherTask){
		return this.timeSlot_.getStartPoint().getHospitalDate().before(otherTask.getTimeSlot().getStartPoint().getHospitalDate());
	}
}