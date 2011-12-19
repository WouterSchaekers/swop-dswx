package scheduler;

public class EndTimePoint extends TimePoint
{

	public EndTimePoint(HospitalDate d) {
		super(d);
	}
	public EndTimePoint(long l) {
		super(l);
	}
	public EndTimePoint(TimePoint t)
	{
		super(t);
	}
	@Override
	public boolean isEnd()
	{
		return true;
	}
	@Override
	public boolean isStart()
	{
		return false;
	}
	@Override
	public TimePoint clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
