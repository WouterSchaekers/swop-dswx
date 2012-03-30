package scheduler.tasks;

import java.util.Collection;
import result.Result;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeLord;
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
	private Result result_;
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
		rv.setLocation(this.getLocation());
		rv.setResult_(this.getResult());
		rv.setTimeSlot(this.getTimeSlot());
		rv.setUsedResources(this.getResources());
		return rv;
	}

	public Collection<Schedulable> getAllResources() {
		return this.hospital_.getAllSchedulables();
	}

	public TaskDescription getDescription() {
		return description_;
	}

	public Location getLocation() {
		return this.location_;
	}

	public Collection<Location> getLocations() {
		return this.hospital_.getAllLocations();
	}

	public Collection<Schedulable> getResources() {
		return this.resources_;
	}

	public Result getResult() {
		return result_;
	}

	public HospitalDate getStartDate() {
		return this.timeSlot_.getStartPoint().getHospitalDate();
	}

	public HospitalDate getStopDate() {
		return this.timeSlot_.getStopPoint().getHospitalDate();
	}

	public TimeLord getTimeLord() {
		return this.hospital_.getTimeKeeper();
	}

	public TimeSlot getTimeSlot() {
		return this.timeSlot_;
	}

	void setDescription(TaskDescription description_) {
		this.description_ = description_;
	}

	void setLocation(Location location) {
		if (location == null)
			throw new IllegalArgumentException(location + " cannot be null!");
		this.location_ = location;
	}

	void setResult_(Result result_) {
		this.result_ = result_;
	}

	void setTimeSlot(TimeSlot timeSlot) {
		if (timeSlot == null)
			throw new IllegalArgumentException(timeSlot + " cannot be null!");
		this.timeSlot_ = timeSlot;
	}

	void setUsedResources(Collection<Schedulable> usedResources) {
		if (usedResources == null)
			throw new IllegalArgumentException(usedResources + " cannot be null!");
		this.resources_ = usedResources;
	}

}