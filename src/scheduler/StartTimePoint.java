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

	/**
	 * Returns a copy of this TimePoint.
	 */
	@Override
	public StartTimePoint clone() {
		return new StartTimePoint(this);
	}

	/**
	 * Returns true.
	 */
	@Override
	public boolean isStart() {
		return true;
	}

	/**
	 * Returns false.
	 */
	@Override
	public boolean isStop() {
		return false;
	}

	/**
	 * Returns a textual representation of the TimePoint.
	 */
	@Override
	public String toString() {
		return "Start: " + this.getHospitalDate();
	}

	/**
	 * Checks whether the given Object is a StartTimePoint and has the same
	 * HospitalDate.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StartTimePoint))
			return false;
		return this.getHospitalDate().equals(((StartTimePoint) o).getHospitalDate());
	}
}