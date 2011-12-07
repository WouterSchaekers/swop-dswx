package scheduler.timetables;

import java.util.*;
import scheduler.TimeType;

/***
 * A class consisting of timeslots that always have a start- and end moment.
 * The slots stored here represent timeslots from real life.
 */
public class TimeTable
{
	private TimeSlot[] timeSlots;

	public TimeTable(TimeSlot... slots) {
		this(new ArrayList<TimeSlot>(Arrays.asList(slots)));
	}

	/**
	 * Default constructor. Will initialise fields.
	 * @param slots
	 * All TimeSlots to be stored in this TimeTable.
	 */
	public TimeTable(Collection<TimeSlot> slots) {
		TimeSlot[] v = new TimeSlot[slots.size()];
		int i = 0;
		for (TimeSlot s : slots) {
			v[i++] = s;
		}
		this.timeSlots = v;
	}

	/**
	 * This method will get the intersection of this TimeTable with a lot of other TimeTables.
	 * @param table
	 * The collection of Tables you would like to get the intersection with.
	 * @return
	 * A TimeTable that's the intersection of all given tables and this table. 
	 */
	public TimeTable getIntersectionWith(Collection<TimeTable> table) {
		TimeTable rv = this.getIntersect(this);
		for (TimeTable timeTable : table) {
			rv = timeTable.getIntersect(rv);
		}
		return rv;
	}

	/**
	 * 
	 * @param time_needed
	 * @return
	 */
	public TimeTable getFirstFreeSlot(int time_needed) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param head
	 * @param line
	 * @return
	 */
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
			TimeSlot t = new TimeSlot(new TimePoint(new Date(0), TimeType.start), new TimePoint(new Date(3), TimeType.end));
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

	/**
	 * This function will calculate the intersection of this timetable with
	 * another timetable. The result is a new timetable that is marked as "busy"
	 * when both timetables are busy.
	 * 
	 * @param that
	 * The timetable which has to be intersected with this one.
	 * @return
	 * A new timetable that has all the busy-slots of both timetables.
	 */
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (TimeSlot slot : timeSlots)
			builder.append(slot.toString());
		return builder.toString();
	}

	/**
	 *@return true if t is "busy" at the same moments as this timetable.
	 */
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
