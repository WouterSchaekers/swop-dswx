package scheduler.timetables;

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
	 */
	public TimeSlot(TimePoint t1,TimePoint t2){
		if(t1.isEnd()) throw new IllegalArgumentException("Invalid TimePoint 1!");
		if(t2.isStart()) throw new IllegalArgumentException("Invalid TimePoint 2!");		
		if(t1.compareTo(t2)>0) throw new IllegalArgumentException("Invalid TimePoints! start < stop!");
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
	
	public long getLength() {
		return this.getStopPoint().getTime() - this.getStartPoint().getTime();
	}
	
}
