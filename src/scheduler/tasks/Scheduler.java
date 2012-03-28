package scheduler.tasks;

import java.util.Collection;
import java.util.HashMap;
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
	public Scheduler() {
	}

	public ScheduledTask<?> schedule(UnscheduledTask<?> unschedTask)
			throws InvalidSchedulingRequestException,
			CanNeverBeScheduledException {
		Collection<Schedulable> resPool = unschedTask.getAllResources();
		HospitalDate curDate = unschedTask.getTimeLord().getSystemTime();
		TaskDescription desc = unschedTask.getDescription();
		Collection<Requirement> reqs = desc.getAllRequirements();
		HospitalDate minDate = new HospitalDate(desc.getCreationTime()
				.getTimeSinceStart() + desc.getExtraTime());
		HospitalDate startDate = HospitalDate.getMaximum(curDate, minDate);
		HospitalDate stopDate = new HospitalDate(HospitalDate.END_OF_TIME);
		Collection<Requirement> metReqs = getMetReqs(resPool, reqs, startDate);
		HashMap<LinkedList<Schedulable>, Integer> avRes = getAvRes(resPool,
				reqs, metReqs);
		removeDoubleBookings(avRes);
		checkIfEnoughRes(avRes);
		return schedule(avRes, produceUsedResList(avRes),
				getPosLocs(avRes, unschedTask.getLocations()), desc, startDate,
				stopDate);
	}

	private ScheduledTask<?> schedule(
			HashMap<LinkedList<Schedulable>, Integer> avRes,
			HashMap<LinkedList<Schedulable>, LinkedList<Integer>> usedResList,
			Collection<Location> posLocs, TaskDescription desc,
			HospitalDate startDate, HospitalDate stopDate)
			throws InvalidSchedulingRequestException {
		ScheduledTask<?> bestSchedTask = null;
		for (Location posLoc : posLocs) {
			ScheduledTask<?> posSchedTask;
			try {
				posSchedTask = schedule(avRes, usedResList, posLoc, startDate,
						stopDate, desc);
			} catch (InvalidSchedulingRequestException e) {
				posSchedTask = null;
			}
			if (bestSchedTask == null || posSchedTask.before(bestSchedTask)) {
				bestSchedTask = posSchedTask;
			}
		}
		if (bestSchedTask == null) {
			throw new InvalidSchedulingRequestException(
					"The task cannot be scheduled.");
		}
		return bestSchedTask;
	}

	private ScheduledTask<?> schedule(
			HashMap<LinkedList<Schedulable>, Integer> avRes,
			HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosRes,
			Location loc, HospitalDate startDate, HospitalDate stopDate,
			TaskDescription taskDesc) throws InvalidSchedulingRequestException {
		LinkedList<Schedulable> bestOptToFind = null;
		LinkedList<Schedulable> curResPool = null;
		for (LinkedList<Schedulable> resPool : avRes.keySet()) {
			if (avRes.get(resPool) > chosRes.get(resPool).size()) {
				curResPool = resPool;
				bestOptToFind = new LinkedList<Schedulable>(resPool);
				delAlreadyUsedRes(bestOptToFind, chosRes.get(resPool));
				break;
			}
		}
		if (bestOptToFind == null) {
			return produceSchedTask(taskDesc, avRes, chosRes, startDate,
					stopDate, loc);
		}
		int bestOption = findBestOpt(bestOptToFind, loc, startDate, stopDate,
				taskDesc.getDuration());
		TimeSlot bestSlot = null;
		try {
			bestSlot = bestOptToFind.get(bestOption).getFirstFreeSlotBetween(
					loc, startDate, stopDate, taskDesc.getDuration());
		} catch (Exception e) {
			throw new Error(e.toString());
		}
		HospitalDate newStartDate = bestSlot.getStartPoint().getHospitalDate();
		HospitalDate newStopDate = bestSlot.getStopPoint().getHospitalDate();
		try {
			return schedule(avRes,
					cloneAndAddHashMapValues(chosRes, curResPool, bestOption),
					loc, newStartDate, newStopDate, taskDesc);
		} catch (InvalidSchedulingRequestException e) {
			newStartDate = new HospitalDate(
					newStartDate.getTimeSinceStart() + 1);
			return schedule(avRes, chosRes, loc, newStartDate, newStopDate,
					taskDesc);
		}
	}

	// Auxiliary methods for the main scheduling method.
	private Collection<Requirement> getMetReqs(Collection<Schedulable> resPool,
			Collection<Requirement> reqs, HospitalDate startDate) {
		Collection<Requirement> isAlreadyMet = new LinkedList<Requirement>();
		for (Requirement requirement : reqs) {
			if (requirement.isMetOn(startDate)) {
				isAlreadyMet.add(requirement);
			}
		}
		return isAlreadyMet;
	}

	private HashMap<LinkedList<Schedulable>, Integer> getAvRes(
			Collection<Schedulable> resPool, Collection<Requirement> reqs,
			Collection<Requirement> metReqs)
			throws InvalidSchedulingRequestException {
		Collection<Requirement> notMetYet = new LinkedList<Requirement>();
		for (Requirement requirement : reqs) {
			if (!metReqs.contains(requirement)) {
				notMetYet.add(requirement);
			}
		}
		HashMap<LinkedList<Schedulable>, Integer> availableResources = new HashMap<LinkedList<Schedulable>, Integer>();
		for (Requirement requirement : notMetYet) {
			LinkedList<Schedulable> isMetBy = new LinkedList<Schedulable>();
			for (Schedulable schedulable : resPool) {
				if (requirement.isMetBy(schedulable)) {
					isMetBy.add(schedulable);
				}
			}
			if (isMetBy.size() == 0) {
				throw new InvalidSchedulingRequestException(
						"Some of the necessary conditions were not satisfied.");
			}
			availableResources.put(isMetBy, requirement.getAmount());
		}
		return availableResources;
	}

	private void removeDoubleBookings(
			HashMap<LinkedList<Schedulable>, Integer> avRes)
			throws InvalidSchedulingRequestException {
		for (LinkedList<Schedulable> resourcePool : avRes.keySet()) {
			for (LinkedList<Schedulable> curSchedulablePool : avRes.keySet()) {
				for (Schedulable schedulable : curSchedulablePool) {
					if (resourcePool.contains(schedulable)) {
						if (curSchedulablePool.size() == 1) {
							resourcePool.remove(schedulable);
						} else {
							throw new InvalidSchedulingRequestException(
									"There is something wrong with the setup of your description. There are duplicate requirements.");
						}
					}
				}
			}
		}
	}

	private void checkIfEnoughRes(
			HashMap<LinkedList<Schedulable>, Integer> avRes)
			throws CanNeverBeScheduledException {
		for (LinkedList<Schedulable> resourcePool : avRes.keySet()) {
			if (resourcePool.size() < avRes.get(resourcePool)) {
				throw new CanNeverBeScheduledException(
						"There are not enough resources to schedule this task.");
			}
		}
	}

	private HashMap<LinkedList<Schedulable>, LinkedList<Integer>> produceUsedResList(
			HashMap<LinkedList<Schedulable>, Integer> avRes) {
		HashMap<LinkedList<Schedulable>, LinkedList<Integer>> usedResources = new HashMap<LinkedList<Schedulable>, LinkedList<Integer>>();
		for (LinkedList<Schedulable> resourcePool : avRes.keySet()) {
			usedResources.put(resourcePool, new LinkedList<Integer>());
		}
		return usedResources;
	}

	private LinkedList<Location> getPosLocs(
			HashMap<LinkedList<Schedulable>, Integer> avRes,
			Collection<Location> locs) {
		LinkedList<Location> possibleLocations = new LinkedList<Location>(locs);
		boolean first = true;
		for (LinkedList<Schedulable> resourcePool : avRes.keySet()) {
			boolean canTravel = false;
			LinkedList<Location> curPossibleLocations = new LinkedList<Location>();
			HashMap<Location, Integer> amountOfResources = new HashMap<Location, Integer>();
			for (Schedulable schedulable : resourcePool) {
				if (schedulable.canTravel()) {
					canTravel = true;
				}
				Location curLocation = schedulable.getLocation();
				if (amountOfResources.containsKey(curLocation)) {
					amountOfResources.put(curLocation, 1);
				} else {
					amountOfResources.put(curLocation,
							amountOfResources.get(curLocation) + 1);
				}
			}
			for (Location location : amountOfResources.keySet()) {
				if (amountOfResources.get(location) >= avRes.get(resourcePool)) {
					curPossibleLocations.add(location);
				}
			}
			if (!canTravel) {
				if (first) {
					first = false;
					possibleLocations = curPossibleLocations;
				} else {
					possibleLocations = getIntersect(possibleLocations,
							curPossibleLocations);
				}
			}
		}
		return possibleLocations;
	}

	private LinkedList<Location> getIntersect(LinkedList<Location> list1,
			LinkedList<Location> list2) {
		LinkedList<Location> list3 = new LinkedList<Location>();
		for (Location location1 : list1) {
			if (list2.contains(location1)) {
				list3.add(location1);
			}
		}
		return list3;
	}

	// Auxiliary methods for the scheduling algorithm.
	private ScheduledTask<?> produceSchedTask(TaskDescription taskDesc,
			HashMap<LinkedList<Schedulable>, Integer> avResources,
			HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosRes,
			HospitalDate startDate, HospitalDate stopDate, Location loc) {
		Collection<Schedulable> usedResources = new LinkedList<Schedulable>();
		for (LinkedList<Schedulable> resourcePool : avResources.keySet()) {
			LinkedList<Integer> usedInThisPool = chosRes.get(resourcePool);
			for (int i : usedInThisPool) {
				usedResources.add(resourcePool.get(i));
			}
		}
		return new ScheduledTask<TaskDescription>(taskDesc, usedResources,
				new TimeSlot(new StartTimePoint(startDate), new StopTimePoint(
						stopDate)), loc);
	}

	private void delAlreadyUsedRes(LinkedList<Schedulable> res,
			LinkedList<Integer> toRemove) {
		LinkedList<Schedulable> removed = new LinkedList<Schedulable>();
		for (int i = 0; i < res.size(); i++) {
			if (!toRemove.contains(i)) {
				removed.add(res.get(i));
			}
		}
		res = removed;
	}

	private int findBestOpt(LinkedList<Schedulable> bestOptToFind,
			Location loc, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException {
		int bestOption = -1;
		TimeSlot bestSlot = null;
		HospitalDate bestDate = null;
		for (int i = 0; i < bestOptToFind.size(); i++) {
			TimeSlot curSlot;
			try {
				curSlot = bestOptToFind.get(i).getFirstFreeSlotBetween(loc,
						startDate, stopDate, duration);
				HospitalDate curStartDate = curSlot.getStartPoint()
						.getHospitalDate();
				if (bestOption == -1
						|| (curStartDate.before(bestDate) || (curStartDate
								.equals(bestDate) && curSlot.getLength() > bestSlot
								.getLength()))) {
					bestOption = i;
					bestSlot = curSlot;
					bestDate = curStartDate;
				}
			} catch (Exception e) {
				throw new Error(e.toString());
			}
		}
		if (bestOption == -1) {
			throw new InvalidSchedulingRequestException(
					"No Schedulable of this list can be schedulabled.");
		}
		return bestOption;
	}

	private HashMap<LinkedList<Schedulable>, LinkedList<Integer>> cloneAndAddHashMapValues(
			HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosRes,
			LinkedList<Schedulable> resPool, int bestOpt) {
		HashMap<LinkedList<Schedulable>, LinkedList<Integer>> clonedHashMap = new HashMap<LinkedList<Schedulable>, LinkedList<Integer>>();
		for (LinkedList<Schedulable> currentResourcePool : chosRes.keySet()) {
			if (currentResourcePool != resPool) {
				clonedHashMap.put(currentResourcePool, new LinkedList<Integer>(
						chosRes.get(currentResourcePool)));
			} else {
				LinkedList<Integer> newLinkedList = new LinkedList<Integer>(
						chosRes.get(currentResourcePool));
				newLinkedList.add(bestOpt);
				clonedHashMap.put(currentResourcePool, newLinkedList);
			}
		}
		return clonedHashMap;
	}
}