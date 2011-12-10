package scheduler;

import java.util.Comparator;
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
	public static final Comparator<TimePoint> ComparatorsStartFirst = new Comparator<TimePoint>()
	{

		@Override
		public int compare(TimePoint o1, TimePoint o2) {
			if(o1.compareTo(o2)==0)
			{

				if(o1.type==TimeType.start&&o2.type==TimeType.stop)
					return -1;
				if(o1.type==TimeType.stop&&o2.type==TimeType.start)
					return 1;
				return 0;
			}
				else
			return o1.compareTo(o2);
		}
	};
	public static final Comparator<TimePoint> ComparatorsEndFirst = new Comparator<TimePoint>()
	{

		@Override
		public int compare(TimePoint o1, TimePoint o2) {
			if(o1.compareTo(o2)==0)
			{

				if(o1.type==TimeType.start&&o2.type==TimeType.stop)
					return 1;
				if(o1.type==TimeType.stop&&o2.type==TimeType.start)
					return -1;
				return 0;
			}
				else
			return o1.compareTo(o2);
		}
	};
	
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
		return this.type == TimeType.stop;
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
	public long getTimeBetween(TimePoint t) {
		return t.getTime() - this.getTime();
	}
	
	public boolean isBetweenOrOn(TimePoint t1, TimePoint t2){
		return (this.getTime() - t1.getTime() >= 0) && (t2.getTime() - this.getTime() >= 0);
	}
	
	public boolean isBetween(TimePoint t1, TimePoint t2){
		return (this.getTime() - t1.getTime() > 0) && (t2.getTime() - this.getTime() > 0);
	}
}
