package scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * A class consisting of timeslots that always have a start- and end moment. The
 * slots stored here represent timeslots from real life.
 */
public class TimeTable
{
	private LinkedList<TimeSlot> timeSlots_;
	/**
	 * This will allow us to keep the TimeSlots chronologically sorted.
	 */
	private static Comparator<TimeSlot> c = new Comparator<TimeSlot>()
	{
		public int compare(TimeSlot o1, TimeSlot o2) {
			if (o1.getStartPoint().getTime() < o2.getStartPoint().getTime())
				return -1;
			else if (o1.getStartPoint().getTime() == o2.getStartPoint().getTime())
				return 0;
			else
				return 1;
		};
	};

	/**
	 * Default constructor. Will initialise all fields.
	 * 
	 * @param slots
	 *            All TimeSlots to be stored in this TimeTable.
	 * @throws InvalidTimeSlotException
	 *             The given TimeSlots are not valid.
	 */
	public TimeTable(LinkedList<TimeSlot> timeSlots) throws InvalidTimeSlotException {
		if (!TimeTable.isValidTimeSlots(timeSlots))
			throw new InvalidTimeSlotException("TimeTable initialized with nullpointer.");
		this.timeSlots_ = timeSlots;
		this.eliminateOverlap();
	}

	/**
	 * Alternative constructor where a certain amount of slots can just be given
	 * as parameters, not as a collection.
	 */
	public TimeTable(TimeSlot... slots) throws InvalidTimeSlotException {
		this(new LinkedList<TimeSlot>(Arrays.asList(slots)));
	}

	/**
	 * Initialises an empty time table.
	 */
	public TimeTable() {
		this.timeSlots_ = new LinkedList<TimeSlot>();
	}

	/**
	 * @return An array of the TimeSlots of this TimeTable.
	 */
	public TimeSlot[] getArrayTimeSlots() {
		TimeSlot[] returnValue = new TimeSlot[this.timeSlots_.size()];
		return this.timeSlots_.toArray(returnValue);
	}

	/**
	 * Adds a timeslot to the current table.
	 * 
	 * @param timeSlot
	 *            The timeslot that has to be added
	 * @throws InvalidSchedulingRequestException
	 *             The timeslot cannot be scheduled in this timetable
	 */
	public void addTimeSlot(TimeSlot timeSlot) throws InvalidSchedulingRequestException {
		if (!this.hasFreeSlotAt(timeSlot) || timeSlot == null) {
			throw new InvalidSchedulingRequestException("Can't schedule at given timeSlot!");
		}
		this.timeSlots_.add(timeSlot);
		this.sortTimeSlots();
		this.eliminateOverlap();
	}

	/**
	 * This method will sort the timeslots kept in this timetable by starting
	 * time.
	 */
	private void sortTimeSlots() {
		TimeSlot[] toSort = new TimeSlot[timeSlots_.size()];
		Iterator<TimeSlot> timeIt = timeSlots_.iterator();
		for (int i = 0; i < toSort.length; i++)
			toSort[i] = timeIt.next();
		Arrays.sort(toSort, c);
		this.timeSlots_ = new LinkedList<TimeSlot>(Arrays.asList(toSort));
	}

	/**
	 * @return The invert of this TimeTable.
	 */
	public TimeTable invert() {
		TimeTable returnValue = null;
		this.sortTimeSlots();
		HospitalDate d1 = HospitalDate.START_OF_TIME;
		HospitalDate d2 = HospitalDate.END_OF_TIME;
		TimeSlot timeSlot = new TimeSlot(new StartTimePoint(d1), new StopTimePoint(d2));
		returnValue = new TimeTable();
		if (this.timeSlots_.isEmpty())
			try {
				return new TimeTable(timeSlot);
			} catch (InvalidTimeSlotException e) {
				throw new Error(e);
			}
		if (this.timeSlots_.get(0).getStartPoint().getTime() != d1.getTimeSinceStart())
			try {
				returnValue.addTimeSlot(new TimeSlot(new StartTimePoint(d1), new StopTimePoint(this.timeSlots_.get(0)
						.getStartPoint().getHospitalDate())));
			} catch (InvalidSchedulingRequestException e) {
				throw new Error(e);
			}
		for (int i = 0; i < this.timeSlots_.size() - 1; i++)
			try {
				returnValue.addTimeSlot(new TimeSlot(new StartTimePoint(this.timeSlots_.get(i).getStopPoint()
						.getHospitalDate()), new StopTimePoint(this.timeSlots_.get(i + 1).getStartPoint()
						.getHospitalDate())));
			} catch (InvalidSchedulingRequestException e) {
				throw new Error(e);
			}
		if (this.timeSlots_.get(this.timeSlots_.size() - 1).getStopPoint().getHospitalDate().getTimeSinceStart() != d2
				.getTimeSinceStart())
			try {
				returnValue.addTimeSlot(new TimeSlot(new StartTimePoint(this.timeSlots_.get(this.timeSlots_.size() - 1)
						.getStopPoint().getHospitalDate()), new StopTimePoint(d2)));
			} catch (InvalidSchedulingRequestException e) {
				throw new Error(e);
			}
		return returnValue;
	}

	/**
	 * This method returns all free slots in this TimeTable with a certain
	 * minimal length.
	 * 
	 * @param length
	 *            The minimal length of the required free slot.
	 * @return A TimeTable that contains all free slots of this TimeTable.
	 */
	public TimeTable getAllFreeSlots(long length) {
		TimeTable invert = this.invert();
		TimeTable rv = new TimeTable();
		for (TimeSlot t : invert.timeSlots_)
			if (t.getLength() >= length)
				try {
					rv = rv.getUnion(new TimeTable(t));
				} catch (InvalidTimeSlotException e) {
					throw new Error(e);
				}
		return rv;
	}

	/**
	 * Returns the first free slot from a specific HospitalDate.
	 * 
	 * @param hospitalDate
	 *            The hospitalDate.
	 * @return The first free slot from the given HospitalDate.
	 */
	public TimeSlot getFirstFreeSlotFrom(HospitalDate hospitalDate) {
		TimeTable invert = this.invert();
		LinkedList<TimeSlot> allTimeSlots = invert.timeSlots_;
		for (TimeSlot t : allTimeSlots)
			if (!t.getStartPoint().getHospitalDate().before(hospitalDate))
				return t;
		StopTimePoint newStopPoint = new StopTimePoint(allTimeSlots.get(allTimeSlots.size() - 1).getStopPoint()
				.getHospitalDate());
		return new TimeSlot(new StartTimePoint(hospitalDate), newStopPoint);
	}

	/**
	 * Returns the first free slot between two specific dates, with a given
	 * duration.
	 * 
	 * @param startDate
	 *            The date that has to fall before the free slot.
	 * @param stopDate
	 *            The date that has to fall behind the free slot.
	 * @param duration
	 *            The minimal duration of the TimeSlot.
	 * @return The first free slot between the startDate and stopDate with a
	 *         minimal duration of duration.
	 */
	public TimeSlot getFirstFreeSlotBetween(HospitalDate startDate, HospitalDate stopDate, long duration) {
		TimeTable invert = this.invert();
		for (TimeSlot t : invert.timeSlots_) {
			HospitalDate startDateOfTimeSlot = t.getStartPoint().getHospitalDate();
			HospitalDate stopDateOfTimeSlot = t.getStopPoint().getHospitalDate();
			if (!startDateOfTimeSlot.before(startDate))
				if (stopDateOfTimeSlot.before(stopDate)
						&& stopDateOfTimeSlot.getTimeSinceStart() - startDateOfTimeSlot.getTimeSinceStart() >= duration)
					return new TimeSlot(new StartTimePoint(startDateOfTimeSlot), new StopTimePoint(stopDateOfTimeSlot));
				else if (!stopDateOfTimeSlot.before(stopDate)
						&& stopDate.getTimeSinceStart() - startDateOfTimeSlot.getTimeSinceStart() >= duration)
					return new TimeSlot(new StartTimePoint(startDateOfTimeSlot), new StopTimePoint(stopDate));
				else if (stopDateOfTimeSlot.before(stopDate)
						&& stopDateOfTimeSlot.getTimeSinceStart() - startDate.getTimeSinceStart() >= duration)
					return new TimeSlot(new StartTimePoint(startDate), new StopTimePoint(stopDateOfTimeSlot));
				else if (!stopDateOfTimeSlot.before(stopDate)
						&& stopDate.getTimeSinceStart() - startDate.getTimeSinceStart() >= duration)
					return new TimeSlot(new StartTimePoint(startDate), new StopTimePoint(stopDate));
		}
		throw new IllegalStateException("No more free slots available between the given Start -and EndDate!");
	}

	/**
	 * Checks whether the TimeTable has a free slot between two given dates.
	 * 
	 * @param startDate
	 *            The TimeTable needs to have a free slot behind this date.
	 * @param stopDate
	 *            The TimeTable needs to have a free slot before this date.
	 * @return True if the TimeTable has a free TimeSlot between the given start
	 *         -and stopDate.
	 */
	public boolean hasFreeSlotAt(HospitalDate startDate, HospitalDate stopDate) {
		return this.hasFreeSlotAt(new TimeSlot(new StartTimePoint(startDate), new StopTimePoint(stopDate)));
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
		for (TimeSlot thisSlot : this.timeSlots_) {
			if (thisSlot.overlaps(slotToCheck))
				return false;
		}
		return true;
	}

	/**
	 * Checks whether a given HospitalDate is back to back with a TimeSlot in
	 * this TimeTable.
	 * 
	 * @param startDate
	 *            The HospitalDate that has to be checked.
	 * @return True if the given HospitalDate is back to back with a TimeSlot.
	 */
	public boolean isBackToBack(HospitalDate startDate) {
		for (TimeSlot thisSlot : this.timeSlots_)
			if (thisSlot.isToBack(startDate))
				return true;
		return false;
	}

	/**
	 * This function will calculate the union of this timetable with another
	 * timetable. The result is a new timetable that is marked as "busy" when
	 * one of the timetables are busy.
	 * 
	 * @param that
	 *            The timetable which has to be unioned with this one.
	 * @return A new timetable that has the busy-slots of all timetables.
	 */
	public TimeTable getUnion(TimeTable that) {
		this.sortTimeSlots();
		TimePoint[] allPoints = new TimePoint[this.timeSlots_.size() * 2 + that.timeSlots_.size() * 2];
		LinkedList<TimeSlot> rv = new LinkedList<TimeSlot>();
		int i = 0;
		for (TimeSlot t : this.timeSlots_) {
			allPoints[i++] = t.getStartPoint();
			allPoints[i++] = t.getStopPoint();
		}

		for (TimeSlot t : that.timeSlots_) {
			allPoints[i++] = t.getStartPoint();
			allPoints[i++] = t.getStopPoint();
		}
		Arrays.sort(allPoints, TimePoint.ComparatorsStartFirst);
		i = 0;
		while (i < allPoints.length) {
			TimeSlot t = new TimeSlot(new StartTimePoint(new HospitalDate(0)), new StopTimePoint(new HospitalDate(3)));
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
		TimeTable timeTable = null;
		try {
			timeTable = new TimeTable(rv);
		} catch (InvalidTimeSlotException e) {
			throw new Error(e);
		}
		return timeTable;
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
		return (this.invert().getUnion(that.invert())).invert();
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
	public void eliminateOverlap() {
		TimePoint[] timePoints = new TimePoint[this.timeSlots_.size() * 2];
		for (int i = 0; i < this.timeSlots_.size(); i++) {
			TimeSlot t = this.timeSlots_.get(i);
			timePoints[2 * i] = t.getStartPoint();
			timePoints[2 * i + 1] = t.getStopPoint();
		}
		Arrays.sort(timePoints, TimePoint.ComparatorsEndFirst);
		ArrayList<TimePoint> simplifiedTimePoints = new ArrayList<TimePoint>();
		int amount = 0;
		for (int i = 0; i < timePoints.length; i++)
			if (timePoints[i].isStart()) {
				amount++;
				if (!(amount > 1))
					if (!(i > 0 && timePoints[i - 1].getTime() == timePoints[i].getTime()))
						simplifiedTimePoints.add(timePoints[i]);
			} else {
				amount--;
				if (!(amount > 0))
					if (!(i < timePoints.length - 1 && timePoints[i].getTime() == timePoints[i + 1].getTime()))
						simplifiedTimePoints.add(timePoints[i]);
			}
		LinkedList<TimeSlot> newTimeSlots = new LinkedList<TimeSlot>();
		for (int i = 0; i < simplifiedTimePoints.size() - 1; i = i + 2) {
			TimeSlot timeSlot = new TimeSlot(simplifiedTimePoints.get(i), simplifiedTimePoints.get(i + 1));
			newTimeSlots.add(timeSlot);
		}
		this.timeSlots_ = newTimeSlots;
	}

	public TimePoint[] getTimePoints() {
		TimePoint[] timePoints = new TimePoint[this.timeSlots_.size() * 2];
		for (int i = 0; i < this.timeSlots_.size(); i++) {
			TimeSlot currentTimeSlot = this.timeSlots_.get(i).clone();
			timePoints[i * 2] = currentTimeSlot.getStartPoint();
			timePoints[i * 2 + 1] = currentTimeSlot.getStopPoint();
		}
		return timePoints;
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
	 * @throws InvalidTimeSlotException
	 * @throws InvalidSchedulingRequestException
	 */
	public static TimeTable intersectAll(Collection<TimeTable> tables) throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		TimeTable table = new TimeTable();
		table = table.invert();
		for (TimeTable t : tables) {
			table = table.getIntersect(t);
		}
		return table;
	}

	/**
	 * @return The timeslots of this timetable as a LinkedList.
	 */
	@Basic
	public LinkedList<TimeSlot> getTimeSlots() {
		this.sortTimeSlots();
		LinkedList<TimeSlot> rv = new LinkedList<TimeSlot>();
		for (TimeSlot s : this.timeSlots_)
			rv.add(s.clone());
		return rv;
	}

	/**
	 * Updates this TimeTable, so it stays clean. All the TimeSlots that are
	 * finished will be deleted.
	 * 
	 * @param newDate
	 *            The HospitalDate on which all the TimeSlots will have to be
	 *            checked against.
	 */
	public void updateTimeTable(HospitalDate newDate) {
		LinkedList<TimeSlot> newTimeSlots = new LinkedList<TimeSlot>();
		for (TimeSlot timeSlot : this.timeSlots_)
			if (newDate.before(timeSlot.getStopPoint().getHospitalDate()))
				newTimeSlots.add(timeSlot);
		this.timeSlots_ = newTimeSlots;
	}

	/**
	 * Returns a concatination of all timeslots of this timetable.
	 * 
	 * @return The concatination of all timeslots of this timetable
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (TimeSlot slot : this.timeSlots_)
			builder.append(slot.toString());
		return builder.toString();
	}

	/**
	 * Checks wether all the given collection of timeslots consists of valid
	 * timeslots.
	 * 
	 * @param timeSlots
	 *            the given collection of timeslots
	 * @return true if all timeslots are valid
	 */
	public static boolean isValidTimeSlots(LinkedList<TimeSlot> timeSlots) {
		if (timeSlots == null)
			return false;
		return true;
	}

	/**
	 * @return True if the given TimeTable is "busy" at the same moments as this
	 *         TimeTable.
	 */
	public boolean equals(TimeTable timeTable) {
		if (this.timeSlots_.size() != timeTable.timeSlots_.size())
			return false;
		for (int i = 0; i < timeTable.timeSlots_.size(); i++) {
			boolean t1Cond = this.timeSlots_.get(i).getStartPoint().equals(timeTable.timeSlots_.get(i).getStartPoint());
			boolean t2Cond = this.timeSlots_.get(i).getStopPoint().equals(timeTable.timeSlots_.get(i).getStopPoint());
			if (!(t1Cond && t2Cond))
				return false;
		}
		return true;
	}

	/**
	 * Returns a copy of this TimeTable.
	 */
	@Override
	public TimeTable clone() {
		LinkedList<TimeSlot> newTimeSlots = new LinkedList<TimeSlot>();
		for (TimeSlot timeSlot : this.getTimeSlots())
			newTimeSlots.add(timeSlot);
		TimeTable newTimeTable = null;
		try {
			newTimeTable = new TimeTable(newTimeSlots);
		} catch (InvalidTimeSlotException e) {
			e.printStackTrace();
		}
		return newTimeTable;
	}
}