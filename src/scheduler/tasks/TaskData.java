package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import system.Hospital;
import system.Location;

/**
 * Use to store data about Tasks.
 */
class TaskData
{
	private TaskDescription description_;
	private Hospital hospital_;
	private Location location_;
	private Collection<Schedulable> resources_;
	private TimeSlot timeSlot_;

	public TaskData(Hospital hospital) {
		if (hospital == null)
			throw new IllegalArgumentException(hospital + " cannot be null!");
		this.hospital_ = hospital;
	}

	public boolean before(TaskData bestSchedTask) {
		return this.getTimeSlot().before(bestSchedTask.getTimeSlot());
	}

	@Override
	public TaskData clone() {
		TaskData rv = new TaskData(this.hospital_);
		rv.setLocation(this.location_);
		rv.setTimeSlot(this.timeSlot_);
		rv.setUsedResources(this.resources_);
		rv.setDescription(this.description_);
		return rv;
	}

	public Collection<Schedulable> getAllAvailableResources() {
		return this.hospital_.getAllSchedulables();
	}

	public TaskDescription getDescription() {
		return description_;
	}

	public Location getLocation() {
		return this.location_;
	}

	public Collection<Location> getLocations() {
		return new LinkedList<Location>(this.hospital_.getAllLocations());
	}

	public Collection<Schedulable> getResources() {
		if(this.resources_ != null)
			return new LinkedList<Schedulable>(this.resources_);
		return new LinkedList<Schedulable>();
	}

	public HospitalDate getStartDate() {
		return this.timeSlot_.getStartPoint().getHospitalDate();
	}

	public HospitalDate getStopDate() {
		return this.timeSlot_.getStopPoint().getHospitalDate();
	}

	public HospitalDate getSystemTime() {
		return this.hospital_.getTimeKeeper().getSystemTime();
	}

	public TimeSlot getTimeSlot() {
		return this.timeSlot_;
	}

	void setDescription(TaskDescription description_) {
		this.description_ = description_;
	}

	void setLocation(Location location) {
		this.location_ = location;
	}

	void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot_ = timeSlot;
	}

	void setUsedResources(Collection<Schedulable> usedResources) {
		this.resources_ = usedResources;
	}

}
