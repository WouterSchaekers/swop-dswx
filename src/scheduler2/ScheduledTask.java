package scheduler2;

import java.util.Collection;
import schedulerold.task.Schedulable;
import system.Whereabouts;

public class ScheduledTask extends Task
{
	Collection<Schedulable> _resources;
	TimeSlot _timeSlot;
	private Whereabouts _location;

	ScheduledTask(TaskDescription description,
			Collection<Schedulable> resources, TimeSlot timeSlot, Whereabouts location) {
		super(description);
		this._resources = resources;
		this._timeSlot = timeSlot;
		this._location = location;
	}

	public Collection<Schedulable> getUsedResources() {
		return this._resources;
	}

	public HospitalDate getStartDate() {
		return this._timeSlot.getStartPoint().getHospitalDate();
	}

	public HospitalDate getStopDate() {
		return this._timeSlot.getStopPoint().getHospitalDate();
	}
	
	public Whereabouts getLocation(){
		return this._location;
	}
	
	public TimeSlot getTimeSlot(){
		return this._timeSlot;
	}
}