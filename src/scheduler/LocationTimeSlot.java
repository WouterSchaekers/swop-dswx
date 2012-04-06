package scheduler;

import system.Location;

/**
 * This class will store 2 timepoints and a location.
 */
public class LocationTimeSlot extends TimeSlot
{
	private Location location_;

	/**
	 * Default constructor. It will create a LocationTimeSlot, containing the
	 * given two TimePoints and location.
	 * 
	 * @param t1
	 *            The startTimePoint of this TimeSlot.
	 * @param t2
	 *            The stopTimePoint of this TimeSlot.
	 * @param location
	 *            The Location of this TimeSlot.
	 */
	public LocationTimeSlot(TimePoint t1, TimePoint t2, Location location) {
		super(t1, t2);
		this.location_ = location;
	}

	/**
	 * Constructor. It will create a LocationTimeSlot, containing the given
	 * TimeSlot and Location.
	 * 
	 * @param timeSlot
	 *            The TImeSlot of this LocationTimeSlot.
	 * @param location
	 *            The Location of this TimeSlot.
	 */
	public LocationTimeSlot(TimeSlot timeSlot, Location location) {
		this(timeSlot.getStartPoint(), timeSlot.getStopPoint(), location);
	}

	/**
	 * @return The Location of this LocationTimeSlot.
	 */
	public Location getLocation() {
		return this.location_;
	}

	/**
	 * Checks whether this TimeSlot contains a given HospitalDate.
	 * 
	 * @param hospitalDate
	 *            The HosptialDate that will be checked.
	 * @return True if the given HospitalDate falls after the StartDate and
	 *         before the StopDate or when the given HospitalDate falls on the
	 *         StartDate of this LocationTimeSlot.
	 */
	@Override
	public boolean contains(HospitalDate hospitalDate) {
		return hospitalDate.equals(this.getStartDate())
				|| (hospitalDate.before(this.getStopDate()) && hospitalDate.after(this.getStartDate()));
	}
	
	/**
	 * Returns a copy of this LocationTimeSlot.
	 */
	@Override
	public LocationTimeSlot clone() {
		return new LocationTimeSlot(this.getStartPoint().clone(), this.getStopPoint().clone(), this.getLocation());
	}
}