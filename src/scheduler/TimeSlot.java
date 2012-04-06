package scheduler;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class will store 2 timepoints. It then represents the timeslot between
 * those 2 points.
 */
public class TimeSlot
{
	private StartTimePoint startTimePoint_;
	private StopTimePoint stopTimePoint_;

	/**
	 * Default constructor. It will create a timeSlot, containing the given two
	 * TimePoints.
	 * 
	 * @param t1
	 *            The startTimePoint of this TimeSlot.
	 * @param t2
	 *            The stopTimePoint of this TimeSlot.
	 */
	public TimeSlot(TimePoint t1, TimePoint t2) {
		if (!isValid(t1, t2)) {
			throw new IllegalArgumentException("Invalid TimeSlot.");
		}
		this.startTimePoint_ = new StartTimePoint(t1.getHospitalDate());
		this.stopTimePoint_ = new StopTimePoint(t2.getHospitalDate());
	}

	/**
	 * @return The StartTimePoint.
	 */
	@Basic
	public StartTimePoint getStartPoint() {
		return this.startTimePoint_;
	}

	/**
	 * @return The HospitalDate of the startTimePoint.
	 */
	public HospitalDate getStartDate() {
		return this.startTimePoint_.getHospitalDate();
	}

	/**
	 * Sets the StopTimePoint of the TimeSlot.
	 * 
	 * @param t1
	 *            The StopPoint of this TimeSlot.
	 */
	@Basic
	void setStartPoint(TimePoint t1) {
		this.startTimePoint_ = new StartTimePoint(t1.getHospitalDate());
	}

	/**
	 * @return The StopTimePoint.
	 */
	@Basic
	public StopTimePoint getStopPoint() {
		return stopTimePoint_;
	}

	/**
	 * @return The HospitalDate of the StopTimePoint.
	 */
	public HospitalDate getStopDate() {
		return this.stopTimePoint_.getHospitalDate();
	}

	/**
	 * Sets the StopTimePoint of the TimeSlot.
	 * 
	 * @param t2
	 *            The StopTimePoint of this TimeSlot.
	 */
	@Basic
	void setStopPoint(TimePoint t2) {
		this.stopTimePoint_ = new StopTimePoint(t2.getHospitalDate());
	}

	/**
	 * Returns a textual representation of the TimeSlot.
	 */
	@Override
	public String toString() {
		return "[ " + startTimePoint_.toString() + "," + stopTimePoint_.toString() + " ]";
	}

	/**
	 * @return The length of the timespan that this timeslot covers.
	 */
	public long getLength() {
		return this.stopTimePoint_.getTime() - this.startTimePoint_.getTime();
	}

	/**
	 * Checks wether this timeslot overlaps the given timeslot. Back-to-back
	 * scheduling is not considered as overlapping.
	 * 
	 * @param timeslot
	 *            The given TimeSlot.
	 * @return True if the given TimeSlot overlaps this TimeSlot.
	 */
	public boolean overlaps(TimeSlot timeslot) {
		TimePoint t1 = timeslot.getStartPoint();
		TimePoint t2 = timeslot.getStopPoint();
		return t1.isBetweenExcluding(this.startTimePoint_, this.stopTimePoint_)
				|| t2.isBetweenExcluding(this.startTimePoint_, this.stopTimePoint_) || t1.equals(this.startTimePoint_)
				|| t2.equals(this.stopTimePoint_);
	}

	/**
	 * Returns true if the given hospitalDate is back to back with this
	 * TimeSlot.
	 * 
	 * @param hospitalDate
	 *            The HospitalDate that will be checked whether this TimeSlot is
	 *            back to back with.
	 * @return True if the given HospitalDate is back to back with this
	 *         TimeSlot.
	 */
	public boolean isToBack(HospitalDate hospitalDate) {
		return this.stopTimePoint_.getTime() == hospitalDate.getTimeSinceStart();
	}

	/**
	 * Checks wether the given timeSlot is a valid timeSlot.
	 * 
	 * @param timeSlot
	 *            The TimeSlot that has to be checked for consistency
	 * @return true is the given timeslot is valid
	 */

	/**
	 * Checks wether the given TimePoints can form a valid TimeSlot.
	 * 
	 * @param startTimePoint
	 *            The StartTimePoint of the possible TimeSlot.
	 * @param stopTimePoint
	 *            The StopTimePoint of the possible TimeSlot.
	 * @return True if the TimePoints are valid and the StopTimePoint is after
	 *         the StartTimePoint.
	 */
	private boolean isValid(TimePoint startTimePoint, TimePoint stopTimePoint) {
		if (startTimePoint.isStop())
			return false;
		if (stopTimePoint.isStart())
			return false;
		if (startTimePoint.getTime() >= stopTimePoint.getTime())
			return false;
		return true;
	}

	/**
	 * Returns a copy of this TimeSlot.
	 */
	@Override
	public TimeSlot clone() {
		return new TimeSlot(this.getStartPoint().clone(), this.getStopPoint().clone());
	}

	/**
	 * Checks whether the given Object is equal to this TimeSlot.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TimeSlot))
			return false;
		TimeSlot that = (TimeSlot) o;
		return this.startTimePoint_.equals(that.startTimePoint_) && this.stopTimePoint_.equals(that.stopTimePoint_);
	}

	/**
	 * Checks whether this TimeSlot is before a given TimeSlot.
	 * 
	 * @param timeSlot
	 *            The TimeSlot that this TimeSlot will be compared with.
	 * @return True if the StartTimePoint of this TimeSlot falls before the
	 *         StartTimePoint of the given TimeSlot.
	 */
	public boolean before(TimeSlot timeSlot) {
		return this.getStartPoint().getHospitalDate().before(timeSlot.getStartPoint().getHospitalDate());
	}

	/**
	 * Checks whether this TimeSlot contains a given HospitalDate.
	 * 
	 * @param date
	 *            The HosptialDate that will be checked.
	 * @return True if the given HospitalDate falls after the StartDate and
	 *         before the StopDate.
	 */
	public boolean contains(HospitalDate date) {
		return date.after(this.getStartDate()) && date.before(this.getStopDate());
	}
}