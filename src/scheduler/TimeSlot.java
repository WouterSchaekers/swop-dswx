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
	 * Default Constructor. Will initialise both time points.
	 * 
	 * @throws illegalargumentexception
	 *             if t1 is an endpoint or t2 is a startpoint if t2 is before or
	 *             at the same time as t2
	 * 
	 */
	public TimeSlot(TimePoint t1, TimePoint t2) {
		if(!isValid(t1, t2)){
			throw new IllegalArgumentException("Invalid TimeSlot.");
		}
		this.startTimePoint_ = new StartTimePoint(t1.getHospitalDate());
		this.stopTimePoint_ = new StopTimePoint(t2.getHospitalDate());
	}

	@Basic
	public StartTimePoint getStartPoint() {
		return startTimePoint_;
	}
	
	public HospitalDate getStartDate(){
		return this.startTimePoint_.getHospitalDate();
	}

	@Basic
	public void setStartPoint(TimePoint t1) {
		this.startTimePoint_ = new StartTimePoint(t1.getHospitalDate());
	}

	@Basic
	public StopTimePoint getStopPoint() {
		return stopTimePoint_;
	}
	
	public HospitalDate getStopDate(){
		return this.stopTimePoint_.getHospitalDate();
	}

	@Basic
	public void setStopPoint(TimePoint t2) {
		this.stopTimePoint_ = new StopTimePoint(t2.getHospitalDate());
	}

	@Override
	public String toString() {
		return "[ " + startTimePoint_.toString() + ","
				+ stopTimePoint_.toString() + " ]";
	}

	/**
	 * @return The length of the timespan that this timeslot covers.
	 */
	public long getLength() {
		return this.stopTimePoint_.getTime() - this.startTimePoint_.getTime();
	}
	
	public boolean contains(HospitalDate hospitalDate){
		if(this.startTimePoint_.getHospitalDate().after(hospitalDate)){
			return false;
		}
		if(hospitalDate.after(this.startTimePoint_.getHospitalDate())){
			return false;
		}
		return true;
	}

	/**
	 * Checks wether this timeslot overlaps the given timeslot. Back-to-back
	 * scheduling is not considered as overlapping.
	 * 
	 * @param timeslot
	 *            The given timeslot
	 * @return true if the given timeslot overlaps this timeslot
	 */
	public boolean overlaps(TimeSlot timeslot) {
		TimePoint t1 = timeslot.getStartPoint();
		TimePoint t2 = timeslot.getStopPoint();
		return t1.isBetweenExcluding(this.startTimePoint_, this.stopTimePoint_)
				|| t2.isBetweenExcluding(this.startTimePoint_, this.stopTimePoint_) || t1.equals(this.startTimePoint_)
				|| t2.equals(this.stopTimePoint_);
	}

	public boolean isToBack(HospitalDate hospitalDate) {
		return this.stopTimePoint_.getTime() == hospitalDate.getTimeSinceStart();
	}

	/**
	 * Checks wether the given timeSlot is a valid timeSlot.
	 * 
	 * @param timeSlot
	 *            The timeslot that has to be checked for consistency
	 * @return true is the given timeslot is valid
	 */
	private boolean isValid(TimePoint startTimePoint, TimePoint stopTimePoint) {
		if (startTimePoint.isEnd())
			return false;
		if (stopTimePoint.isStart())
			return false;
		if (startTimePoint.getTime() >= stopTimePoint.getTime())
			return false;
		return true;
	}

	public TimeSlot clone() {
		return new TimeSlot(this.getStartPoint().clone(), this.getStopPoint()
				.clone());
	}

	public boolean equals(Object o) {
		if (!(o instanceof TimeSlot))
			return false;
		TimeSlot that = (TimeSlot) o;
		return this.startTimePoint_.equals(that.startTimePoint_)
				&& this.stopTimePoint_.equals(that.stopTimePoint_);
	}

	public boolean before(TimeSlot timeSlot) {
		return this.getStartPoint().getHospitalDate().before(timeSlot.getStartPoint().getHospitalDate());
	}
}
