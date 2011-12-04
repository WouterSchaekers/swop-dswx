package scheduler.timetables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/***
 * \ A class consisting of timeslots that always have a start and and end moment
 * The slots stored here are time segments that are taken. The union of 2 time
 * tables is the union of the collections of time slots.
 * 
 * @author Dieter
 * 
 */
public class TimeTable
{
	private TimeSlot[] timeSlots;

	public TimeTable(TimeSlot... slots) {
		TimeSlot[] v = new TimeSlot[slots.length];
		for (int i = 0; i < v.length; i++) {
			v[i] = slots[i];
		}
		this.timeSlots = v;
	}

	public TimeTable(Collection<TimeSlot> slots) {
		TimeSlot[] v = new TimeSlot[slots.size()];
		int i = 0;
		for (TimeSlot s : slots) {
			v[i++] = s;
		}
		this.timeSlots = v;
	}

	public TimeTable getIntersectionWith(Collection<TimeTable> table) {
		return null;

	}

	public TimeTable getFirstFreeSlot(int time_needed) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Collection<TimeTable> intersectAll(
			Collection<TimeTable> head, TimeTable line) {
		// TODO Auto-generated method stub
		return null;
	}

	public TimeTable getIntersect(TimeTable line) {

		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * The union of 2 timetables is this, the union of time slots, Ex : union
	 * {(1,9),(21,55)} with {(3,15)} is {(1,15),(21,55)} Thus where both
	 * Timetables are occupied.
	 * 
	 * @return
	 */
	public TimeTable getUnion(TimeTable that) {
		TimePoint[] allPoints = new TimePoint[this.timeSlots.length*2
				+ that.timeSlots.length*2 ];
		ArrayList<TimeSlot> rv = new ArrayList<TimeSlot>();
		int i = 0;
		for (TimeSlot t : this.timeSlots) {
			allPoints[i++] = t.getT1();
			allPoints[i++] = t.getT2();
		}
		for (TimeSlot t : that.timeSlots) {
			allPoints[i++] = t.getT1();
			allPoints[i++] = t.getT2();
		}
		Arrays.sort(allPoints);
		i = 0;
		while (i < allPoints.length) {
			TimeSlot t = new TimeSlot(TimePoint.L1, TimePoint.L2);
			t.setT1(allPoints[i]);
			int endcount = 1;
			while (endcount > 0) {
				i++;
				if (allPoints[i].isStart()) {
					endcount++;
				} else {
					endcount--;
				}
			}
			t.setT2(allPoints[i++]);
			rv.add(t);
		}
		return new TimeTable(rv);
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for(TimeSlot slot : timeSlots)
			builder.append(slot.toString());
		return builder.toString();
	}
}
