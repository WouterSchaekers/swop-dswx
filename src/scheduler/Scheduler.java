package scheduler;

import java.util.*;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import scheduler.task.*;

public class Scheduler
{
	private static HospitalDate currentSystemTime = new HospitalDate();

	public static Task schedule(long duration,
			LinkedList<LinkedList<Schedulable>> neededResources,
			LinkedList<Integer> occurences) throws InvalidTimeSlotException,
			InvalidSchedulingRequestException {
		LinkedList<Integer> newOccurences = Scheduler
				.makeCorrespondingArray(occurences);
		boolean[][] treeMatrix = Scheduler.makeTreeMatrix(neededResources,
				newOccurences);
		return Scheduler.schedule(duration, Scheduler.currentSystemTime,
				HospitalDate.END_OF_TIME, neededResources,
				new LinkedList<Schedulable>(), treeMatrix, newOccurences, 0);
	}

	private static Task schedule(long duration, HospitalDate startDate,
			HospitalDate stopDate,
			LinkedList<LinkedList<Schedulable>> neededResources,
			LinkedList<Schedulable> usedResources, boolean[][] treeMatrix,
			LinkedList<Integer> occurences, int iteration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {

		LinkedList<Schedulable> curResList = neededResources.get(iteration);
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

	private static int findBestOption(long duration, HospitalDate startDate,
			HospitalDate stopDate, boolean[][] treeMatrix,
			LinkedList<Schedulable> curResList, int iteration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		HospitalDate firstDate = HospitalDate.END_OF_TIME;
		int bestOption = -1;
		for (int i = 0; i < treeMatrix[iteration].length; i++) {
			if (treeMatrix[iteration][i]) {
				HospitalDate curDate = curResList.get(i).getTimeTable()
						.getFirstFreeSlotBetween(startDate, stopDate, duration)
						.getStartPoint().getDate();
				if (curDate.before(firstDate)) {
					bestOption = i;
					firstDate = curDate;
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
}