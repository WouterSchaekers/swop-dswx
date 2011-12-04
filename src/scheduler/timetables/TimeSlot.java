package scheduler.timetables;

import be.kuleuven.cs.som.annotate.Basic;

public class TimeSlot
{
	private TimePoint t1;
	private TimePoint t2;
	
	/**
	 * Default Constructor
	 */
	public TimeSlot(TimePoint t1,TimePoint t2){
		if(t1.isEnd())
			throw new IllegalArgumentException("Invalid TimePoint 1!");
		if(t2.isStart())
			throw new IllegalArgumentException("Invalid TimePoint 2!");		
		if(t1.compareTo(t2)>0)
			throw new IllegalArgumentException("Invalid TimePoints! start < stop!");
		this.t1=t1;
		this.t2=t2;
		
	}
	
	@Basic
	public TimePoint getT1() {
		return t1;
	}
	
	@Basic
	public void setT1(TimePoint t1) {
		this.t1 = t1;
	}
	
	@Basic
	public TimePoint getT2() {
		return t2;
	}
	
	@Basic
	public void setT2(TimePoint t2) {
		this.t2 = t2;
	}
	
	@Override
	public String toString()
	{
		return "[ " + t1.toString()+","+t2.toString()+" ]";
	}
}
