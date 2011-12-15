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
		LinkedList<Integer> rv = Scheduler.makeCorrespondingArray(occurences);
		boolean[][] treeMatrix = Scheduler.makeTreeMatrix(neededResources, rv);
		return Scheduler.schedule(duration, neededResources,
				new LinkedList<Schedulable>(), treeMatrix, rv, 0);
	}

	private static Task schedule(long duration,
			LinkedList<LinkedList<Schedulable>> neededResources, LinkedList<Schedulable> usedResources,
			boolean[][] treeMatrix, LinkedList<Integer> occurences,
			int iteration) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {

		LinkedList<Schedulable> curResList = neededResources.get(iteration);
		int bestOption = Scheduler.findBestOption(duration, treeMatrix,
				curResList, iteration);
		usedResources.add(curResList.get(bestOption));
		treeMatrix = Scheduler.updateTreeMatrix(treeMatrix, bestOption,
				occurences, iteration);
		
		if(++iteration <= occurences.size()){
			return schedule(duration, neededResources, usedResources, treeMatrix, occurences, iteration);
		}
		else{
			TimePoint startPoint = curResList.get(bestOption).getTimeTable()
					.getFirstFreeSlotFrom(Scheduler.currentSystemTime,
							duration).getStartPoint();
			TimeSlot bestSlot = new TimeSlot(startPoint, new TimePoint(new HospitalDate(startPoint.getDate().getTotalMillis() + duration), TimeType.stop));
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
			LinkedList<Integer> occurences) {
		boolean[][] treeMatrix = new boolean[neededResources.size()][];
		for (int i = 0; i < neededResources.size(); i++) {
			int curOccurence = occurences.get(i);
			for (int j = 0; j < curOccurence; j++) {
				boolean[] currentArray = new boolean[neededResources.get(i)
						.size()];
				for (int k = 0; k < currentArray.length; k++) {
					currentArray[j] = true;
				}
				treeMatrix[i] = currentArray;
			}
		}
		return treeMatrix;
	}

	private static int findBestOption(long duration, boolean[][] treeMatrix,
			LinkedList<Schedulable> curResList, int iteration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		HospitalDate firstDate = HospitalDate.END_OF_TIME;
		int bestOption = -1;
		for (int i = 0; i < treeMatrix[iteration].length; i++) {
			if (treeMatrix[iteration][i]) {
				HospitalDate curDate = curResList
						.get(i)
						.getTimeTable()
						.getFirstFreeSlotFrom(Scheduler.currentSystemTime,
								duration).getStartPoint().getDate();
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