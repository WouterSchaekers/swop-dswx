package scheduler.timetables;

import java.util.Date;
import scheduler.TimeType;

/**
 * This class can be used to schedule appointments. It is either a start- or a
 * stop point. Useful for finding free appointments.
 */
public class TimePoint implements Comparable<TimePoint>
{
	private static long count = 0;
	private long id;
	private TimeType type;
	private Date date;

	long getid() {
		return id;
	};

	/**
	 * Default constructor: tell the timepoint what type it is and what it's
	 * time is.
	 */
	public TimePoint(Date d, TimeType t) {
		this.type = t;
		this.date = d;
		id = count++;
	}

	/**
	 * @return The type of this timepoint.
	 */
	public TimeType getType() {
		return this.type;
	}

	/**
	 * @return The time of this timepoint.
	 */
	public Date getDate() {
		return this.date;
	}

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
	
	public boolean isStart() {
		return this.type == TimeType.start;
	}
	
	public boolean isEnd() {
		return this.type == TimeType.end;
	}
	
	public boolean equals(TimePoint t) {
		return t.getType().equals(this.type) && t.compareTo(this) == 0; 
	}

}
