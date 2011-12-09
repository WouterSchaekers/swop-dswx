package scheduler;

import java.util.*;
import be.kuleuven.cs.som.annotate.Basic;

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
	 * Default constructor. Will initialise all fields.
	 * 
	 * @param slots
	 *            All TimeSlots to be stored in this TimeTable.
	 */
	public TimeTable(Collection<TimeSlot> slots) {
		this.timeSlots = new TimeSlot[slots.size()];
		int i = 0;
		for (TimeSlot s : slots) {
			this.timeSlots[i++] = s;
		}
	}

	public TimeSlot[] getArrayTimeSlots() {
		return this.timeSlots;
	}

	/**
	 * This method will find the first free slot that is available in this
	 * timetable with the required length.
	 * 
	 * @param timeNeeded
	 *            The minimal amount of time to be reserved.
	 * @return The first available timeslot in this timetable.
	 */
	public TimeSlot getFirstFreeSlot(long timeNeeded) {
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
		// everything has been scheduled from back-to-back
		// give a new timeslot from the end of this timetable.
		return getLastSlotWithLength(timeNeeded);

	}

	/**
	 * This method returns all free slots in this TimeTable starting from a
	 * certain point in time with a certain minimal length.
	 * 
	 * @param length
	 *            The minimal length of the required free slot.
	 * @param time
	 *            The point in time from which to start looking from.
	 * @return A TimeTable that contains all free slots of this TimeTable.
	 */
	public TimeTable getFreeTimeSlotsFrom(Date time, long length) {
		int amountOfSlots = this.timeSlots.length;
		Collection<TimeSlot> returnValue = new ArrayList<TimeSlot>(
				amountOfSlots);
		TimeSlot[] slots = this.timeSlots;

		// Compare the start of the later timepoint to
		// the stop of the earlier ones.
		for (int i = 1; i < amountOfSlots; i++) {
			TimePoint curPoint = slots[i].getStartPoint();
			TimePoint prevPoint = slots[i - 1].getStopPoint();
			if (curPoint.getTime() >= time.getTime()
					&& prevPoint.getTime() >= time.getTime()) {
				// the current timeslot meets the time-requirement
				if (curPoint.getTimeBetween(prevPoint) >= length) {
					// the current timeslot meets all requirements and
					// can thus be added to the returnvalue
					TimePoint t1 = new TimePoint(prevPoint.getDate(),
							prevPoint.getType());
					TimePoint t2 = new TimePoint(curPoint.getDate(),
							curPoint.getType());
					returnValue.add(new TimeSlot(t1, t2));
				}
			}
		}
		// All scheduled timeslots have been iterated over.
		// We now need can add a slot at the end of the current timeline aswell
		// and then return everything in a new TimeTable.
		returnValue.add(getLastSlotWithLength(length));

		return new TimeTable(returnValue);
	}

	/**
	 * This method returns all free slots in this TimeTable with a certain
	 * minimal certain length.
	 * 
	 * @param length
	 *            The minimal length of the required free slot.
	 * @param time
	 *            The point in time from which to start looking from.
	 * @return A TimeTable that contains all free slots of this TimeTable.
	 */
	public TimeTable getAllFreeSlots(long length) {
		int amountOfSlots = this.timeSlots.length;
		Collection<TimeSlot> returnValue = new ArrayList<TimeSlot>(
				amountOfSlots);
		TimeSlot[] slots = this.timeSlots;

		for (int i = 1; i < amountOfSlots; i++) {
			TimePoint curPoint = slots[i].getStartPoint();
			TimePoint prevPoint = slots[i - 1].getStopPoint();
			if (curPoint.getTimeBetween(prevPoint) >= length) {
				// the current timeslot meets all requirements and
				// can thus be added to the returnvalue
				TimePoint t1 = new TimePoint(prevPoint.getDate(),
						prevPoint.getType());
				TimePoint t2 = new TimePoint(curPoint.getDate(),
						curPoint.getType());
				returnValue.add(new TimeSlot(t1, t2));
			}
		}
		// All scheduled timeslots have been iterated over.
		// We now need can add a slot at the end of the current timeline aswell
		// and then return everything in a new TimeTable.
		returnValue.add(getLastSlotWithLength(length));

		return new TimeTable(returnValue);
	}

	/**
	 * This method will check whether or not this TimeTable has a free slot at
	 * the given parameter slot.
	 * 
	 * @param slotToCheck
	 *            The TimeSlot to be checked for.
	 * @return True if this TimeTable is free for the complete given TimeSlot.
	 */
	public boolean hasFreeSlotAt(TimeSlot slotToCheck) {
		TimeTable freeSlotsTable = this
				.getAllFreeSlots(slotToCheck.getLength());
		Collection<TimeSlot> slots = freeSlotsTable.getTimeSlots();

		for (TimeSlot thisSlot : slots)
			if (thisSlot.containsSlot(slotToCheck))
				return true;

		return false;
	}

	/**
	 * The union of 2 timetables is this, the union of time slots, Ex : union
	 * {(1,9),(21,55)} with {(3,15)} is {(1,15),(21,55)} Thus where both
	 * Timetables are occupied. <br>
	 * 
	 * @return
	 */
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
		TimePoint[] one = TimeTable.eliminateOverlap(this);
		TimePoint[] two = TimeTable.eliminateOverlap(that);
		ArrayList<TimeSlot> rv = new ArrayList<TimeSlot>();
		int first = 0;
		int second = 0;
		while (first < one.length - 1 && second < two.length - 1) {
			while (first < one.length - 1
					&& second < two.length - 1
					&& (!(one[first].compareTo(two[second]) <= 0 && one[first + 1]
							.compareTo(two[second]) >= 0) && !(two[second]
							.compareTo(one[first]) <= 0 && two[second + 1]
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
	 * This function will eliminate all overlap in a given timetable.
	 * Also all things that are scheduled back to back will be considered as a whole thing.
	 * E.g. A timetable with timeslots from 1 to 9 and from 5 to 13, 
	 * will return the timepoints 1 (start) and 13 (stop).
	 * E.g. A timetable with timeslots from 1 to 5 and from 5 to 9,
	 * will return the timepoints 1 (start) and 9 (stop).
	 * 
	 * @param timeTable
	 * 			A certain timetable that has to be simplified
	 * @return The timePoints of this timetable without overlap
	 */
	public static TimePoint[] eliminateOverlap(TimeTable timeTable) {
		TimePoint[] timePoints = new TimePoint[timeTable.timeSlots.length * 2];
		int i = 0;
		for (TimeSlot t : timeTable.timeSlots) {
			timePoints[i++] = t.getStartPoint();
			timePoints[i++] = t.getStopPoint();
		}
		Arrays.sort(timePoints);
		Collection<TimePoint> simplifiedTimePoints = new ArrayList<TimePoint>();
		int amount = 0;
		for (i = 0; i < timePoints.length; i++) {
			if (timePoints[i].getType() == TimeType.start) {
				amount++;
				if (!(amount > 1)) {
					if(!(i > 0 && timePoints[i-1].getTime() == timePoints[i].getTime())){
						simplifiedTimePoints.add(timePoints[i]);
					}
				}
			} else {
				amount--;
				if (!(amount > 0)) {
					if(!(i < timePoints.length-1 && timePoints[i].getTime() == timePoints[i+1].getTime())){
						simplifiedTimePoints.add(timePoints[i]);
					}
				}
			}
		}
		TimePoint[] returnTimePoints = new TimePoint[simplifiedTimePoints
				.size()];
		simplifiedTimePoints.toArray(returnTimePoints);
		return returnTimePoints;
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
	
	/**
	 * This method will get the union of this TimeTable with a lot of other
	 * TimeTables.
	 * 
	 * @param tables
	 *            The collection of Tables you would like to get the union of.
	 * @return A TimeTable that's the union of all given tables and this table.
	 */
	public TimeTable unionAll(Collection<TimeTable> tables) {
		TimeTable rv = this.getUnion(this);
		for (TimeTable timeTable : tables)
			rv = timeTable.getUnion(rv);

		return rv;
	}

	/**
	 * This method will give the timeslot at the end of this time table without
	 * appending it to it's timeslots. It can be of any length, which is what
	 * the length parameter is for.
	 * 
	 * @param length
	 *            The wished length of the timeslot at the end of the current
	 *            timetable.
	 * @return A timeslot at the end of this timetable of the wanted length.
	 */
	private TimeSlot getLastSlotWithLength(long length) {
		Date startDate = this.timeSlots[this.timeSlots.length - 1]
				.getStopPoint().getDate();
		Date stopDate = new Date(startDate.getTime() + length);
		TimePoint startFree = new TimePoint(startDate, TimeType.start);
		TimePoint stopFree = new TimePoint(stopDate, TimeType.end);

		return new TimeSlot(startFree, stopFree);
	}
	
	/**
	 * Returns the timeslots of this timetable as a linked list.
	 * 
	 * @return The timeslots of this timetable as a linked list
	 */
	@Basic
	public LinkedList<TimeSlot> getTimeSlots() {
		return new LinkedList<TimeSlot>(Arrays.asList(this.timeSlots));
	}
	
	/**
	 * Returns a concatination of all timeslots of this timetable.
	 * 
	 * @return The concatination of all timeslots of this timetable
	 */
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
