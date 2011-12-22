package scheduler;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import scheduler.task.Schedulable;
import scheduler.task.ScheduledTask;
import scheduler.task.UnscheduledTask;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class is a tool that allows you to schedule certain things at the first
 * free slot available that satisfies all the constraints from the assignement.
 */
public class Scheduler
{
	private HospitalDate currentSystemTime = new HospitalDate();
	
	/**
	 * Schedules a set of schedulables on the best available slot, taking into
	 * account the required duration. <br>
	 * <br>
	 * <b>We recommend not using this method. You need to use schedule(duration,
	 * startDate, users, otherSchedulables, userOccurences, otherOccurences) if
	 * you want to meet the requirements of the assignment!</b>
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
	public ScheduledTask schedule(long duration, HospitalDate startDate,
			LinkedList<LinkedList<Schedulable>> neededSchedulables,
			LinkedList<Integer> occurences) throws InvalidTimeSlotException,
			InvalidSchedulingRequestException, InvalidResourceException {
		if (!isValidToScheduleCollection(neededSchedulables,
				occurences)) {
			throw new InvalidSchedulingRequestException(
					"Trying to schedule an invalid amount of schedulables.");
		}
		LinkedList<Integer> fullOccurences = new Scheduler()
				.makeCorrespondingArray(occurences);
		boolean[][] treeMatrix = makeTreeMatrix(neededSchedulables,
				fullOccurences);
		return schedule(duration, startDate,
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
	public ScheduledTask schedule(long duration, HospitalDate startDate,
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
		return schedule(duration, startDate, neededSchedulables, occurences);
	}
	
	/**
	 * Schedules a set of schedulables on the best available slot, taking into
	 * account the required duration.
	 * 
	 * @param users
	 *            This list should contain all users, if one would like to
	 *            schedule users.
	 * @param otherSchedulables
	 *            The list of all schedulable that aren't users and that need to
	 *            be scheduled.
	 * @param duration
	 *            The length of the appointment one would like to make.
	 * @param userOccurences
	 *            The amount of times a collection of users has to be scheduled.
	 * @param otherOccurences
	 *            The amount of times a collection of other resources has to be
	 *            scheduled.
	 * 
	 * @pre If there are no linked lists of users in otherSchedulables, then the
	 *      returned Task will be scheduled back-to-back with at least 1 User.
	 *      Otherwise it will be scheduled back-to-back with at least 1 resource
	 *      (User, Machine,...)
	 * @return A ScheduledTask containing data about the scheduled appointment.
	 * @throws InvalidResourceException 
	 * @throws InvalidSchedulingRequestException 
	 * @throws InvalidTimeSlotException 
	 */
	public ScheduledTask schedule(long duration, HospitalDate startDate, LinkedList<LinkedList<Schedulable>> users, LinkedList<LinkedList<Schedulable>> otherSchedulables, LinkedList<Integer> userOccurences, LinkedList<Integer> otherOccurences) throws InvalidTimeSlotException, InvalidSchedulingRequestException, InvalidResourceException {
		LinkedList<LinkedList<Schedulable>> listOfSchedulables = new LinkedList<LinkedList<Schedulable>>();
		listOfSchedulables.addAll(users);
		listOfSchedulables.addAll(otherSchedulables);
		LinkedList<Integer> occurencesCool = new LinkedList<Integer>();
		occurencesCool.addAll(userOccurences);
		occurencesCool.addAll(otherOccurences);
		
		return schedule(duration, startDate, listOfSchedulables, occurencesCool);
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
	 */
	public ScheduledTask schedule(UnscheduledTask task) throws InvalidTimeSlotException, InvalidSchedulingRequestException, InvalidResourceException {
		LinkedList<LinkedList<Schedulable>> listOfSchedulables = new LinkedList<LinkedList<Schedulable>>(task.getResourcePool());
		long duration = task.getDuration();
		HospitalDate startDate = new HospitalDate(task.getSystemTime().getTimeSinceStart() + task.getExtraTime());
		LinkedList<Integer> occurences = task.getOccurences();
		ScheduledTask schedTask =  schedule(duration, startDate, listOfSchedulables, occurences);
		
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
	 */
	private ScheduledTask schedule(long duration,
			HospitalDate startDate, HospitalDate stopDate,
			LinkedList<LinkedList<Schedulable>> neededSchedulables,
			LinkedList<Schedulable> usedSchedulables, boolean[][] treeMatrix,
			LinkedList<Integer> fullOccurences, int iteration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidResourceException {

		int curCollectionToSchedule = fullOccurences.get(iteration);
		LinkedList<Schedulable> curSchedList = neededSchedulables
				.get(curCollectionToSchedule);
		int bestOption = findBestOption(duration, startDate,
				stopDate, treeMatrix[iteration], curSchedList);
		Schedulable chosenSchedulable = curSchedList.get(bestOption);
		TimeSlot bestTimeSlot = chosenSchedulable.getTimeTable()
				.getFirstFreeSlotBetween(startDate, stopDate, duration);

		HospitalDate newStartDate = bestTimeSlot.getStartPoint().getDate();
		HospitalDate newStopDate = bestTimeSlot.getStopPoint().getDate();
		LinkedList<Schedulable> newUsedSchedulables = Scheduler
				.copyList(usedSchedulables);
		newUsedSchedulables.add(chosenSchedulable);
		boolean[][] newTreeMatrix = copyMatrix(treeMatrix);
		updateTreeMatrix(newTreeMatrix, bestOption, fullOccurences,
				iteration);
		if (iteration < fullOccurences.size() - 1) {
			try {
				return schedule(duration, newStartDate, newStopDate,
						neededSchedulables, newUsedSchedulables, newTreeMatrix,
						fullOccurences, iteration + 1);
			} catch (InvalidSchedulingRequestException e) {
				HospitalDate nextHospitalDate = getNextHospitalDate(curSchedList, newStartDate, newStopDate);
				return schedule(duration, nextHospitalDate, stopDate,
						neededSchedulables, usedSchedulables, treeMatrix,
						fullOccurences, iteration);
			}
		} else {
			TimePoint startOfTimeSlot = new StartTimePoint(bestTimeSlot
					.getStartPoint().getDate());
			TimePoint endOfTimeSlot = new StopTimePoint(startOfTimeSlot
					.getDate().getTimeSinceStart() + duration);
			TimeSlot timeSlotToReturn = new TimeSlot(startOfTimeSlot,
					endOfTimeSlot);
			return new ScheduledTask(newUsedSchedulables, timeSlotToReturn);
		}
	}

	/**
	 * This method will expand occurences into fullOccurences
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
	 */
	private int findBestOption(long duration, HospitalDate startDate,
			HospitalDate stopDate, boolean[] treeArray,
			LinkedList<Schedulable> curSchedList)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		int bestOption = -1;
		TimeSlot bestSlot = null;
		HospitalDate bestDate = null;
		for (int i = 0; i < treeArray.length; i++) {
			if (treeArray[i]) {
				try {
					TimeSlot curSlot = curSchedList
							.get(i)
							.getFirstFreeSlotBetween(startDate, stopDate,
									duration);
					HospitalDate curDate = curSlot.getStartPoint().getDate();
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
	public HospitalDate getNextHospitalDate(Collection<Schedulable> curSchedList, HospitalDate previousDate, HospitalDate stopDate) throws InvalidTimeSlotException, InvalidSchedulingRequestException{
		HospitalDate nextDate = null;
		for(Schedulable curSchedulable : curSchedList){
			try{
				HospitalDate curNextDate = curSchedulable.getTimeTable().getNextHospitalDateAfter(previousDate);
				if(nextDate == null || curNextDate.before(curNextDate)){
					nextDate = curNextDate;
				}
			}
			catch(InvalidHospitalDateException e){
				;
			}
		}
		if(nextDate == null || stopDate.before(nextDate)){
			throw new InvalidSchedulingRequestException("No more timeslots available in this list.");
		}
		return nextDate;
	}

	/**
	 * This method will generate the treematrix.
	 * @param neededSchedulables 
	 * --> @see schedule()
	 * @param fullOccurences
	 * --> @see schedule()
	 * @return
	 * --> @see schedule()
	 */
	public boolean[][] makeTreeMatrix(
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
	public boolean[][] updateTreeMatrix(boolean[][] treeMatrix,
			int bestOption, LinkedList<Integer> fullOccurences, int iteration) {
		int occurenceNumber = fullOccurences.get(iteration);
		for (int i = 1; iteration + i < fullOccurences.size()
				&& occurenceNumber == fullOccurences.get(iteration + i); i++) {
			treeMatrix[iteration + i][bestOption] = false;
		}
		return treeMatrix;
	}

	@Basic
	public void setNewSystemTime(HospitalDate newTime) {
		if (!isValidSystemTime(newTime))
			throw new IllegalArgumentException(
					"Invalid new system time given to setNewSystemTime() in ");
		currentSystemTime = newTime;
	}

	/**
	 * @return True if t is a valid new system time.
	 */
	private boolean isValidSystemTime(HospitalDate t) {
		if (currentSystemTime == null)
			return t != null;
		return t != null
				&& t.getTimeSinceStart() >= currentSystemTime
						.getTimeSinceStart();
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
	private  boolean[][] copyMatrix(boolean[][] treeMatrix) {
		boolean[][] newTreeMatrix = new boolean[treeMatrix.length][];
		for (int i = 0; i < treeMatrix.length; i++) {
			newTreeMatrix[i] = Arrays.copyOf(treeMatrix[i],
					treeMatrix[i].length);
		}
		return newTreeMatrix;
	}

	public HospitalDate getCurrentSystemTime() {
		return currentSystemTime;
	}
}