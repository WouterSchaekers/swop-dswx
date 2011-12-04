package scheduler.timetables;

import java.util.Date;

public class TimePoint implements Comparable<TimePoint>
{
	enum time_type
	{
		start, end;
	}
	public static final TimePoint L1 = new TimePoint(new Date(0), time_type.start);
	public static final TimePoint L2 = new TimePoint(new Date(3), time_type.end);

	public boolean isStart() {
		return this.type == time_type.start;
	}

	public boolean isEnd() {
		return this.type == time_type.end;
	}

	public TimePoint(Date time, time_type t) {
		this.moment = time;
		this.type = (t);
	}

	private time_type type;
	Date moment;

	@Override
	public int compareTo(TimePoint o) {
		// TODO Auto-generated method stub
		return moment.compareTo(o.moment);
	}

	public time_type getType() {
		return type;
	}

	public boolean before(TimePoint t1) {
		return this.compareTo(t1) < 0;

	}
	public String toString()
	{
		return ""+this.moment.getTime();
	}
}