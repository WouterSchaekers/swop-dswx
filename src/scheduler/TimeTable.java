package scheduler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;

import exceptions.ImpossibleToScheduleException;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class consisting of timeslots that always have a start- and end moment. The
 * slots stored here represent timeslots from real life.
 */
public class TimeTable
{
	private LinkedList<TimeSlot> timeSlots;

	/**
	 * Alternative constructor where a certain amount of slots can just be given
	 * as parameters, not as a collection.
	 */
	public TimeTable(TimeSlot... slots) {
		this(new LinkedList<TimeSlot>(Arrays.asList(slots)));
	}

	public TimeTable() {
		this(new LinkedList<TimeSlot>());
	}

	/**
	 * Default constructor. Will initialise all fields.
	 * 
	 * @param slots
	 *            All TimeSlots to be stored in this TimeTable.
	 */
	public TimeTable(LinkedList<TimeSlot> slots) {
		this.timeSlots = slots;
	}

	public TimeSlot[] getArrayTimeSlots() {
		TimeSlot[] returnValue = null;
		return this.timeSlots.toArray(returnValue);
	}

	/**
	 * Adds a timeslot to the current table.
	 * 
	 * @param timeSlot
	 *            The timeslot that has to be added
	 * @throws ImpossibleToScheduleException
	 *             The timeslot cannot be scheduled in this timetable
	 */
	public void addTimeSlot(TimeSlot timeSlot)
			throws ImpossibleToScheduleException {
		if (!this.hasFreeSlotAt(timeSlot)) {
			throw new ImpossibleToScheduleException(
					"Dieter, you fucking retard!");
		}
		this.timeSlots.add(timeSlot);
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
		int amountOfSlots = this.timeSlots.size();
		TimeSlot[] slot = this.getArrayTimeSlots();

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
		int amountOfSlots = this.timeSlots.size();
		LinkedList<TimeSlot> returnValue = new LinkedList<TimeSlot>();
		TimeSlot[] slots = this.getArrayTimeSlots();

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
	 * @param table
	 * @return
	 * @throws ImpossibleToScheduleException 
	 */
	public TimeTable invert() throws ImpossibleToScheduleException {
		TimeTable returnValue = null;
		// Start of time
		Date d1 = Scheduler.START_OF_TIME;
		// the end of time is here :)
		Date d2 = Scheduler.END_OF_TIME;
		TimeSlot t = new TimeSlot(new TimePoint(d1, TimeType.start),
				new TimePoint(d2, TimeType.stop));
		returnValue = new TimeTable();

		if (timeSlots.isEmpty())
			return new TimeTable(t);

		returnValue.addTimeSlot(new TimeSlot(new TimePoint(d1, TimeType.start),
				new TimePoint(timeSlots.get(0).getStartPoint().getDate(),
						TimeType.stop)));
		for (int i = 0; i < timeSlots.size() - 1; i++) {
			returnValue.addTimeSlot(new TimeSlot(new TimePoint(timeSlots.get(i)
					.getStopPoint().getDate(), TimeType.start), new TimePoint(
					timeSlots.get(i+1).getStartPoint().getDate(), TimeType.stop)));
		}
		returnValue.addTimeSlot(new TimeSlot(new TimePoint(timeSlots.get(this.timeSlots.size()-1)
				.getStartPoint().getDate(), TimeType.start), new TimePoint(d2,
				TimeType.stop)));
		return returnValue;
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
	 * @throws ImpossibleToScheduleException 
	 */
	public TimeTable getAllFreeSlots(long length) throws ImpossibleToScheduleException {
		TimeTable x = this.invert();
		TimeTable rv = new TimeTable();
		for (TimeSlot t : x.timeSlots) {
			if (t.getLength() >= length) {
				rv = rv.getUnion(new TimeTable(t));
			}
		}
		return rv;
	}

	public boolean hasFreeSlotAt(Date startDate, Date stopDate) throws ImpossibleToScheduleException {
		return this.hasFreeSlotAt(new TimeSlot(new TimePoint(startDate,
				TimeType.start), new TimePoint(stopDate, TimeType.stop)));
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
		for (TimeSlot thisSlot : this.timeSlots){
			if (thisSlot.overlaps(slotToCheck))
				return false;
		}
		return true;
	}

	/**
	 * The union of 2 timetables is this, the union of time slots, Ex : union
	 * {(1,9),(21,55)} with {(3,15)} is {(1,15),(21,55)} Thus where both
	 * Timetables are occupied. <br>
	 * 
	 * @return
	 */
	public TimeTable getUnion(TimeTable that) {
		TimePoint[] allPoints = new TimePoint[this.timeSlots.size() * 2
				+ that.timeSlots.size() * 2];
		LinkedList<TimeSlot> rv = new LinkedList<TimeSlot>();
		int i = 0;
		for (TimeSlot t : this.timeSlots) {
			allPoints[i++] = t.getStartPoint();
			allPoints[i++] = t.getStopPoint();
		}

		for (TimeSlot t : that.timeSlots) {
			allPoints[i++] = t.getStartPoint();
			allPoints[i++] = t.getStopPoint();
		}

		Arrays.sort(allPoints, TimePoint.ComparatorsStartFirst);
		i = 0;
		while (i < allPoints.length) {
			TimeSlot t = new TimeSlot(
					new TimePoint(new Date(0), TimeType.start), new TimePoint(
							new Date(3), TimeType.stop));
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
		LinkedList<TimeSlot> rv = new LinkedList<TimeSlot>();
		int first = 0;
		int second = 0;
		while (first < one.length - 1 && second < two.length - 1) {
			while (first < one.length - 1
					&& second < two.length - 1
					&& !TimeTable.inBetween(two[second], one[first],
							one[first + 1])
					&& !TimeTable.inBetween(one[first], two[second],
							two[second + 1])) {
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
	 * Checks wether the first timepoint lies between the two other timepoints.
	 * 
	 * @param timePoint
	 *            The timepoint that has to lie between the other two timepoints
	 * @param before
	 *            The timepoint that has to lie before the first timepoint
	 * @param after
	 *            The timepoint that has to lie behind the first timepoint
	 * @return True if the first timepoint lies between the other two timepoints
	 */
	public static boolean inBetween(TimePoint timePoint, TimePoint before,
			TimePoint after) {
		return before.compareTo(timePoint) <= 0
				&& after.compareTo(timePoint) >= 0;
	}

	/**
	 * This function will eliminate all overlap in a given timetable. Also all
	 * things that are scheduled back to back will be considered as a whole
	 * thing. E.g. A timetable with timeslots from 1 to 9 and from 5 to 13, will
	 * return the timepoints 1 (start) and 13 (stop). E.g. A timetable with
	 * timeslots from 1 to 5 and from 5 to 9, will return the timepoints 1
	 * (start) and 9 (stop).
	 * 
	 * @param timeTable
	 *            A certain timetable that has to be simplified
	 * @return The timePoints of this timetable without overlap
	 */
	public static TimePoint[] eliminateOverlap(TimeTable timeTable) {

		TimePoint[] timePoints = new TimePoint[timeTable.timeSlots.size() * 2];
		int i = 0;
		for (TimeSlot t : timeTable.timeSlots) {
			timePoints[i++] = t.getStartPoint();
			timePoints[i++] = t.getStopPoint();
		}
		Arrays.sort(timePoints, TimePoint.ComparatorsEndFirst);
		Collection<TimePoint> simplifiedTimePoints = new ArrayList<TimePoint>();
		int amount = 0;
		for (i = 0; i < timePoints.length; i++) {
			if (timePoints[i].getType() == TimeType.start) {
				amount++;
				if (!(amount > 1)) {
					if (!(i > 0 && timePoints[i - 1].getTime() == timePoints[i]
							.getTime())) {
						simplifiedTimePoints.add(timePoints[i]);
					}
				}
			} else {
				amount--;
				if (!(amount > 0)) {
					if (!(i < timePoints.length - 1 && timePoints[i].getTime() == timePoints[i + 1]
							.getTime())) {
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
		TimeSlot t;
		if (this.timeSlots.size() == 0) {
			System.out.println("wololo");
			t = new TimeSlot(new TimePoint(Scheduler.getCurrentSystemTime(),
					TimeType.start), new TimePoint(new Date(Scheduler
					.getCurrentSystemTime().getTime() + length), TimeType.stop));
			return t;
		}
		Date startDate = this.timeSlots.getLast()
				.getStopPoint().getDate();
		Date stopDate = new Date(startDate.getTime() + length);
		TimePoint startFree = new TimePoint(startDate, TimeType.start);
		TimePoint stopFree = new TimePoint(stopDate, TimeType.stop);

		t =  new TimeSlot(startFree, stopFree);
		System.out.println("TimeSlot allocated: " + t);
		return t;
	}

	/**
	 * Returns the timeslots of this timetable as a linked list.
	 * 
	 * @return The timeslots of this timetable as a linked list
	 */
	@Basic
	public LinkedList<TimeSlot> getTimeSlots() {
		return new LinkedList<TimeSlot>(this.timeSlots);
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
		if (this.timeSlots.size() != t.timeSlots.size())
			return false;
		for (int i = 0; i < t.timeSlots.size(); i++) {
			boolean t1Cond = this.timeSlots.get(i).getStartPoint().equals(
					t.timeSlots.get(i).getStartPoint());
			boolean t2Cond = this.timeSlots.get(i).getStopPoint().equals(
					t.timeSlots.get(i).getStopPoint());
			if (!(t1Cond && t2Cond))
				return false;
		}
		return true;
	}
}
