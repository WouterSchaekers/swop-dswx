package scheduler;

public class StartTimePoint extends TimePoint
{

	public StartTimePoint(HospitalDate d) {
		super(d);
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
