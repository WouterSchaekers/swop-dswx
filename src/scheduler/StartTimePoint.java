package scheduler;

public class StartTimePoint extends TimePoint
{

	public StartTimePoint(HospitalDate d) {
		super(d);
	}
	public StartTimePoint(long timeInMillis)
	{
		super(timeInMillis);
	}
	public StartTimePoint(TimePoint t)
	{
		super(t);
	}

	@Override
	public TimePoint clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStart() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

}
