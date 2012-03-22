package scheduler.tasks;

import java.util.Collection;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import system.Campus;

public class ScheduledTask extends Task
{
	private Collection<Schedulable> resources_;
	private TimeSlot timeSlot_;
	private Campus location_;

	ScheduledTask(TaskDescription description,
			Collection<Schedulable> resources, TimeSlot timeSlot, Campus location) {
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
	
	public Campus getLocation(){
		return this.location_;
	}
	
	public TimeSlot getTimeSlot(){
		return this.timeSlot_;
	}
}