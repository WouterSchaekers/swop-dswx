package scheduler;

import java.util.*;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import scheduler.task.*;

public class Scheduler
{
	private static HospitalDate currentSystemTime = new HospitalDate();

	/**
	 * Schedules a set of resources on the best available slot, taking into
	 * account the required duration.
	 * 
	 * @param duration
	 *            The duration of the needed timeslot.
	 * @param neededResources
	 *            A collection of schedulable collections of which at least one
	 *            schedulable need to be scheduled.
	 * @param occurences
	 *            The amount of times a collection of resources has to be
	 *            scheduled.
	 * @return A task which includes the timeslot and the used resources.
	 * @throws InvalidTimeSlotException
	 * @throws InvalidSchedulingRequestException
	 */
	public static Task schedule(long duration,
			LinkedList<LinkedList<Schedulable>> neededResources,
			LinkedList<Integer> occurences) throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		if (!Scheduler.isValidToScheduleCollection(neededResources, occurences)) {
			throw new InvalidSchedulingRequestException(
					"Trying to schedule an invalid amount of resources.");
		}
		LinkedList<Integer> newOccurences = Scheduler
				.makeCorrespondingArray(occurences);
		boolean[][] treeMatrix = Scheduler.makeTreeMatrix(neededResources,
				newOccurences);
		return Scheduler.schedule(duration, Scheduler.currentSystemTime,
				HospitalDate.END_OF_TIME, neededResources,
				new LinkedList<Schedulable>(), treeMatrix, newOccurences, 0);
	}

	/**
	 * Schedules a set of resources on the best available slot, taking into
	 * account the required duration. A seperate list of specific resources may
	 * be included.
	 * 
	 * @param duration
	 *            The duration of the needed timeslot.
	 * @param neededResources
	 *            A collection of schedulable collections of which at least one
	 *            schedulable need to be scheduled.
	 * @param specificResources
	 *            A collection of resources that all need to be scheduled.
	 * @param occurences
	 *            The amount of times a collection of resources has to be
	 *            scheduled.
	 * @return A task which includes the timeslot and the used resources.
	 * @throws InvalidTimeSlotException
	 * @throws InvalidSchedulingRequestException
	 */
	public static Task schedule(long duration,
			LinkedList<LinkedList<Schedulable>> neededResources,
			LinkedList<Schedulable> specificResources,
			LinkedList<Integer> occurences) throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		for (int i = specificResources.size() - 1; i >= 0; i--) {
			LinkedList<Schedulable> newResourceList = new LinkedList<Schedulable>();
			newResourceList.add(specificResources.get(i));
			neededResources.addFirst(newResourceList);
			occurences.addFirst(1);
		}
		return Scheduler.schedule(duration, neededResources, occurences);
	}

	private static Task schedule(long duration, HospitalDate startDate,
			HospitalDate stopDate,
			LinkedList<LinkedList<Schedulable>> neededResources,
			LinkedList<Schedulable> usedResources, boolean[][] treeMatrix,
			LinkedList<Integer> occurences, int iteration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {

		int curCollectionToSchedule = occurences.get(iteration);
		LinkedList<Schedulable> curResList = neededResources
				.get(curCollectionToSchedule);
		int bestOption = Scheduler.findBestOption(duration, startDate,
				stopDate, treeMatrix, curResList, iteration);
		Schedulable chosenSchedulable = curResList.get(bestOption);
		usedResources.add(chosenSchedulable);
		treeMatrix = Scheduler.updateTreeMatrix(treeMatrix, bestOption,
				occurences, iteration);

		if (++iteration <= occurences.size()) {
			return schedule(duration, startDate, stopDate, neededResources,
					usedResources, treeMatrix, occurences, iteration);
		} else {
			TimePoint startPoint = curResList
					.get(bestOption)
					.getTimeTable()
					.getFirstFreeSlotFrom(Scheduler.currentSystemTime, duration)
					.getStartPoint();
			TimeSlot bestSlot = new TimeSlot(startPoint, new TimePoint(
					new HospitalDate(startPoint.getDate().getTotalMillis()
							+ duration), TimeType.stop));
			return new Task(usedResources, bestSlot);
		}
	}

	private static LinkedList<Integer> makeCorrespondingArray(
			LinkedList<Integer> occurences) {
		LinkedList<Integer> rv = new LinkedList<Integer>();
		for (int i = 0; i < occurences.size(); i++) {
			for (int j = 0; j < i; j++) {
				rv.add(i);
			}
		}
		return rv;
	}

	private static boolean[][] makeTreeMatrix(
			LinkedList<LinkedList<Schedulable>> neededResources,
			LinkedList<Integer> newOccurences) {
		boolean[][] treeMatrix = new boolean[newOccurences.size()][];
		for (int i = 0; i < newOccurences.size(); i++) {
			boolean[] currentArray = new boolean[neededResources.get(i).size()];
			for (int j = 0; j < currentArray.length; j++) {
				currentArray[j] = true;
			}
			treeMatrix[i] = currentArray;
		}
		return treeMatrix;
	}

	/**
	 * 
	 * @param duration
	 * @param startDate
	 * @param stopDate
	 * @param treeMatrix
	 * @param curResList
	 * @param iteration
	 * @return
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidTimeSlotException
	 */
	private static int findBestOption(long duration, HospitalDate startDate,
			HospitalDate stopDate, boolean[][] treeMatrix,
			LinkedList<Schedulable> curResList, int iteration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		int bestOption = -1;
		TimeSlot bestSlot = new TimeSlot(new TimePoint(
				HospitalDate.END_OF_TIME, TimeType.start), new TimePoint(
				HospitalDate.END_OF_TIME, TimeType.stop));
		HospitalDate bestDate = bestSlot.getStartPoint().getDate();
		for (int i = 0; i < treeMatrix[iteration].length; i++) {
			if (treeMatrix[iteration][i]) {
				TimeSlot curSlot = curResList.get(i).getTimeTable()
						.getFirstFreeSlotBetween(startDate, stopDate, duration);
				HospitalDate curDate = curSlot.getStartPoint().getDate();
				if (curDate.before(bestDate)
						|| (curDate.equals(bestDate) && curSlot.getLength() > bestSlot
								.getLength())) {
					bestOption = i;
					bestSlot = curSlot;
					bestDate = curDate;
				}
			}
		}
		return bestOption;
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
				&& t.getTotalMillis() >= currentSystemTime.getTotalMillis();
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
			if (occurences.get(i) >= toCheck.get(i).size()) {
				return false;
			}
		}
		return true;
	}
}