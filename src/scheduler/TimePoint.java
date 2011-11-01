package scheduler;

import java.util.Date;

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
	public TimePoint(TimeType t, Date d) {
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

}
