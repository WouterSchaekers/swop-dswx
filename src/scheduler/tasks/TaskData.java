package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import system.Hospital;
import system.Location;

/**
 * Class used to store data about Tasks.
 */
class TaskData
{
	private TaskDescription description_;
	private Hospital hospital_;
	private Location location_;
	private Collection<Schedulable> resources_;
	private TimeSlot timeSlot_;

	/**
	 * Default constructor.
	 * 
	 * @param hospital
	 *            The Hospital that contains all the resources.
	 */
	public TaskData(Hospital hospital) {
		if (hospital == null)
			throw new IllegalArgumentException(hospital + " cannot be null!");
		this.hospital_ = hospital;
	}

	/**
	 * Checks whether this TaskData object is scheduled before another TaskData
	 * object.
	 * 
	 * @param bestSchedTask
	 *            The other TaskData object.
	 * @return True if the TimeSlot of this TaskData object is before the
	 *         TimeSlot of the other TaskData object.
	 */
	boolean before(TaskData bestSchedTask) {
		return this.getTimeSlot().before(bestSchedTask.getTimeSlot());
	}

	/**
	 * Returns a copy of this object.
	 */
	@Override
	public TaskData clone() {
		TaskData rv = new TaskData(this.hospital_);
		rv.setLocation(this.location_);
		rv.setTimeSlot(this.timeSlot_);
		rv.setUsedResources(this.resources_);
		rv.setDescription(this.description_);
		return rv;
	}

	/**
	 * @return All the Schedulables of the Hospital.
	 */
	public Collection<Schedulable> getAllAvailableResources() {
		return this.hospital_.getAllSchedulables();
	}

	/**
	 * @return The Description.
	 */
	public TaskDescription getDescription() {
		return this.description_;
	}

	/**
	 * @return The Location.
	 */
	public Location getLocation() {
		return this.location_;
	}

	/**
	 * @return All the Locations of the Hospital.
	 */
	public Collection<Location> getLocations() {
		return new LinkedList<Location>(this.hospital_.getAllLocations());
	}

	/**
	 * @return The used Schedulables.
	 */
	public Collection<Schedulable> getResources() {
		if (this.resources_ != null)
			return new LinkedList<Schedulable>(this.resources_);
		return new LinkedList<Schedulable>();
	}

	/**
	 * @return The StartDate of the TimeSlot.
	 */
	public HospitalDate getStartDate() {
		return this.timeSlot_.getStartPoint().getHospitalDate();
	}

	/**
	 * @return The StopDate of the TimeSlot.
	 */
	public HospitalDate getStopDate() {
		return this.timeSlot_.getStopPoint().getHospitalDate();
	}

	/**
	 * @return The SystemTime of the Hospital.
	 */
	public HospitalDate getSystemTime() {
		return this.hospital_.getTimeKeeper().getSystemTime();
	}

	/**
	 * @return The TimeSlot of the TaskData.
	 */
	public TimeSlot getTimeSlot() {
		return this.timeSlot_;
	}

	/**
	 * Sets the Description.
	 * 
	 * @param description_
	 *            The TaskDescription.
	 */
	void setDescription(TaskDescription description) {
		this.description_ = description;
	}

	/**
	 * Sets the Location.
	 * 
	 * @param location
	 *            The Location.
	 */
	void setLocation(Location location) {
		this.location_ = location;
	}

	/**
	 * Sets the TimeSlot.
	 * 
	 * @param timeSlot
	 *            The TimeSlot.
	 */
	void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot_ = timeSlot;
	}

	/**
	 * Sets the used resources.
	 * 
	 * @param usedResources
	 *            The used resources.
	 */
	void setUsedResources(Collection<Schedulable> usedResources) {
		this.resources_ = usedResources;
	}
}