package scheduler;

public class StartTimePoint extends TimePoint
{

	public StartTimePoint(HospitalDate d) {
		super(d);
	}

	public StartTimePoint(long timeInMillis) {
		super(timeInMillis);
	}

	public StartTimePoint(TimePoint t) {
		super(t);
	}

	@Override
	public TimePoint clone() {
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
}
