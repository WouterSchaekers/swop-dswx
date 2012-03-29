package scheduler.tasks;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.StartTimePoint;
import scheduler.StopTimePoint;
import scheduler.TimeSlot;
import scheduler.requirements.Requirement;
import system.Location;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidSchedulingRequestException;

public class Scheduler
{
	public Scheduler() {}

	/**
	 * Tries to schedule an unscheduled task.
	 * 
	 * @param unschedTask
	 *            The task that has to be scheduled.
	 * @return The scheduled task of the unscheduled task.
	 * @throws InvalidSchedulingRequestException
	 *             The task cannot be scheduled, because there are not enough
	 *             resources available at this specific moment.
	 * @throws CanNeverBeScheduledException
	 *             The task can never be scheduled with the current amount of
	 *             resources available right now in the hospital.
	 */
	public <T extends TaskDescription> void schedule(Task<T> unschedTask) throws InvalidSchedulingRequestException,
			CanNeverBeScheduledException {
		TaskData taskData = unschedTask.getData();
		Collection<Schedulable> resPool = taskData.getAllResources();
		HospitalDate curDate = taskData.getTimeLord().getSystemTime();
		T desc = unschedTask.getDescription();
		Collection<Requirement> reqs = desc.getAllRequirements();
		HospitalDate minDate = new HospitalDate(desc.getCreationTime().getTimeSinceStart() + desc.getExtraTime());
		HospitalDate startDate = HospitalDate.getMaximum(curDate, minDate);
		HospitalDate stopDate = new HospitalDate(HospitalDate.END_OF_TIME);
		Collection<Requirement> metReqs = getMetReqs(reqs, startDate);
		LinkedHashMap<LinkedList<Schedulable>, Integer> avRes = getAvRes(resPool, reqs, metReqs);
		removeDoubleBookings(avRes);
		checkIfEnoughRes(avRes);
		TaskData data = schedule(avRes, produceUsedResList(avRes), getPosLocs(avRes, unschedTask.getData().getLocations()), desc,
				startDate, stopDate, taskData);
		TaskState newState = new ScheduledState(data);
		unschedTask.setState(newState);
	}

	/**
	 * For each location, this method will try to schedule the task. The task
	 * that comes first will be chosen.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @param usedResList
	 *            A mapping of the possible resources and the places of the used
	 *            resources.
	 * @param posLocs
	 *            A LinkedList of locations that are suitable for scheduling.
	 * @param desc
	 *            The TaskDescription.
	 * @param startDate
	 *            The task has to be scheduled after this date.
	 * @param stopDate
	 *            The task has to be scheudeld before this date.
	 * @return The scheduled task of the unscheduled task.
	 * @throws InvalidSchedulingRequestException
	 *             The task cannot be scheduled, because there are not enough
	 *             resources available at this specific moment.
	 */
	private <T extends TaskDescription> TaskData schedule(LinkedHashMap<LinkedList<Schedulable>, Integer> avRes,
			LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>> usedResList, Collection<Location> posLocs,
			T desc, HospitalDate startDate, HospitalDate stopDate, TaskData taskData)
			throws InvalidSchedulingRequestException {
		TaskData bestSchedTask = null;
		for (Location posLoc : posLocs) {
			TaskData posSchedTask;
			try {
				//TODO: toodoo voor wouter.
				posSchedTask = schedule(avRes, usedResList, posLoc, desc, startDate, stopDate, taskData);
			} catch (InvalidSchedulingRequestException e) {
				posSchedTask = null;
			}
			if (bestSchedTask == null || posSchedTask.before(bestSchedTask))
				bestSchedTask = posSchedTask;
		}
		if (bestSchedTask == null)
			throw new InvalidSchedulingRequestException("The task cannot be scheduled.");
		return bestSchedTask;
	}

	/**
	 * Tries to schedule an a list of resources at a specific location, in a
	 * specific time interval.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @param usedResList
	 *            A mapping of the possible resources and the places of the used
	 *            resources.
	 * @param loc
	 *            The location that has to be scheduled on.
	 * @param desc
	 *            The TaskDescription.
	 * @param startDate
	 *            The task has to be scheduled after this date.
	 * @param stopDate
	 *            The task has to be scheudeld before this date.
	 * @return The scheduled task of the given list of resources at a specific
	 *         location, in a specific time interval.
	 * @throws InvalidSchedulingRequestException
	 *             The task cannot be scheduled, because there are not enough
	 *             resources available at this specific moment, at the specific
	 *             location.
	 */
	private <T extends TaskDescription> TaskData schedule(LinkedHashMap<LinkedList<Schedulable>, Integer> avRes,
			LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>> usedResList, Location loc,
			T desc, HospitalDate startDate, HospitalDate stopDate, TaskData taskData)
			throws InvalidSchedulingRequestException {
		LinkedList<Schedulable> curResPool = null;
		LinkedList<Schedulable> bestOptToFind = findListToSchedule(avRes, usedResList, curResPool);
		if (bestOptToFind == null)
			return produceTaskData(avRes, usedResList, startDate, loc,taskData, desc.getDuration());
		int bestOption = findBestOpt(bestOptToFind, loc, startDate, stopDate, desc.getDuration());
		TimeSlot bestSlot = null;
		try {
			bestSlot = bestOptToFind.get(bestOption).getFirstFreeSlotBetween(loc, startDate, stopDate,
					desc.getDuration());
		} catch (Exception e) {
			throw new Error(e.toString());
		}
		HospitalDate newStartDate = bestSlot.getStartPoint().getHospitalDate();
		HospitalDate newStopDate = bestSlot.getStopPoint().getHospitalDate();
		try {
			return schedule(avRes, cloneAndAddLinkedHashMapValues(usedResList, curResPool, bestOption), loc, desc,
					newStartDate, newStopDate, taskData);
		} catch (InvalidSchedulingRequestException e) {
			newStartDate = new HospitalDate(newStartDate.getTimeSinceStart() + 1);
			return schedule(avRes, usedResList, loc, desc, newStartDate, newStopDate,taskData);
		}
	}

	/**
	 * Returns the resources that are already met on a certain date.
	 * 
	 * @param reqs
	 *            The requirements that need to be satisfied.
	 * @param startDate
	 *            The date on which the requirements need to be satisfied.
	 * @return A collection of requirements that were satisfied on this speficic
	 *         date.
	 */
	private Collection<Requirement> getMetReqs(Collection<Requirement> reqs, HospitalDate startDate) {
		Collection<Requirement> isAlreadyMet = new LinkedList<Requirement>();
		for (Requirement requirement : reqs) {
			if (requirement.isMetOn(startDate)) {
				isAlreadyMet.add(requirement);
			}
		}
		return isAlreadyMet;
	}

	/**
	 * Returns the resources that are available and can be met to satisfy the
	 * requirements.
	 * 
	 * @param resPool
	 *            All the resources of the hospital.
	 * @param reqs
	 *            The requirements that have to be satisfied.
	 * @param metReqs
	 *            The requirements that are already satisfied.
	 * @return A HashMap that maps all resources that can satisfy the
	 *         requirements with the needed amount.
	 * @throws InvalidSchedulingRequestException
	 *             Some of the requirement cannot be satisfied right now.
	 * @throws CanNeverBeScheduledException
	 *             Some of the requirements can never be satisfied with the
	 *             existing resources.
	 */
	private LinkedHashMap<LinkedList<Schedulable>, Integer> getAvRes(Collection<Schedulable> resPool,
			Collection<Requirement> reqs, Collection<Requirement> metReqs) throws InvalidSchedulingRequestException,
			CanNeverBeScheduledException {
		Collection<Requirement> notMetYet = new LinkedList<Requirement>();
		for (Requirement requirement : reqs)
			if (!metReqs.contains(requirement))
				notMetYet.add(requirement);
		LinkedHashMap<LinkedList<Schedulable>, Integer> availableResources = new LinkedHashMap<LinkedList<Schedulable>, Integer>();
		for (Requirement requirement : notMetYet) {
			LinkedList<Schedulable> isMetBy = new LinkedList<Schedulable>();
			for (Schedulable schedulable : resPool)
				if (requirement.isMetBy(schedulable))
					isMetBy.add(schedulable);
			if (isMetBy.size() == 0) {
				if (requirement.isCrucial())
					throw new CanNeverBeScheduledException("There are not enough resources to schedule this task.");
				throw new InvalidSchedulingRequestException("Some of the necessary conditions were not satisfied.");
			}
			availableResources.put(isMetBy, requirement.getAmount());
		}
		return availableResources;
	}

	/**
	 * Removes the specific resources that are booked in two different places.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @throws InvalidSchedulingRequestException
	 *             Something is wrong with the setup of the description.
	 */
	private void removeDoubleBookings(LinkedHashMap<LinkedList<Schedulable>, Integer> avRes)
			throws InvalidSchedulingRequestException {
		for (LinkedList<Schedulable> resourcePool : avRes.keySet())
			for (LinkedList<Schedulable> curSchedulablePool : avRes.keySet())
				for (Schedulable schedulable : curSchedulablePool)
					if (resourcePool.contains(schedulable))
						if (curSchedulablePool.size() == 1)
							resourcePool.remove(schedulable);
						else
							throw new InvalidSchedulingRequestException(
									"There is something wrong with the setup of your description. There are duplicate requirements.");
	}

	/**
	 * Checks if there are enough resources available in the hospital.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @throws CanNeverBeScheduledException
	 *             Some of the requirements can never be satisfied with the
	 *             existing resources.
	 */
	private void checkIfEnoughRes(LinkedHashMap<LinkedList<Schedulable>, Integer> avRes)
			throws CanNeverBeScheduledException {
		for (LinkedList<Schedulable> resourcePool : avRes.keySet()) {
			if (resourcePool.size() < avRes.get(resourcePool)) {
				throw new CanNeverBeScheduledException("There are not enough resources to schedule this task.");
			}
		}
	}

	/**
	 * Returns an empty mapping of the possible resources and the places of the
	 * used resources.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @return An empty mapping of the possible resources and the places of the
	 *         used resources.
	 */
	private LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>> produceUsedResList(
			LinkedHashMap<LinkedList<Schedulable>, Integer> avRes) {
		LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>> usedResources = new LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>>();
		for (LinkedList<Schedulable> resourcePool : avRes.keySet()) {
			usedResources.put(resourcePool, new LinkedList<Integer>());
		}
		return usedResources;
	}

	/**
	 * Returns all the possible locations. The locations that contain not enough
	 * resources are eliminated.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @param locs
	 *            All locations of the hospital.
	 * @return A LinkedList of locations that are suitable for scheduling.
	 */
	private LinkedList<Location> getPosLocs(LinkedHashMap<LinkedList<Schedulable>, Integer> avRes,
			Collection<Location> locs) {
		LinkedList<Location> possibleLocations = new LinkedList<Location>(locs);
		for (LinkedList<Schedulable> resourcePool : avRes.keySet()) {
			boolean canTravel = false;
			LinkedList<Location> curPossibleLocations = new LinkedList<Location>();
			LinkedHashMap<Location, Integer> amountOfResources = new LinkedHashMap<Location, Integer>();
			for (Schedulable schedulable : resourcePool) {
				canTravel = schedulable.canTravel();
				Location curLocation = schedulable.getLocation();
				if (amountOfResources.containsKey(curLocation))
					amountOfResources.put(curLocation, 1);
				else
					amountOfResources.put(curLocation, amountOfResources.get(curLocation) + 1);
			}
			for (Location location : amountOfResources.keySet())
				if (amountOfResources.get(location) >= avRes.get(resourcePool))
					curPossibleLocations.add(location);
			if (!canTravel)
				possibleLocations = getIntersect(possibleLocations, curPossibleLocations);
		}
		return possibleLocations;
	}

	/**
	 * Returns the intersect of two lists (logical AND operation).
	 * 
	 * @param list1
	 *            The first list.
	 * @param list2
	 *            The second list.
	 * @return The intersect of list1 and list2.
	 */
	private LinkedList<Location> getIntersect(LinkedList<Location> list1, LinkedList<Location> list2) {
		LinkedList<Location> list3 = new LinkedList<Location>();
		for (Location location1 : list1) {
			if (list2.contains(location1)) {
				list3.add(location1);
			}
		}
		return list3;
	}

	/**
	 * Returns the list that still needs a resource that has to be scheduled.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @param usedResList
	 *            A mapping of the possible resources and the places of the used
	 *            resources.
	 * @param curResPool
	 *            The resourcePool that has been chosen to investigate.
	 * @return The list that still needs a resource that has to be scheduled.
	 *         Schedulables that are already scheduled are removed.
	 */
	private LinkedList<Schedulable> findListToSchedule(LinkedHashMap<LinkedList<Schedulable>, Integer> avRes,
			LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>> usedResList, LinkedList<Schedulable> curResPool) {
		LinkedList<Schedulable> bestOptToFind = null;
		for (LinkedList<Schedulable> resPool : avRes.keySet())
			if (avRes.get(resPool) > usedResList.get(resPool).size()) {
				curResPool = resPool;
				bestOptToFind = new LinkedList<Schedulable>(resPool);
				delAlreadyUsedRes(bestOptToFind, usedResList.get(resPool));
				break;
			}
		return bestOptToFind;
	}

	/**
	 * Deletes the resources that are already used for scheduling. The index of
	 * the used resources are listed in the toRemove list.
	 * 
	 * @param res
	 *            The resources that have to be reviewed.
	 * @param toRemove
	 *            The indexes of the resources that have to be removed.
	 */
	private void delAlreadyUsedRes(LinkedList<Schedulable> res, LinkedList<Integer> toRemove) {
		LinkedList<Schedulable> removed = new LinkedList<Schedulable>();
		for (int i = 0; i < res.size(); i++)
			if (!toRemove.contains(i))
				removed.add(res.get(i));
		res = removed;
	}

	/**
	 * Creates a scheduled task from the resources that have been chosen.
	 * 
	 * @param avResources
	 *            HashMap that contains resources and the amount needed of them.
	 * @param chosRes
	 *            A mapping between the list of resources and the index of the
	 *            resources that have been chosen.
	 * @param desc
	 *            The TaskDescription.
	 * @param startDate
	 *            The task has to be scheduled on this date.
	 * @param loc
	 *            The location where the task is held.
	 * @return A scheduled task from the resources that have been chosen.
	 */
	private TaskData produceTaskData(LinkedHashMap<LinkedList<Schedulable>, Integer> avResources,
			LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosRes, HospitalDate startDate, Location loc,
			TaskData taskData, long duration) {
		Collection<Schedulable> usedResources = new LinkedList<Schedulable>();
		for (LinkedList<Schedulable> resourcePool : avResources.keySet()) {
			LinkedList<Integer> usedInThisPool = chosRes.get(resourcePool);
			for (int i : usedInThisPool)
				usedResources.add(resourcePool.get(i));
		}
		TaskData newData = taskData.clone();
		newData.setLocation(loc);
		newData.setTimeSlot(new TimeSlot(new StartTimePoint(startDate), new StopTimePoint(new HospitalDate(startDate
				.getTimeSinceStart() + duration))));
		newData.setUsedResources(usedResources);
		return newData;
	}

	/**
	 * Returns the index of the schedulable that has the best timeslot.
	 * 
	 * @param bestOptToFind
	 *            The list of schedulables that make a chance to be the best
	 *            schedulable.
	 * @param loc
	 *            The location that has to be scheduled on.
	 * @param startDate
	 *            The task has to be scheduled after this date.
	 * @param stopDate
	 *            The task has to be scheudeld before this date.
	 * @param duration
	 *            The duration of the task.
	 * @return The index of the schedulable that has the best timeslot, on the
	 *         given location, between a certain start -and endDate and with a
	 *         given duration.
	 * @throws InvalidSchedulingRequestException
	 *             There is no good slot available for the given time
	 *             limitations and given location.
	 */
	private int findBestOpt(LinkedList<Schedulable> bestOptToFind, Location loc, HospitalDate startDate,
			HospitalDate stopDate, long duration) throws InvalidSchedulingRequestException {
		int bestOption = -1;
		TimeSlot bestSlot = null;
		for (int i = 0; i < bestOptToFind.size(); i++) {
			TimeSlot curSlot;
			try {
				curSlot = bestOptToFind.get(i).getFirstFreeSlotBetween(loc, startDate, stopDate, duration);
				HospitalDate curStartDate = curSlot.getStartPoint().getHospitalDate();
				HospitalDate bestStartDate = bestSlot.getStartPoint().getHospitalDate();
				if (bestOption == -1
						|| (curStartDate.before(bestStartDate) || (curStartDate.equals(bestStartDate) && curSlot
								.getLength() > bestSlot.getLength()))) {
					bestOption = i;
					bestSlot = curSlot;
				}
			} catch (Exception e) {
				throw new Error(e.toString());
			}
		}
		if (bestOption == -1)
			throw new InvalidSchedulingRequestException("No Schedulable of this list can be schedulabled.");
		return bestOption;
	}

	private LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>> cloneAndAddLinkedHashMapValues(
			LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosRes, LinkedList<Schedulable> resPool,
			int bestOpt) {
		LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>> clonedLinkedHashMap = new LinkedHashMap<LinkedList<Schedulable>, LinkedList<Integer>>();
		for (LinkedList<Schedulable> currentResourcePool : chosRes.keySet()) {
			if (currentResourcePool != resPool) {
				clonedLinkedHashMap.put(currentResourcePool, new LinkedList<Integer>(chosRes.get(currentResourcePool)));
			} else {
				LinkedList<Integer> newLinkedList = new LinkedList<Integer>(chosRes.get(currentResourcePool));
				newLinkedList.add(bestOpt);
				clonedLinkedHashMap.put(currentResourcePool, newLinkedList);
			}
		}
		return clonedLinkedHashMap;
	}
}