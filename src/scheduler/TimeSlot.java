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
		if(t1.compareTo(t2)>=0) throw new IllegalArgumentException("Invalid TimePoints! start > stop!");
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
	
	public boolean overlaps(TimeSlot t){
		TimePoint tt1 = this.getStartPoint();
		TimePoint tt2 = this.getStopPoint();
		TimePoint t1 = t.getStartPoint();
		TimePoint t2 = t.getStopPoint();
		return t1.isBetween(tt1, tt2) || t2.isBetween(tt1, tt2) || t1.equals(tt1) || t2.equals(tt2);
	}
	
}
