package scheduler.tasks;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.StartTimePoint;
import scheduler.StopTimePoint;
import scheduler.TimeSlot;
import scheduler.requirements.Requirement;
import system.Location;
import exceptions.AlreadyScheduledException;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public class Scheduler
{
	public Scheduler() {
	}

	/**
	 * Tries to schedule an unscheduled task.
	 * 
	 * @param unscheduledTask
	 *            The task that has to be scheduled.
	 * @throws InvalidSchedulingRequestException
	 *             The task cannot be scheduled, because there are not enough
	 *             resources available at this specific moment.
	 * @throws CanNeverBeScheduledException
	 *             The task can never be scheduled with the current amount of
	 *             resources available right now in the hospital.
	 * @throws AlreadyScheduledException
	 *             The taks has already been scheduled.
	 */
	public <T extends TaskDescription> void schedule(Task<T> unscheduledTask) throws InvalidSchedulingRequestException,
			CanNeverBeScheduledException, AlreadyScheduledException {
		if (!unscheduledTask.isQueued())
			throw new AlreadyScheduledException("This task has already been scheduled.");
		TaskData taskData = unscheduledTask.getData();
		Collection<Schedulable> resPool = taskData.getAllResources();
		HospitalDate curDate = taskData.getSystemTime();
		T desc = unscheduledTask.getDescription();
		Collection<Requirement> reqs = desc.getAllRequirements();
		HospitalDate minDate = new HospitalDate(desc.getCreationTime().getTimeSinceStart() + desc.getExtraTime());
		HospitalDate startDate = HospitalDate.getMaximum(curDate, minDate);
		HospitalDate stopDate = new HospitalDate(HospitalDate.END_OF_TIME);
		Collection<Requirement> metReqs = getMetReqs(reqs, startDate);
		Collection<Requirement> unmetReqs = getUnmetRequirements(reqs, metReqs);
		Map<LinkedList<Schedulable>, Integer> avRes = getAvRes(resPool, unmetReqs);
		//avRes = removeDoubleBookings(avRes);
		LinkedList<Location> locs = getLocationsWithEnoughResources(avRes,
				new LinkedList<Location>(taskData.getLocations()), curDate);
		TaskData data = schedule(avRes, produceUsedResList(avRes), locs, desc, startDate, stopDate, taskData);
		actuallyScheduleResources(data);
		unscheduledTask.nextState(data);
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
	 * @return The taskdata with the information about the scheduled task.
	 * @throws InvalidSchedulingRequestException
	 *             The task cannot be scheduled, because there are not enough
	 *             resources available at this specific moment.
	 */
	private <T extends TaskDescription> TaskData schedule(Map<LinkedList<Schedulable>, Integer> avRes,
			Map<LinkedList<Schedulable>, LinkedList<Integer>> usedResList, Collection<Location> posLocs, T desc,
			HospitalDate startDate, HospitalDate stopDate, TaskData taskData) throws InvalidSchedulingRequestException {
		TaskData bestTaskData = null;
		for (Location posLoc : posLocs) {
			TaskData posTaskData = null;
			try {
				posTaskData = schedule(avRes, usedResList, posLoc, desc, startDate, stopDate, taskData);
			} catch (InvalidSchedulingRequestException e) {
			}
			if (bestTaskData == null || posTaskData.before(bestTaskData))
				bestTaskData = posTaskData;
		}
		if (bestTaskData == null)
			throw new InvalidSchedulingRequestException("The task cannot be scheduled.");
		return bestTaskData;
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
	 * @return The taskdata with the information about the scheduled task of the
	 *         given list of resources at a specific location, in a specific
	 *         time interval.
	 * @throws InvalidSchedulingRequestException
	 *             The task cannot be scheduled, because there are not enough
	 *             resources available at this specific moment, at the specific
	 *             location.
	 */
	private <T extends TaskDescription> TaskData schedule(Map<LinkedList<Schedulable>, Integer> avRes,
			Map<LinkedList<Schedulable>, LinkedList<Integer>> usedResList, Location loc, T desc,
			HospitalDate startDate, HospitalDate stopDate, TaskData taskData) throws InvalidSchedulingRequestException {
		LinkedList<Schedulable> curResPool = findListToSchedule(avRes, usedResList);
		if (curResPool == null)
			return produceTaskData(avRes, usedResList, startDate, loc, taskData, desc.getDuration());
		LinkedList<Schedulable> bestOptToFind = delAlreadyUsedRes(curResPool, usedResList.get(curResPool));
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
			return schedule(avRes, cloneAndAddMapValues(usedResList, curResPool, bestOption), loc, desc, newStartDate,
					newStopDate, taskData);
		} catch (InvalidSchedulingRequestException e) {
			newStartDate = getNextStartDate(bestOptToFind, loc, newStartDate, newStopDate, desc.getDuration());
			if (!newStartDate.before(newStopDate))
				throw new InvalidSchedulingRequestException("The task cannot be scheudled at this location.");
			return schedule(avRes, usedResList, loc, desc, newStartDate, newStopDate, taskData);
		}
	}

	/**
	 * Returns the requirements that are already met on a certain date.
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
	 * Returns the requirements that are not yet met on a certain date.
	 * 
	 * @param reqs
	 *            The requirements that need to be satisfied.
	 * @param metReqs
	 *            The requirements that are already satisfied.
	 * @return A collection of requirements that were not yet satisfied.
	 */
	private LinkedList<Requirement> getUnmetRequirements(Collection<Requirement> reqs, Collection<Requirement> metReqs) {
		LinkedList<Requirement> unmetRequirements = new LinkedList<Requirement>();
		for (Requirement req : reqs)
			if (!metReqs.contains(req)) {
				unmetRequirements.add(req);
			}
		return unmetRequirements;
	}

	/**
	 * Returns the resources that are available and can be met to satisfy the
	 * requirements.
	 * 
	 * @param resPool
	 *            All the resources of the hospital.
	 * @param notMetYet
	 *            The requirements that are not satisfied yet.
	 * @return A HashMap that maps all resources that can satisfy the
	 *         requirements with the needed amount.
	 * @throws InvalidSchedulingRequestException
	 *             Some of the requirement cannot be satisfied right now.
	 * @throws CanNeverBeScheduledException
	 *             Some of the requirements can never be satisfied with the
	 *             existing resources.
	 */
	private Map<LinkedList<Schedulable>, Integer> getAvRes(Collection<Schedulable> resPool,
			Collection<Requirement> notMetYet) throws InvalidSchedulingRequestException {
		Map<LinkedList<Schedulable>, Integer> availableResources = new HashMap<LinkedList<Schedulable>, Integer>();
		for (Requirement requirement : notMetYet) {
			LinkedList<Schedulable> isMetBy = new LinkedList<Schedulable>();
			for (Schedulable schedulable : resPool)
				if (requirement.isMetBy(schedulable))
					isMetBy.add(schedulable);
			if (isMetBy.size() < requirement.getAmount() && !requirement.isCrucial())
				throw new InvalidSchedulingRequestException("There are not enough non-crucial resources available.");
			availableResources.put(isMetBy, requirement.getAmount());
		}
		return availableResources;
	}

	/**
	 * Removes the specific resources that are booked in two different places.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @return A copy of avRes with the double resources removed.
	 */
	private Map<LinkedList<Schedulable>, Integer> removeDoubleBookings(Map<LinkedList<Schedulable>, Integer> avRes) {
		Map<LinkedList<Schedulable>, Integer> newAvRes = new HashMap<LinkedList<Schedulable>, Integer>();
		LinkedList<LinkedList<Schedulable>> keys = new LinkedList<LinkedList<Schedulable>>(avRes.keySet());
		LinkedList<Integer> values = new LinkedList<Integer>();
		LinkedList<Integer> toCheck = new LinkedList<Integer>();
		for (int i = 0; i < keys.size(); i++) {
			LinkedList<Schedulable> key = keys.get(i);
			values.add(avRes.get(key));
			if (key.size() == 1)
				toCheck.add(i);
		}
		for (int i = 0; i < toCheck.size(); i++) {
			int index = toCheck.get(i);
			Schedulable unique = keys.get(index).get(0);
			for (int j = 0; j < keys.size(); j++) {
				LinkedList<Schedulable> key = keys.get(j);
				if (key.contains(unique) && i != j) {
					System.out.println("gefokt");
					key.remove(unique);
				}
			}
		}
		for (int i = 0; i < keys.size(); i++) {
			newAvRes.put(keys.get(i), values.get(i));
		}
		return newAvRes;
	}

	/**
	 * Checks if there are enough resources available in the hospital.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @param locs
	 *            All the locations of the hospital.
	 * @throws CanNeverBeScheduledException
	 *             Some of the requirements can never be satisfied with the
	 *             existing resources.
	 */
	private LinkedList<Location> getLocationsWithEnoughResources(Map<LinkedList<Schedulable>, Integer> avRes,
			LinkedList<Location> locs, HospitalDate now) throws CanNeverBeScheduledException {
		LinkedList<Location> possibleLocations = new LinkedList<Location>();
		for (Location loc : locs) {
			boolean enoughResources = true;
			for (LinkedList<Schedulable> resourcePool : avRes.keySet()) {
				int amount = 0;
				for (Schedulable resource : resourcePool) {
					if (resource.canTravel() || resource.getLocationAt(now) == loc) {
						amount++;
					}
				}
				if (amount < avRes.get(resourcePool)) {
					enoughResources = false;
					break;
				}
			}
			if (enoughResources)
				possibleLocations.add(loc);
		}
		if (possibleLocations.size() == 0)
			throw new CanNeverBeScheduledException(
					"This task can never be scheduled in this hospital with the current amount of schedulables.");
		return possibleLocations;
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
	private Map<LinkedList<Schedulable>, LinkedList<Integer>> produceUsedResList(
			Map<LinkedList<Schedulable>, Integer> avRes) {
		Map<LinkedList<Schedulable>, LinkedList<Integer>> usedResources = new HashMap<LinkedList<Schedulable>, LinkedList<Integer>>();
		for (LinkedList<Schedulable> resourcePool : avRes.keySet()) {
			usedResources.put(resourcePool, new LinkedList<Integer>());
		}
		return usedResources;
	}

	/**
	 * Returns the list that still needs a resource that has to be scheduled.
	 * 
	 * @param avRes
	 *            HashMap that contains resources and the amount needed of them.
	 * @param usedResList
	 *            A mapping of the possible resources and the places of the used
	 *            resources.
	 * @return The list that still needs a resource that has to be scheduled.
	 *         Schedulables that are already scheduled are removed.
	 */
	private LinkedList<Schedulable> findListToSchedule(Map<LinkedList<Schedulable>, Integer> avRes,
			Map<LinkedList<Schedulable>, LinkedList<Integer>> usedResList) {
		LinkedList<Schedulable> bestOptToFind = null;
		for (LinkedList<Schedulable> resPool : avRes.keySet()) {
			if (avRes.get(resPool) > usedResList.get(resPool).size()) {
				bestOptToFind = resPool;
				break;
			}
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
	private LinkedList<Schedulable> delAlreadyUsedRes(LinkedList<Schedulable> res, LinkedList<Integer> toRemove) {
		LinkedList<Schedulable> removed = new LinkedList<Schedulable>();
		for (int i = 0; i < res.size(); i++)
			if (!toRemove.contains(i))
				removed.add(res.get(i));
		return removed;
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
	private TaskData produceTaskData(Map<LinkedList<Schedulable>, Integer> avResources,
			Map<LinkedList<Schedulable>, LinkedList<Integer>> chosRes, HospitalDate startDate, Location loc,
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
				if (bestSlot == null)
					bestSlot = curSlot;
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

	/**
	 * Amongst a list of schedulables, search the start date of the first free
	 * slot that is after the given startDate.
	 * 
	 * @param scheds
	 *            A list of schedulables that can be chosen from.
	 * @param loc
	 *            The location of the schedulable.
	 * @param startDate
	 *            The start date.
	 * @param stopDate
	 *            The stop date.
	 * @param duration
	 *            The required duration.
	 * @return The first date of the next free slot.
	 * @throws InvalidSchedulingRequestException
	 *             There a no more free slots left.
	 */
	private HospitalDate getNextStartDate(LinkedList<Schedulable> scheds, Location loc, HospitalDate startDate,
			HospitalDate stopDate, long duration) throws InvalidSchedulingRequestException {
		LinkedList<Schedulable> usableSchedulables = new LinkedList<Schedulable>();
		for (Schedulable schedulable : scheds) {
			if (schedulable.getLocationAt(startDate) == loc) {
				usableSchedulables.add(schedulable);
			}
		}
		HospitalDate nextStartDate = startDate;
		for (Schedulable schedulable : usableSchedulables) {
			try {
				TimeSlot firstTimeSlot = schedulable.getFirstFreeSlotBetween(loc, startDate, stopDate, duration);
				HospitalDate newStartDate = firstTimeSlot.getStartDate();
				if (nextStartDate.equals(startDate) || newStartDate.before(nextStartDate))
					nextStartDate = newStartDate;
			} catch (InvalidTimeSlotException e) {
				throw new Error("Scheduler, getNextStartDate");
			} catch (InvalidHospitalDateArgument e) {
				throw new Error("Scheduler, getNextStartDate");
			}
		}
		if (nextStartDate == startDate)
			throw new InvalidSchedulingRequestException(
					"There are no more free slots available for these schedulables.");
		return nextStartDate;
	}

	/**
	 * Clones the incoming HashMap and adds the number of the chosen schedulable
	 * to the correct list.
	 * 
	 * @param chosRes
	 *            The resources that have been chosen.
	 * @param resPool
	 *            The resourcespool where it's all about.
	 * @param bestOpt
	 *            The index of the resource that has been chosen and need to be
	 *            added to the HashMap.
	 * @return A clone of chosRes, with the extra bestOpt number in the value of
	 *         resPool.
	 */
	private Map<LinkedList<Schedulable>, LinkedList<Integer>> cloneAndAddMapValues(
			Map<LinkedList<Schedulable>, LinkedList<Integer>> chosRes, LinkedList<Schedulable> resPool, int bestOpt) {
		Map<LinkedList<Schedulable>, LinkedList<Integer>> clonedMap = new HashMap<LinkedList<Schedulable>, LinkedList<Integer>>();
		for (LinkedList<Schedulable> currentResourcePool : chosRes.keySet()) {
			if (currentResourcePool != resPool) {
				clonedMap.put(currentResourcePool, new LinkedList<Integer>(chosRes.get(currentResourcePool)));
			} else {
				LinkedList<Integer> newLinkedList = new LinkedList<Integer>(chosRes.get(currentResourcePool));
				newLinkedList.add(bestOpt);
				clonedMap.put(currentResourcePool, newLinkedList);
			}
		}
		return clonedMap;
	}

	/**
	 * Schedules all the resources and collects all the requirements that are in
	 * the taskData, with the given information in the taskData.
	 * 
	 * @param taskData
	 *            Provides the information to execute the Scheduling.
	 */
	private void actuallyScheduleResources(TaskData taskData) {
		Collection<Requirement> listOfRequirements = taskData.getDescription().getAllRequirements();
		for (Requirement req : listOfRequirements)
			req.collect();
		Collection<Schedulable> listOfSchedulables = taskData.getAllResources();
		HospitalDate startDate = taskData.getStartDate();
		HospitalDate stopDate = taskData.getStopDate();
		TimeSlot timeSlot = new TimeSlot(new StartTimePoint(startDate), new StopTimePoint(stopDate));
		Location location = taskData.getLocation();
		for (Schedulable sched : listOfSchedulables)
			try {
				sched.scheduleAt(timeSlot, location);
			} catch (InvalidSchedulingRequestException e) {
				throw new Error(
						"The scheduling algorithm has chosen a timeslot that is not available for the current schedulable.");
			}
	}
}