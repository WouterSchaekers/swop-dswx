package scheduler.timetables;

import java.util.*;
import scheduler.TimeType;

/**
 * A class consisting of timeslots that always have a start- and end moment. The
 * slots stored here represent timeslots from real life.
 */
public class TimeTable
{
	private TimeSlot[] timeSlots;

	/**
	 * Alternative constructor where a certain amount of slots can just be given
	 * as parameters, not as a collection.
	 */
	public TimeTable(TimeSlot... slots) {
		this(new ArrayList<TimeSlot>(Arrays.asList(slots)));
	}

	/**
	 * Default constructor. Will initialise fields.
	 * 
	 * @param slots
	 *            All TimeSlots to be stored in this TimeTable.
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
	 * This method will find the first free slot that is
	 * 
	 * @param timeNeeded
	 *            The minimal amount of time to be reserved.
	 * @return The first available timeslot in this timetable.
	 */
	public TimeSlot getFirstFreeSlot(int timeNeeded) {
		int amountOfSlots = this.timeSlots.length;
		TimeSlot[] slot = this.timeSlots;
		// Compare the start of the later timepoint to
		// the stop of the earlier ones.
		for (int i = 1; i < amountOfSlots; i++) {
			TimePoint curPoint = slot[i].getStartPoint();
			TimePoint prevPoint = slot[i - 1].getStopPoint();
			if (curPoint.getTimeBetween(prevPoint) >= timeNeeded)
				return new TimeSlot(slot[i - 1].getStopPoint(),
						slot[i].getStartPoint());
		}

		Date startDate = slot[amountOfSlots-1].getStopPoint().getDate();
		Date stopDate = new Date(slot[amountOfSlots-1].getStopPoint().getTime()
				+ timeNeeded);
		TimePoint startFree = new TimePoint(startDate, TimeType.start);
		TimePoint stopFree = new TimePoint(stopDate, TimeType.end);
		return new TimeSlot(startFree, stopFree);
	}

	/**
	 * The union of 2 timetables is this, the union of time slots, Ex : union
	 * {(1,9),(21,55)} with {(3,15)} is {(1,15),(21,55)} Thus where both
	 * Timetables are occupied. <br>
	 * <br>
	 * <b>DOES NOT REMOVE DOUBLES!!!</b>
	 * 
	 * @return
	 */
	// TODO: remove doubles? -- remove method?
	public TimeTable getUnion(TimeTable that) {
		TimePoint[] allPoints = new TimePoint[this.timeSlots.length * 2
				+ that.timeSlots.length * 2];
		ArrayList<TimeSlot> rv = new ArrayList<TimeSlot>();
		int i = 0;
		for (TimeSlot t : this.timeSlots) {
			allPoints[i++] = t.getStartPoint();
			allPoints[i++] = t.getStopPoint();
		}

		for (TimeSlot t : that.timeSlots) {
			allPoints[i++] = t.getStartPoint();
			allPoints[i++] = t.getStopPoint();
		}

		Arrays.sort(allPoints);
		i = 0;

		while (i < allPoints.length) {
			TimeSlot t = new TimeSlot(
					new TimePoint(new Date(0), TimeType.start), new TimePoint(
							new Date(3), TimeType.end));
			t.setStartPoint(allPoints[i]);
			int endcount = 1;
			while (endcount > 0) {
				i++;
				if (allPoints[i].isStart()) {
					endcount++;
				} else {
					endcount--;
				}
			}
			t.setStopPoint(allPoints[i++]);
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
	 *            The timetable which has to be intersected with this one.
	 * @return A new timetable that has all the busy-slots of both timetables.
	 */
	public TimeTable getIntersect(TimeTable that) {
		TimePoint[] one = new TimePoint[this.timeSlots.length * 2];
		TimePoint[] two = new TimePoint[that.timeSlots.length * 2];
		ArrayList<TimeSlot> rv = new ArrayList<TimeSlot>();
		int i = 0;
		for (TimeSlot t : this.timeSlots) {
			one[i++] = t.getStartPoint();
			one[i++] = t.getStopPoint();
		}
		i = 0;
		for (TimeSlot t : that.timeSlots) {
			two[i++] = t.getStartPoint();
			two[i++] = t.getStopPoint();
		}
		Arrays.sort(one);
		Arrays.sort(two);
		int first = 0;
		int second = 0;
		while (first < one.length - 1 && second < two.length - 1) {
			while (first < one.length - 1
					&& second < two.length - 1
					&& (!(one[first].compareTo(two[second]) <= 0 && one[first + 1]
							.compareTo(two[second]) >= 0)
					&& !(two[second].compareTo(one[first]) <= 0 && two[second + 1]
							.compareTo(one[first]) >= 0))) {
				if (one[first].compareTo(two[second]) > 0) {
					second = second + 2;
				} else {
					first = first + 2;
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

	/**
	 * This method will get the intersection of this TimeTable with a lot of
	 * other TimeTables.
	 * 
	 * @param tables
	 *            The collection of Tables you would like to get the
	 *            intersection with.
	 * @return A TimeTable that's the intersection of all given tables and this
	 *         table.
	 */
	public TimeTable intersectAll(Collection<TimeTable> tables) {
		TimeTable rv = this.getIntersect(this);
		for (TimeTable timeTable : tables)
			rv = timeTable.getIntersect(rv);

		return rv;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (TimeSlot slot : timeSlots)
			builder.append(slot.toString());
		return builder.toString();
	}

	/**
	 * @return true if t is "busy" at the same moments as this timetable.
	 */
	public boolean equals(TimeTable t) {
		if (this.timeSlots.length != t.timeSlots.length)
			return false;
		for (int i = 0; i < t.timeSlots.length; i++) {
			boolean t1Cond = this.timeSlots[i].getStartPoint().equals(
					t.timeSlots[i].getStartPoint());
			boolean t2Cond = this.timeSlots[i].getStopPoint().equals(
					t.timeSlots[i].getStopPoint());
			if (!(t1Cond && t2Cond))
				return false;
		}
		return true;
	}
}
