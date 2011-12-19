package scheduler;

/**
 * This class represents a point in time that, specifically, is the end of a
 * timeslot.
 */
public class EndTimePoint extends TimePoint
{
	/**
	 * @param d
	 * The date of this Endpoint.
	 */
	public EndTimePoint(HospitalDate d) {
		super(d);
	}

	/**
	 * @param l
	 * The amount of milliseconds since the start of time.
	 */
	public EndTimePoint(long l) {
		super(l);
	}

	/**
	 * @param t
	 * Another timepoint that serves as the base for this endpoint.
	 */
	public EndTimePoint(TimePoint t) {
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
	public TimePoint clone() {
		return new EndTimePoint(this);
	}

	@Override
	public String toString() {
		return "End: " + this.getDate();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof EndTimePoint))
			return false;
		return this.getDate().equals(((EndTimePoint) o).getDate());
	}
}
