package scheduler;

/**
 * This class represents a point in time that is, specifically, the start of a
 * TimeSlot.
 */
public class StartTimePoint extends TimePoint
{
	/**
	 * Constructor where a HospitalDate is given.
	 * 
	 * @param d
	 *            The date of this time point.
	 */
	public StartTimePoint(HospitalDate d) {
		super(d);
	}

	/**
	 * Constructor where the amount of millis since the system start have been
	 * given to.
	 * 
	 * @param timeInMillis
	 *            The amount of milliseconds since the sytem start.
	 */
	public StartTimePoint(long timeInMillis) {
		super(timeInMillis);
	}

	/**
	 * Use this constructor if you would like to have this StartTimePoint be a
	 * clone of another TimePoint.(same date)
	 * 
	 * @param t
	 */
	public StartTimePoint(TimePoint t) {
		super(t);
	}

	@Override
	public StartTimePoint clone() {
		return new StartTimePoint(this);
	}

	@Override
	public boolean isStart() {
		return true;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

	@Override
	public String toString() {
		return "Start: " + this.getDate();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StartTimePoint))
			return false;
		return this.getDate().equals(((StartTimePoint) o).getDate());
	}
}
