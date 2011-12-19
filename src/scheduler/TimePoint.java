package scheduler;

import java.util.Comparator;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class can be used to schedule appointments. It is either a start- or a
 * stop point. Useful for finding free appointments.
 */
public abstract class TimePoint implements Comparable<TimePoint>
{
	private HospitalDate hospitalDate;
	public static final Comparator<TimePoint> ComparatorsStartFirst = new Comparator<TimePoint>()
	{
		@Override
		public int compare(TimePoint o1, TimePoint o2) {
			if (o1.compareTo(o2) == 0) {
				if (o1 instanceof StartTimePoint && o2 instanceof EndTimePoint)
					return -1;
				if (o1 instanceof EndTimePoint && o2 instanceof StartTimePoint)
					return 1;
				return 0;
			} else
				return o1.compareTo(o2);
		}
	};
	public static final Comparator<TimePoint> ComparatorsEndFirst = new Comparator<TimePoint>()
	{
		@Override
		public int compare(TimePoint o1, TimePoint o2) {
			if (o1.compareTo(o2) == 0) {

				if (o1 instanceof StartTimePoint&& o2 instanceof EndTimePoint)
					return 1;
				if (o1 instanceof EndTimePoint&& o2 instanceof StartTimePoint)
					return -1;
				return 0;
			} else
				return o1.compareTo(o2);
		}
	};

	/**
	 * Default constructor: tell the timepoint what type it is and what it's
	 * time is.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given date is null or the timetype is null.
	 */
	protected TimePoint(HospitalDate d) {
		if (d == null )
			throw new IllegalArgumentException(
					"Invalid date or TimeType in constructor-call of TimePoint!");
	
		this.hospitalDate = d;
	}
	
	//TODO
	protected TimePoint(long timeInMillis ){
		this(new HospitalDate(timeInMillis));
	}
	
	protected TimePoint(TimePoint t){
		this(t.getDate());
	}

	
	@Basic
	public HospitalDate getDate() {
		return this.hospitalDate;
	}

	@Basic
	public long getTime() {
		return this.hospitalDate.getTimeSinceStart();
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
	public abstract boolean isStart(); 

	/**
	 * @return True if this is an end-timepoint
	 */
	public abstract boolean isEnd(); 

	/**
	 * @return the time between this TimePoint and t.
	 */
	public long getTimeBetween(TimePoint t) {
		return t.getTime() - this.getTime();
	}

	/**
	 * Checks wether this timepoint lies between the two other timepoints.
	 * 
	 * @param timePoint
	 *            The timepoint that has to lie between the other two timepoints
	 * @param before
	 *            The timepoint that has to lie before the first timepoint
	 * @param after
	 *            The timepoint that has to lie behind the first timepoint
	 * @return True if the first timepoint lies between the other two timepoints
	 */
	public boolean isBetweenExcluding(TimePoint before, TimePoint after) {
		return (this.getTime() - before.getTime() > 0)
				&& (after.getTime() - this.getTime() > 0);
	}

	/**
	 * Checks wether this timepoint lies between the two other timepoints, including the first point, but excluding the last point.
	 * 
	 * @param timePoint
	 *            The timepoint that has to lie between the other two timepoints
	 * @param before
	 *            The timepoint that has to lie before or on the first timepoint
	 * @param after
	 *            The timepoint that has to lie behind the first timepoint
	 * @return True if the first timepoint lies between the other two timepoints or on the first point
	 */
	public boolean isBetweenExcludingEndPoint(TimePoint before, TimePoint after) {
		return (this.getTime() - before.getTime() >= 0)
				&& (after.getTime() - this.getTime() > 0);
	}
	
	public abstract String toString();
	
	public abstract TimePoint clone();
	
}
