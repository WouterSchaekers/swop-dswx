package scheduler;

import java.util.Comparator;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class can be used to schedule appointments. It is either a start- or a
 * stop point. Useful for finding free appointments.
 */
public class TimePoint implements Comparable<TimePoint>
{
	private TimeType type;
	private HospitalDate hospitalDate;
	public static final Comparator<TimePoint> ComparatorsStartFirst = new Comparator<TimePoint>()
	{
		@Override
		public int compare(TimePoint o1, TimePoint o2) {
			if (o1.compareTo(o2) == 0) {

				if (o1.type == TimeType.start && o2.type == TimeType.stop)
					return -1;
				if (o1.type == TimeType.stop && o2.type == TimeType.start)
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

				if (o1.type == TimeType.start && o2.type == TimeType.stop)
					return 1;
				if (o1.type == TimeType.stop && o2.type == TimeType.start)
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
	public TimePoint(HospitalDate d, TimeType t) {
		if (d == null || t == null)
			throw new IllegalArgumentException(
					"Invalid date or TimeType in constructor-call of TimePoint!");
		this.type = t;
		this.hospitalDate = d;
	}

	@Basic
	public TimeType getType() {
		return this.type;
	}

	@Basic
	public HospitalDate getDate() {
		return this.hospitalDate;
	}

	@Basic
	public long getTime() {
		return this.hospitalDate.getTotalMillis();
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
	public String toString() {
		if (this.type == TimeType.start) {
			return "Start " + this.getDate();
		} else {
			return "End " + this.getDate();
		}
	}

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
	
	public TimePoint clone(){
		return new TimePoint(this.getDate().clone(), this.getType());
	}
}
