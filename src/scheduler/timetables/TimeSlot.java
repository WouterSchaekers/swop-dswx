package scheduler.timetables;

public class TimeSlot
{
	private TimePoint t1;
	private TimePoint t2;
	public TimeSlot(TimePoint t1,TimePoint t2){
		this.t1=t1;
		this.t2=t1;
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
}
