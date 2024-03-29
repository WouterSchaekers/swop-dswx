package scheduler;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import scheduler.tasks.ScheduledTask;
import scheduler.tasks.UnscheduledTask;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeLordException;
import exceptions.InvalidTimeSlotException;

/**
 * This class is a tool that allows you to schedule certain things at the first
 * free slot available that satisfies all the constraints from the assignement.
 */
public class Scheduler2
{
	private TimeLord currentSystemTime;

	/**
	 * Default constructor. Will initialise time lord field.
	 * 
	 * @param lord
	 *            The timelord to be appointed to this scheduler.
	 * @throws InvalidTimeLordException
	 */
	public Scheduler2(TimeLord lord) throws InvalidTimeLordException {
		if (this.canHaveAsTimeLord(lord))
			this.currentSystemTime = lord;
		else
			throw new InvalidTimeLordException(
					"Invalid timelord given to scheduler!");
	}

	/**
	 * Schedule method where only an UnscheduledTask is given.
	 * 
	 * @param Task
	 *            The unscheduled task that needs to be scheduled.
	 * @return A scheduled task that contains all the necessary information.
	 * @throws InvalidResourceException
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidTimeSlotException
	 * @throws InvalidHospitalDateArgument
	 */
	public ScheduledTask schedule(UnscheduledTask unscheduledTask)
			throws InvalidTimeSlotException, InvalidSchedulingRequestException,
			InvalidResourceException, InvalidHospitalDateArgument {
		long duration = unscheduledTask.getDuration();
		LinkedList<LinkedList<Schedulable>> listOfSchedulables = new LinkedList<LinkedList<Schedulable>>(
				unscheduledTask.getResourcePool());
		this.deleteDoubles(listOfSchedulables);
		HospitalDate startDate = new HospitalDate(unscheduledTask
				.getCreationTime().getTimeSinceStart()
				+ unscheduledTask.getExtraTime());
		LinkedList<Integer> occurences = unscheduledTask.getOccurences();
		if (!isValidToScheduleCollection(listOfSchedulables, occurences)) {
			throw new InvalidSchedulingRequestException(
					"Trying to schedule an invalid amount of schedulables.");
		}
		LinkedList<Integer> fullOccurences = this
				.makeCorrespondingArray(occurences);
		boolean[][] treeMatrix = makeTreeMatrix(listOfSchedulables,
				fullOccurences);
		if (startDate.before(this.currentSystemTime.getSystemTime())) {
			startDate = this.currentSystemTime.getSystemTime();
		}
		PatientFile patient = unscheduledTask.getPatient();
		ScheduledTask schedTask = schedule(patient, duration, startDate,
				HospitalDate.END_OF_TIME, listOfSchedulables,
				new LinkedList<Schedulable>(), treeMatrix, fullOccurences, 0);
		boolean isScheduled = false;
		if (schedTask.getStartDate().equals(startDate)
				|| !unscheduledTask.mustBeBackToBack()
				|| this.isBackToBack(schedTask.getStartDate(), schedTask
						.getResources().get(0))) {
			isScheduled = true;
		}
		while (!isScheduled) {
			startDate = HospitalDate.getNextHour(startDate);
			try {
				schedTask = schedule(patient, duration, startDate,
						new HospitalDate(startDate.getTimeSinceStart()
								+ HospitalDate.ONE_HOUR), listOfSchedulables,
						new LinkedList<Schedulable>(), treeMatrix,
						fullOccurences, 0);
				if (this.isBackToBack(startDate, schedTask.getResources()
						.get(0))) {
					isScheduled = true;
				}
			} catch (InvalidSchedulingRequestException e) {
			}
			try {
				schedTask = schedule(patient, duration, startDate,
						new HospitalDate(startDate.getTimeSinceStart()
								+ duration), listOfSchedulables,
						new LinkedList<Schedulable>(), treeMatrix,
						fullOccurences, 0);
				isScheduled = true;
			} catch (InvalidSchedulingRequestException e) {
			}
		}

		LinkedList<Schedulable> scheddedRes = schedTask.getResources();
		for (Schedulable s : scheddedRes)
			s.scheduleAt(schedTask.getTimeSlot());
		return schedTask;
	}

	/**
	 * This method will schedule as many objects as needed from each of the
	 * linked lists in the given linked list.
	 * 
	 * @param duration
	 *            The length each resource needs to be scheduled for.
	 * @param startDate
	 *            The date from which this method should start looking
	 * @param stopDate
	 *            The date from which to stop looking from.
	 * @param neededSchedulables
	 *            A linked list of linked lists that's a collection of all
	 *            possible resources that can be scheduled. E.g. If one would
	 *            like to schedule a nurse that's working in the hospital, the
	 *            first linked list should be a collection of all nurses. If one
	 *            would like to schedule either nurse Jenny or nurse Jenna, the
	 *            first linked list should just contain Jenny and Jenna. If one
	 *            would like to schedule a nurse and a doctor, the first linked
	 *            list will be a collection of all nurses and the second one
	 *            will be one of all doctors etc...
	 * @param usedSchedulables
	 *            Used in the recursive call of this method.
	 * @param treeMatrix
	 *            A tree matrix is used to determine whether or not a resource
	 *            has already been checked in the recursive call.
	 * @param fullOccurences
	 *            Expansion from occurences.<br>
	 *            occurences: 1 3 2 -> fulloccurences: 1 222 33
	 * @param iteration
	 *            Used for recursive purposes.
	 * @return A ScheduledTask that contains all the scheduled resources, the
	 *         start time etc...
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidTimeSlotException
	 * @throws InvalidResourceException
	 * @throws InvalidHospitalDateArgument
	 */
	private ScheduledTask schedule(PatientFile patient, long duration,
			HospitalDate startDate, HospitalDate stopDate,
			LinkedList<LinkedList<Schedulable>> neededSchedulables,
			LinkedList<Schedulable> usedSchedulables, boolean[][] treeMatrix,
			LinkedList<Integer> fullOccurences, int iteration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidResourceException, InvalidHospitalDateArgument {
		int curCollectionToSchedule = fullOccurences.get(iteration);
		LinkedList<Schedulable> curSchedList = neededSchedulables
				.get(curCollectionToSchedule);
		int bestOption = findBestOption(duration, startDate, stopDate,
				treeMatrix[iteration], curSchedList);
		Schedulable chosenSchedulable = curSchedList.get(bestOption);
		TimeSlot bestTimeSlot = chosenSchedulable.getTimeTable()
				.getFirstFreeSlotBetween(startDate, stopDate, duration);

		HospitalDate newStartDate = bestTimeSlot.getStartPoint().getHospitalDate();
		HospitalDate newStopDate = bestTimeSlot.getStopPoint().getHospitalDate();
		LinkedList<Schedulable> newUsedSchedulables = Scheduler2
				.copyList(usedSchedulables);
		newUsedSchedulables.add(chosenSchedulable);
		boolean[][] newTreeMatrix = copyMatrix(treeMatrix);
		updateTreeMatrix(newTreeMatrix, bestOption, fullOccurences, iteration);
		if (iteration < fullOccurences.size() - 1) {
			try {
				return schedule(patient, duration, newStartDate, newStopDate,
						neededSchedulables, newUsedSchedulables, newTreeMatrix,
						fullOccurences, iteration + 1);
			} catch (InvalidSchedulingRequestException e) {
				HospitalDate nextHospitalDate = getNextHospitalDate(
						curSchedList, newStartDate, stopDate);
				return schedule(patient, duration, nextHospitalDate, stopDate,
						neededSchedulables, usedSchedulables, treeMatrix,
						fullOccurences, iteration);
			}
		} else {
			TimePoint startOfTimeSlot = new StartTimePoint(bestTimeSlot
					.getStartPoint().getHospitalDate());
			TimePoint endOfTimeSlot = new StopTimePoint(startOfTimeSlot
					.getHospitalDate().getTimeSinceStart() + duration);
			TimeSlot timeSlotToReturn = new TimeSlot(startOfTimeSlot,
					endOfTimeSlot);
			return new ScheduledTask(patient, newUsedSchedulables,
					timeSlotToReturn);
		}
	}

	private boolean isBackToBack(HospitalDate startDate, Schedulable schedulable)
			throws InvalidTimeSlotException {
		return schedulable.getTimeTable().isBackToBack(startDate);
	}

	/**
	 * This method will expand occurences into fullOccurences
	 * 
	 * @see schedule()
	 */
	private LinkedList<Integer> makeCorrespondingArray(
			LinkedList<Integer> occurences) {
		LinkedList<Integer> rv = new LinkedList<Integer>();
		for (int i = 0; i < occurences.size(); i++) {
			for (int j = 0; j < occurences.get(i); j++) {
				rv.add(i);
			}
		}
		return rv;
	}

	/**
	 * This method will find the best free slot from curSchedList.
	 * 
	 * @param duration
	 *            The length of the wanted free slot.
	 * @param startDate
	 *            The date from which to start looking from.
	 * @param stopDate
	 *            The date where to stop looking.
	 * @param treeArray
	 *            The array of the treematrix of the current list of
	 *            schedulables.
	 * @param curSchedList
	 *            The current list of schedulables.
	 * @return An integer that represents the sequential number of the best free
	 *         slot.
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidTimeSlotException
	 * @throws InvalidHospitalDateArgument
	 */
	private int findBestOption(long duration, HospitalDate startDate,
			HospitalDate stopDate, boolean[] treeArray,
			LinkedList<Schedulable> curSchedList)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidHospitalDateArgument {
		int bestOption = -1;
		TimeSlot bestSlot = null;
		HospitalDate bestDate = null;
		for (int i = 0; i < treeArray.length; i++) {
			if (treeArray[i]) {
				try {
					TimeSlot curSlot = curSchedList.get(i)
							.getFirstFreeSlotBetween(startDate, stopDate,
									duration);
					HospitalDate curDate = curSlot.getStartPoint().getHospitalDate();
					if (bestOption == -1
							|| (curDate.before(bestDate) || (curDate
									.equals(bestDate) && curSlot.getLength() > bestSlot
									.getLength()))) {
						bestOption = i;
						bestSlot = curSlot;
						bestDate = curDate;
					}
				} catch (IllegalStateException e) {
					;
				}
			}
		}
		if (bestOption == -1) {
			throw new InvalidSchedulingRequestException(
					"No Schedulable of this list can be schedulabled.");
		}
		return bestOption;
	}

	/**
	 * This method will return the HospitalDate of the start of the next free
	 * slot.
	 * 
	 * @param curSchedList
	 *            The current list of schedulables.
	 * @param previousDate
	 *            The date of the previous free slot.
	 * @param stopDate
	 *            The date on which we should stop looking.
	 * @return A HospitalDate that is the start of the next free time slot.
	 * @throws InvalidTimeSlotException
	 * @throws InvalidSchedulingRequestException
	 */
	private HospitalDate getNextHospitalDate(
			Collection<Schedulable> curSchedList, HospitalDate previousDate,
			HospitalDate stopDate) throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		HospitalDate nextDate = null;
		for (Schedulable curSchedulable : curSchedList) {
			try {
				HospitalDate curNextDate = curSchedulable.getTimeTable()
						.getNextHospitalDateAfter(previousDate);
				if (nextDate == null || curNextDate.before(curNextDate)) {
					nextDate = curNextDate;
				}
			} catch (InvalidHospitalDateException e) {
				;
			}
		}
		if (nextDate == null || stopDate.before(nextDate)) {
			throw new InvalidSchedulingRequestException(
					"No more timeslots available in this list.");
		}
		return nextDate;
	}

	/**
	 * This method will generate the treematrix.
	 * 
	 * @param neededSchedulables
	 *            --> @see schedule()
	 * @param fullOccurences
	 *            --> @see schedule()
	 * @return --> @see schedule()
	 */
	boolean[][] makeTreeMatrix(
			LinkedList<LinkedList<Schedulable>> neededSchedulables,
			LinkedList<Integer> fullOccurences) {
		boolean[][] treeMatrix = new boolean[fullOccurences.size()][];
		for (int i = 0; i < fullOccurences.size(); i++) {
			boolean[] currentArray = new boolean[neededSchedulables.get(
					fullOccurences.get(i)).size()];
			for (int j = 0; j < currentArray.length; j++) {
				currentArray[j] = true;
			}
			treeMatrix[i] = currentArray;
		}
		return treeMatrix;
	}

	/**
	 * This method will update the treematrix for the given iteration
	 * 
	 * @return The updated treematrix.
	 */
	private boolean[][] updateTreeMatrix(boolean[][] treeMatrix,
			int bestOption, LinkedList<Integer> fullOccurences, int iteration) {
		int occurenceNumber = fullOccurences.get(iteration);
		for (int i = 1; iteration + i < fullOccurences.size()
				&& occurenceNumber == fullOccurences.get(iteration + i); i++) {
			treeMatrix[iteration + i][bestOption] = false;
		}
		return treeMatrix;
	}

	/**
	 * Will check if the called method doesn't want to e.g. schedule more nurses
	 * than there are available in the hospital.
	 * 
	 * @param toCheck
	 *            The collection to check.
	 * @return True if the given Collection of Collections should be able to be
	 *         scheduled.
	 */
	private boolean isValidToScheduleCollection(
			LinkedList<LinkedList<Schedulable>> toCheck,
			LinkedList<Integer> occurences) {
		for (int i = 0; i < occurences.size(); i++) {
			if (occurences.get(i) > toCheck.get(i).size()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Removes the doubles from toCheck.
	 * 
	 * @throws InvalidSchedulingRequestException
	 */
	private void deleteDoubles(LinkedList<LinkedList<Schedulable>> toCheck)
			throws InvalidSchedulingRequestException {
		for (int i = 0; i < toCheck.size(); i++) {
			LinkedList<Schedulable> curToCheck = toCheck.get(i);
			for (int j = 0; j < curToCheck.size(); j++) {
				Schedulable curSchedulable = curToCheck.get(j);
				boolean deleted = false;
				for (int k = 0; k < toCheck.size() && !deleted; k++) {
					LinkedList<Schedulable> curToDelete = toCheck.get(k);
					for (int l = 0; l < curToDelete.size(); l++) {
						if ((i != k || j != l)
								&& curSchedulable.equals(curToDelete.get(l))) {
							curToDelete.remove(l);
							deleted = true;
							j--;
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < toCheck.size(); i++) {
			if (toCheck.get(i).size() == 0) {
				throw new InvalidSchedulingRequestException(
						"Deleted doubles. Some lists are empty now.");
			}
		}
	}

	/**
	 * @return True if t is a valid time lord for this scheduler.
	 */
	private boolean canHaveAsTimeLord(TimeLord t) {
		return t != null;
	}

	/**
	 * @return A copy of the given list.
	 */
	private static LinkedList<Schedulable> copyList(
			LinkedList<Schedulable> listToCopy) {

		LinkedList<Schedulable> newList = new LinkedList<Schedulable>();
		for (int i = 0; i < listToCopy.size(); i++) {
			newList.add(listToCopy.get(i));
		}
		return newList;
	}

	/**
	 * @return a clone of the tree matrix.
	 */
	private boolean[][] copyMatrix(boolean[][] treeMatrix) {
		boolean[][] newTreeMatrix = new boolean[treeMatrix.length][];
		for (int i = 0; i < treeMatrix.length; i++) {
			newTreeMatrix[i] = Arrays.copyOf(treeMatrix[i],
					treeMatrix[i].length);
		}
		return newTreeMatrix;
	}

	@Basic
	public TimeLord getSystemTime() {
		return new TimeLord(this.currentSystemTime.getSystemTime());
	}
}