package scheduler;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class will store 2 timepoints. It then represents the timeslot between
 * those 2 points.
 */
public class TimeSlot
{
	private TimePoint t1;
	private TimePoint t2;
	
	/**
	 * Default Constructor. Will initialise both time points.
	 * @throws illegalargumentexception
	 * 		if t1 is an endpoint or t2 is a startpoint
	 * 		if t2 is before or at the same time as t2
	 * 
	 */
	public TimeSlot(TimePoint t1,TimePoint t2){
		if(t1.isEnd()) throw new IllegalArgumentException("Invalid TimePoint 1!");
		if(t2.isStart()) throw new IllegalArgumentException("Invalid TimePoint 2!");		
		if(t1.compareTo(t2)>=0)
			throw new IllegalArgumentException("Invalid TimePoints! start >= stop!");
		this.t1=t1;
		this.t2=t2;
	}
	
	@Basic
	public TimePoint getStartPoint() {
		return t1;
	}
	
	@Basic
	public void setStartPoint(TimePoint t1) {
		this.t1 = t1;
	}
	
	@Basic
	public TimePoint getStopPoint() {
		return t2;
	}
	
	@Basic
	public void setStopPoint(TimePoint t2) {
		this.t2 = t2;
	}
	
	@Override
	public String toString()
	{
		return "[ " + t1.toString()+","+t2.toString()+" ]";
	}
	
	/**
	 * @return The length of the timespan that this timeslot covers.
	 */
	public long getLength() {
		return this.getStopPoint().getTime() - this.getStartPoint().getTime();
	}
	
	/**
	 * Checks wether this timeslot overlaps the given timeslot.
	 * Back-to-back scheduling is not considered as overlapping.
	 * 
	 * @param timeslot
	 * 			The given timeslot
	 * @return
	 * 			true if the given timeslot overlaps this timeslot
	 */
	public boolean overlaps(TimeSlot timeslot){
		TimePoint tt1 = this.getStartPoint();
		TimePoint tt2 = this.getStopPoint();
		TimePoint t1 = timeslot.getStartPoint();
		TimePoint t2 = timeslot.getStopPoint();
		return t1.isBetweenExcluding(tt1, tt2) || t2.isBetweenExcluding(tt1, tt2) || t1.equals(tt1) || t2.equals(tt2);
	}
	
	/**
	 * Checks wether the given timeSlot is a valid timeSlot.
	 * 
	 * @param timeSlot
	 * 			The timeslot that has to be checked for consistency
	 * @return
	 * 			true is the given timeslot is valid
	 */
	public static boolean isValidTimeSlot(TimeSlot timeSlot){
		if(timeSlot.getStartPoint().getType() == TimeType.stop)
			return false;
		if(timeSlot.getStopPoint().getType() == TimeType.start)
			return false;
		if(timeSlot.getStartPoint().getTime() > timeSlot.getStopPoint().getTime())
			return false;
		return true;
	}
	
	public TimeSlot clone(){
		return new TimeSlot(this.getStartPoint().clone(), this.getStopPoint().clone());
	}
	public boolean equals(Object o){
		if(!(o instanceof TimeSlot))
			return false;
		TimeSlot that = (TimeSlot)o;
		return this.t1.equals(that.t1)&&this.t2.equals(that.t2);
	}
}
