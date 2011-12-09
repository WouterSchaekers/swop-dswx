package scheduler;

import java.util.Date;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class can be used to schedule appointments. It is either a start- or a
 * stop point. Useful for finding free appointments.
 */
public class TimePoint implements Comparable<TimePoint>
{
	private TimeType type;
	private Date date;
	
	/**
	 * Default constructor: tell the timepoint what type it is and what it's
	 * time is.
	 * @throws IllegalArgumentException 
	 * 		if the given date is null or the timetype is null.
	 */
	public TimePoint(Date d, TimeType t) {
		if(d == null || t == null)
			throw new IllegalArgumentException("Invalid date or TimeType in constructor-call of TimePoint!");
		this.type = t;
		this.date = d;
	}
	
	@Basic
	public TimeType getType() {
		return this.type;
	}

	@Basic
	public Date getDate() {
		return this.date;
	}

	@Basic
	public long getTime() {
		return this.date.getTime();
	}

	@Override
	public int compareTo(TimePoint tp2) {
		int before = -1;
		int equals = 0;
		int after = 1;
		if (this.getTime() > tp2.getTime()) {
			return after;
		} else if (this.getTime() < tp2.getTime()) {
			return before;
		} else {
			return equals;
		}
	}
	
	/**
	 * @return True if this is a start-timepoint.
	 */
	public boolean isStart() {
		return this.type == TimeType.start;
	}
	
	/**
	 * @return True if this is an end-timepoint
	 */
	public boolean isEnd() {
		return this.type == TimeType.end;
	}
	
	/**
	 * @return true if this == t
	 */
	public boolean equals(TimePoint t) {
		return t.getType().equals(this.type) && t.compareTo(this) == 0; 
	}
	
	@Override
	public String toString(){
		if(this.type == TimeType.start){
			return "Start " + this.getTime();
		}
		else{
			return "End " + this.getTime();
		}
	}
	
	/** 
	 * @return the time between this TimePoint and t.
	 */
	public int getTimeBetween(TimePoint t) {
		return (int) (t.getTime() - this.getTime());
	}
}
