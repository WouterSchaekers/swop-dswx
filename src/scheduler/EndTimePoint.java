package scheduler;

public class EndTimePoint extends TimePoint
{

	public EndTimePoint(HospitalDate d) {
		super(d);
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
