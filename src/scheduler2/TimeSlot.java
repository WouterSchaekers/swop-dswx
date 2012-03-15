package scheduler2;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class will store 2 timepoints. It then represents the timeslot between
 * those 2 points.
 */
public class TimeSlot
{
	private StartTimePoint _startTimePoint;
	private StopTimePoint _stopTimePoint;

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
		this._startTimePoint = new StartTimePoint(t1.getHospitalDate());
		this._stopTimePoint = new StopTimePoint(t2.getHospitalDate());
	}

	@Basic
	public StartTimePoint getStartPoint() {
		return _startTimePoint;
	}

	@Basic
	public void setStartPoint(TimePoint t1) {
		this._startTimePoint = new StartTimePoint(t1.getHospitalDate());
	}

	@Basic
	public StopTimePoint getStopPoint() {
		return _stopTimePoint;
	}

	@Basic
	public void setStopPoint(TimePoint t2) {
		this._stopTimePoint = new StopTimePoint(t2.getHospitalDate());
	}

	@Override
	public String toString() {
		return "[ " + _startTimePoint.toString() + ","
				+ _stopTimePoint.toString() + " ]";
	}

	/**
	 * @return The length of the timespan that this timeslot covers.
	 */
	public long getLength() {
		return this._stopTimePoint.getTime() - this._startTimePoint.getTime();
	}
	
	public boolean contains(HospitalDate hospitalDate){
		if(this._startTimePoint.getHospitalDate().after(hospitalDate)){
			return false;
		}
		if(hospitalDate.after(this._startTimePoint.getHospitalDate())){
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
		return t1.isBetweenExcluding(this._startTimePoint, this._stopTimePoint)
				|| t2.isBetweenExcluding(this._startTimePoint, this._stopTimePoint) || t1.equals(this._startTimePoint)
				|| t2.equals(this._stopTimePoint);
	}

	public boolean isToBack(HospitalDate hospitalDate) {
		return this._stopTimePoint.getTime() == hospitalDate.getTimeSinceStart();
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
		return this._startTimePoint.equals(that._startTimePoint)
				&& this._stopTimePoint.equals(that._stopTimePoint);
	}
}
