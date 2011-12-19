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
		return new EndTimePoint(this);
	}
	@Override
	public String toString() {
		return "End: " + this.getDate();
	}
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof EndTimePoint))
			return false;
		return this.getDate().equals(((StartTimePoint)o).getDate());
	}
}
