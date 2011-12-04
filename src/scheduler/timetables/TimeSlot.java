package scheduler.timetables;

public class TimeSlot
{
	private TimePoint t1;
	private TimePoint t2;
	public TimeSlot(TimePoint t1,TimePoint t2){
		if(t1.isEnd())
			throw new IllegalArgumentException();
		if(t2.isStart())
			throw new IllegalArgumentException();		
		if(t1.compareTo(t2)>1)
			throw new IllegalArgumentException();
		this.t1=t1;
		this.t2=t2;
		
	}
	public TimePoint getT1() {
		return t1;
	}
	public void setT1(TimePoint t1) {
		this.t1 = t1;
	}
	public TimePoint getT2() {
		return t2;
	}
	public void setT2(TimePoint t2) {
		this.t2 = t2;
	}
	public String toString()
	{
		return "[ " + t1.toString()+","+t2.toString()+" ]";
	}
}
