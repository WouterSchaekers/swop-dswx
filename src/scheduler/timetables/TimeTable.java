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
		TimePoint[] allPoints = new TimePoint[this.timeSlots.length * 2
				+ that.timeSlots.length * 2];
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

	public TimeTable intersect(TimeTable that) {
		TimePoint[] one = new TimePoint[this.timeSlots.length * 2];
		TimePoint[] two = new TimePoint[that.timeSlots.length * 2];
		ArrayList<TimeSlot> rv = new ArrayList<TimeSlot>();
		int i = 0;
		for (TimeSlot t : this.timeSlots) {
			one[i++] = t.getT1();
			one[i++] = t.getT2();
		}
		i = 0;
		for (TimeSlot t : that.timeSlots) {
			two[i++] = t.getT1();
			two[i++] = t.getT2();
		}
		i = 0;
		int _1 = 0;
		int _2 = 0;
		while (_1 < one.length - 1) {
			while (one[_1].compareTo(two[_2]) > 1 && _2 < two.length - 1) {
				_2 = _2 + 2;
			}
			TimePoint startPoint = two[_2];
			TimePoint endPoint;
			if (one[_1 + 1].compareTo(two[_2 + 1]) > 1) {
				endPoint = two[_2 + 1];
				_2 = _2 + 2;
			} else {
				endPoint = one[_1 + 1];
				_1 = _1 + 2;
			}
			TimeSlot t = new TimeSlot(startPoint, endPoint);
			rv.add(t);
		}
		return new TimeTable(rv);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (TimeSlot slot : timeSlots)
			builder.append(slot.toString());
		return builder.toString();
	}
	
	public boolean equals(TimeTable t) {
		for (int i = 0; i <  t.timeSlots.length;i++) {
			if (!(this.timeSlots[i].getT1().toString().equals(t.timeSlots[i].getT1().toString())) && this.timeSlots[i].getT2().toString().equals(t.timeSlots[i].getT2().toString()))
			return false;
		}
		return true;
	}
}
