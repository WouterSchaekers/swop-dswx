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

	public TimeTable getIntersect(TimeTable that) {
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
		int first = 0;
		int second = 0;
		while (first < one.length - 1 && second < two.length - 1) {
			while (first < one.length - 1 && second < two.length - 1) {
				if (!(one[first].compareTo(two[second]) < 0 && one[first + 1]
						.compareTo(two[second]) > 0)
						|| !(two[second].compareTo(one[first]) < 0 && two[second + 1]
								.compareTo(one[first]) > 0)) {
					if (one[first].compareTo(two[second]) > 0) {
						second = second + 2;
					} else {
						first = first + 1;
					}
				}
			}
			if (!(first < one.length - 1 && second < two.length - 1)) {
				break;
			}
			TimePoint startPoint;
			if (one[first].compareTo(two[second]) > 0) {
				startPoint = one[first];
			} else {
				startPoint = two[second];
			}
			TimePoint endPoint;
			if (one[first + 1].compareTo(two[second + 1]) > 0) {
				endPoint = two[second + 1];
				second = second + 2;
			} else {
				endPoint = one[first + 1];
				first = first + 2;
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
		for (int i = 0; i < t.timeSlots.length; i++) {
			boolean t1Cond = this.timeSlots[i].getT1().toString()
					.equals(t.timeSlots[i].getT1().toString());
			boolean t2Cond = this.timeSlots[i].getT2().toString()
					.equals(t.timeSlots[i].getT2().toString());
			if (!(t1Cond && t2Cond))
				return false;
		}
		return true;
	}
}
