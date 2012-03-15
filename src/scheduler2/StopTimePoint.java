package scheduler2;

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

	@Override
	public boolean isEnd() {
		return true;
	}

	@Override
	public boolean isStart() {
		return false;
	}

	@Override
	public StopTimePoint clone() {
		return new StopTimePoint(this);
	}

	@Override
	public String toString() {
		return "End: " + this.getHospitalDate();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StopTimePoint))
			return false;
		return this.getHospitalDate().equals(((StopTimePoint) o).getHospitalDate());
	}
}
