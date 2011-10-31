package scheduler;

import java.util.Date;

/**
 * This class can be used to schedule appointments. It is either a start- or a
 * stop point. Useful for finding free appointments.
 */
public class TimePoint
{
	/**
	 * Default constructor: tell the timepoint what type it is and what it's time is.
	 */
	public TimePoint(TimeType t, Date d) {
		this.type = t;
		this.date = d;
	}

	private TimeType type;
	private Date date;

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

}
