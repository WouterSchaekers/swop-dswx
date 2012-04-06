package scheduler;

/**
 * This class represents a point in time that, specifically, is the end of a
 * timeslot.
 */
public class StopTimePoint extends TimePoint
{
	/**
	 * @param d
	 *            The date of this Endpoint.
	 */
	public StopTimePoint(HospitalDate d) {
		super(d);
	}

	/**
	 * @param l
	 *            The amount of milliseconds since the start of time.
	 */
	public StopTimePoint(long l) {
		super(l);
	}

	/**
	 * @param t
	 *            Another timepoint that serves as the base for this endpoint.
	 */
	public StopTimePoint(TimePoint t) {
		super(t);
	}

	/**
	 * Returns true.
	 */
	@Override
	public boolean isStop() {
		return true;
	}

	/**
	 * Returns false.
	 */
	@Override
	public boolean isStart() {
		return false;
	}

	/**
	 * Returns a copy of the given TimePoint.
	 */
	@Override
	public StopTimePoint clone() {
		return new StopTimePoint(this);
	}

	/**
	 * Returns a textual representation of this TimePoint.
	 */
	@Override
	public String toString() {
		return "End: " + this.getHospitalDate();
	}

	/**
	 * Checks whether the given Object is a StopTimePoint and has the same
	 * HospitalDate.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StopTimePoint))
			return false;
		return this.getHospitalDate().equals(((StopTimePoint) o).getHospitalDate());
	}
}