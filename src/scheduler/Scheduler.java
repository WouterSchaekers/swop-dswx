package scheduler;

import java.util.*;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import scheduler.task.*;

public class Scheduler
{
	private static HospitalDate currentSystemTime = new HospitalDate();

	/**
	 * Schedules a set of schedulables on the best available slot, taking into
	 * account the required duration.
	 * 
	 * @param duration
	 *            The duration of the needed timeslot.
	 * @param neededSchedulables
	 *            A collection of schedulable collections of which at least one
	 *            schedulable need to be scheduled.
	 * @param occurences
	 *            The amount of times a collection of schedulables has to be
	 *            scheduled.
	 * @return A task which includes the timeslot and the used schedulables.
	 * @throws InvalidTimeSlotException
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidResourceException
	 */
	public static ScheduledTask schedule(long duration,
			LinkedList<LinkedList<Schedulable>> neededSchedulables,
			LinkedList<Integer> occurences) throws InvalidTimeSlotException,
			InvalidSchedulingRequestException, InvalidResourceException {
		if (!Scheduler.isValidToScheduleCollection(neededSchedulables,
				occurences)) {
			throw new InvalidSchedulingRequestException(
					"Trying to schedule an invalid amount of schedulables.");
		}
		LinkedList<Integer> fullOccurences = Scheduler
				.makeCorrespondingArray(occurences);
		boolean[][] treeMatrix = Scheduler.makeTreeMatrix(neededSchedulables,
				fullOccurences);
		return Scheduler.schedule(duration, Scheduler.currentSystemTime,
				HospitalDate.END_OF_TIME, neededSchedulables,
				new LinkedList<Schedulable>(), treeMatrix, fullOccurences, 0);
	}

	/**
	 * Schedules a set of schedulables on the best available slot, taking into
	 * account the required duration. A seperate list of specific schedulables
	 * may be included.
	 * 
	 * @param duration
	 *            The duration of the needed timeslot.
	 * @param neededSchedulables
	 *            A collection of schedulable collections of which at least one
	 *            schedulable need to be scheduled.
	 * @param specificSchedulables
	 *            A collection of schedulables that all need to be scheduled.
	 * @param occurences
	 *            The amount of times a collection of schedulables has to be
	 *            scheduled.
	 * @return A task which includes the timeslot and the used schedulables.
	 * @throws InvalidTimeSlotException
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidResourceException
	 */
	public static ScheduledTask schedule(long duration,
			LinkedList<LinkedList<Schedulable>> neededSchedulables,
			LinkedList<Schedulable> specificSchedulables,
			LinkedList<Integer> occurences) throws InvalidTimeSlotException,
			InvalidSchedulingRequestException, InvalidResourceException {
		for (int i = specificSchedulables.size() - 1; i >= 0; i--) {
			LinkedList<Schedulable> newSchedulableList = new LinkedList<Schedulable>();
			newSchedulableList.add(specificSchedulables.get(i));
			neededSchedulables.addFirst(newSchedulableList);
			occurences.addFirst(1);
		}
		return Scheduler.schedule(duration, neededSchedulables, occurences);
	}

	private static ScheduledTask schedule(long duration,
			HospitalDate startDate, HospitalDate stopDate,
			LinkedList<LinkedList<Schedulable>> neededSchedulables,
			LinkedList<Schedulable> usedSchedulables, boolean[][] treeMatrix,
			LinkedList<Integer> fullOccurences, int iteration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidResourceException {

		
		int curCollectionToSchedule = fullOccurences.get(iteration);
		LinkedList<Schedulable> curSchedList = neededSchedulables
				.get(curCollectionToSchedule);
		int bestOption = Scheduler.findBestOption(duration, startDate,
				stopDate, treeMatrix[iteration], curSchedList);
		Schedulable chosenSchedulable = curSchedList.get(bestOption);
		TimeSlot bestTimeSlot = chosenSchedulable.getTimeTable()
				.getFirstFreeSlotBetween(startDate, stopDate, duration);

		HospitalDate newStartDate = bestTimeSlot.getStartPoint().getDate();
		HospitalDate newStopDate = bestTimeSlot.getStopPoint().getDate();
		LinkedList<Schedulable> newUsedSchedulables = Scheduler
				.copyList(usedSchedulables);
		newUsedSchedulables.add(chosenSchedulable);
		boolean[][] newTreeMatrix = Scheduler.copyMatrix(treeMatrix);
		Scheduler.updateTreeMatrix(newTreeMatrix, bestOption, fullOccurences,
				iteration);
		if (iteration < fullOccurences.size() - 1) {
			try {
				return Scheduler.schedule(duration, newStartDate, newStopDate,
						neededSchedulables, newUsedSchedulables, newTreeMatrix,
						fullOccurences, iteration + 1);
			} catch (InvalidSchedulingRequestException e) {
				treeMatrix[iteration][bestOption] = false;
				return Scheduler.schedule(duration, startDate, stopDate,
						neededSchedulables, usedSchedulables, treeMatrix,
						fullOccurences, iteration);
			}
		} else {
			TimePoint startOfTimeSlot = new StartTimePoint(bestTimeSlot.getStartPoint().getDate());
			TimePoint endOfTimeSlot = new EndTimePoint(startOfTimeSlot.getDate().getTimeSinceStart() + duration);
			TimeSlot timeSlotToReturn = new TimeSlot(startOfTimeSlot, endOfTimeSlot);
			return new ScheduledTask(newUsedSchedulables, timeSlotToReturn);
		}
	}

	private static LinkedList<Integer> makeCorrespondingArray(
			LinkedList<Integer> occurences) {
		LinkedList<Integer> rv = new LinkedList<Integer>();
		for (int i = 0; i < occurences.size(); i++) {
			for (int j = 0; j < occurences.get(i); j++) {
				rv.add(i);
			}
		}
		return rv;
	}

	private static int findBestOption(long duration, HospitalDate startDate,
			HospitalDate stopDate, boolean[] treeArray,
			LinkedList<Schedulable> curSchedList)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		int bestOption = -1;
		TimeSlot bestSlot = null;
		HospitalDate bestDate = null;
		for (int i = 0; i < treeArray.length; i++) {
			if (treeArray[i]) {
				TimeSlot curSlot = curSchedList.get(i).getTimeTable()
						.getFirstFreeSlotBetween(startDate, stopDate, duration);
				HospitalDate curDate = curSlot.getStartPoint().getDate();
				if (bestOption == -1 || (curDate.before(bestDate)
						|| (curDate.equals(bestDate) && curSlot.getLength() > bestSlot
								.getLength()))) {
					bestOption = i;
					bestSlot = curSlot;
					bestDate = curDate;
				}
			}
		}
		if (bestOption == -1) {
			throw new InvalidSchedulingRequestException(
					"No Schedulable of this list can be schedulabled.");
		}
		return bestOption;
	}

	public static boolean[][] makeTreeMatrix(
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

	private static boolean[][] updateTreeMatrix(boolean[][] treeMatrix,
			int bestOption, LinkedList<Integer> occurences, int iteration) {
		int occurenceNumber = occurences.get(iteration);
		for (int i = 0; i < occurenceNumber; i++) {
			if (iteration + i < occurences.size()
					&& occurences.get(iteration + i) == occurences
							.get(iteration)) {
				treeMatrix[iteration + i][bestOption] = false;
			}
		}
		return treeMatrix;
	}

	@Basic
	public static void setNewSystemTime(HospitalDate newTime) {
		if (!isValidSystemTime(newTime))
			throw new IllegalArgumentException(
					"Invalid new system time given to setNewSystemTime() in Scheduler!");
		currentSystemTime = newTime;
	}

	/**
	 * @return True if t is a valid new system time.
	 */
	private static boolean isValidSystemTime(HospitalDate t) {
		if (currentSystemTime == null)
			return t != null;
		return t != null
				&& t.getTimeSinceStart() >= currentSystemTime.getTimeSinceStart();
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
	private static boolean isValidToScheduleCollection(
			LinkedList<LinkedList<Schedulable>> toCheck,
			LinkedList<Integer> occurences) {
		for (int i = 0; i < occurences.size(); i++) {
			if (occurences.get(i) > toCheck.get(i).size()) {
				return false;
			}
		}
		return true;
	}

	private static LinkedList<Schedulable> copyList(
			LinkedList<Schedulable> listToCopy) {
		LinkedList<Schedulable> newList = new LinkedList<Schedulable>();
		for (int i = 0; i < listToCopy.size(); i++) {
			newList.add(listToCopy.get(i));
		}
		return newList;
	}

	private static boolean[][] copyMatrix(boolean[][] treeMatrix) {
		boolean[][] newTreeMatrix = new boolean[treeMatrix.length][];
		for (int i = 0; i < treeMatrix.length; i++) {
			newTreeMatrix[i] = Arrays.copyOf(treeMatrix[i],
					treeMatrix[i].length);
		}
		return newTreeMatrix;
	}
}