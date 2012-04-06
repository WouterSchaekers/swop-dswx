package scheduler;

import java.util.Comparator;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class can be used to schedule appointments. It is either a start- or a
 * stop point. Useful for finding free scheduling slots.
 */
public abstract class TimePoint implements Comparable<TimePoint>
{
	private HospitalDate hospitalDate;
	/**
	 * This comparator compares 2 Time Points where this timepoint is a
	 * starttimepoint.
	 */
	public static final Comparator<TimePoint> ComparatorsStartFirst = new Comparator<TimePoint>()
	{
		@Override
		public int compare(TimePoint o1, TimePoint o2) {
			if (o1.compareTo(o2) == 0) {
				if (o1.isStart() && o2.isStop())
					return 1;
				if (o1.isStop() && o2.isStart())
					return -1;
				return 0;
			} else
				return o1.compareTo(o2);
		}
	};
	/**
	 * This comparator compares 2 Time Points where
	 */
	public static final Comparator<TimePoint> ComparatorsEndFirst = new Comparator<TimePoint>()
	{
		@Override
		public int compare(TimePoint o1, TimePoint o2) {
			if (o1.compareTo(o2) == 0) {
				if (o1.isStart() && o2.isStop())
					return -1;
				if (o1.isStop() && o2.isStart())
					return 1;
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
		if (d == null)
			throw new IllegalArgumentException("Invalid date or TimeType in constructor-call of TimePoint!");

		this.hospitalDate = d;
	}

	/**
	 * Constructor where amount of millis since system start can be given to.
	 * 
	 * @param timeInMillis
	 *            Amount of millis since system start.
	 */
	protected TimePoint(long timeInMillis) {
		this(new HospitalDate(timeInMillis));
	}

	/**
	 * Constructor where another TimePoint is given that results in this
	 * TimePoint being a clone of the given one.
	 * 
	 * @param t
	 *            The TimePoint on which this TimePoint is to be based on.
	 */
	protected TimePoint(TimePoint t) {
		this(t.getHospitalDate());
	}

	/**
	 * @return The HospitalDate of the TimePoint.
	 */
	@Basic
	public HospitalDate getHospitalDate() {
		return this.hospitalDate;
	}

	/**
	 * @return The time of the HospitalDate.
	 */
	public long getTime() {
		return this.hospitalDate.getTimeSinceStart();
	}

	/**
	 * Compares this TimePoint with the given TimePoint.
	 */
	@Override
	public int compareTo(TimePoint tp2) {
		if (this.getTime() > tp2.getTime())
			return 1;
		else if (this.getTime() < tp2.getTime())
			return -1;
		else
			return 0;
	}

	/**
	 * @return True if this is a StartTimepoint.
	 */
	public abstract boolean isStart();

	/**
	 * @return True if this is an StopTimepoint
	 */
	public abstract boolean isStop();

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
		return (this.getTime() - before.getTime() > 0) && (after.getTime() - this.getTime() > 0);
	}

	/**
	 * Checks wether this timepoint lies between the two other timepoints,
	 * including the first point, but excluding the last point.
	 * 
	 * @param timePoint
	 *            The timepoint that has to lie between the other two timepoints
	 * @param before
	 *            The timepoint that has to lie before or on the first timepoint
	 * @param after
	 *            The timepoint that has to lie behind the first timepoint
	 * @return True if the first timepoint lies between the other two timepoints
	 *         or on the first point
	 */
	public boolean isBetweenExcludingEndPoint(TimePoint before, TimePoint after) {
		return (this.getTime() - before.getTime() >= 0) && (after.getTime() - this.getTime() > 0);
	}

	/**
	 * Returns a textual representation of the TimePoint.
	 */
	@Override
	public abstract String toString();

	/**
	 * Clones the TimePoint.
	 */
	@Override
	public abstract TimePoint clone();
}