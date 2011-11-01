package scheduler;

import java.util.Date;

/**
 * This class can be used to schedule appointments. It is either a start- or a
 * stop point. Useful for finding free appointments.
 */
public class TimePoint implements Comparable<TimePoint>
{
	private TimeType type;
	private Date date;
	private Date creationDate;
	
	/**
	 * Default constructor: tell the timepoint what type it is and what it's time is.
	 */
	public TimePoint(TimeType t, Date d, Date creationDate) {
		this.type = t;
		this.date = d;
		this.creationDate = creationDate;
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
	
	public long getTime(){
		return this.date.getTime();
	}
	
	public Date getCreationDate(){
		return creationDate;
	}

	@Override
	public int compareTo(TimePoint tp2) {
		int before = -1;
		int equals = 0;
		int after = 1;
		if(this.getTime() >  tp2.getTime()){
			return after;
		}
		else if(this.getTime() <  tp2.getTime()){
			return before;
		}
		else if(this.getCreationDate().getTime() > tp2.getCreationDate().getTime()){
			return after;
		}
		else if(this.getCreationDate().getTime() < tp2.getCreationDate().getTime()){
			return before;
		}
		else{
			return equals;
		}
	}

}
