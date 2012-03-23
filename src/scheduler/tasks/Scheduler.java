package scheduler.tasks;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.SchedulingData;
import scheduler.StartTimePoint;
import scheduler.StopTimePoint;
import scheduler.TimeSlot;
import scheduler.requirements.Requirement;
import system.Location;
import exceptions.InvalidSchedulingRequestException;

public class Scheduler
{
	public Scheduler() {
	}

	public ScheduledTask schedule(SchedulingData schedulingData)
			throws InvalidSchedulingRequestException {
		Collection<Schedulable> resourcePool = schedulingData.getAllResources();
		HospitalDate currentDate = schedulingData.getTimeLord().getSystemTime();
		UnscheduledTask unscheduledTask = schedulingData.getUnscheduledTask();
		TaskDescription description = unscheduledTask.getDescription();
		Collection<Requirement> requirements = description.getAllRequirements();
		HospitalDate minimumDate = new HospitalDate(description
				.getCreationTime().getTimeSinceStart()
				+ description.getExtraTime());
		HospitalDate startDate = HospitalDate.getMaximum(currentDate,
				minimumDate);
		HospitalDate stopDate = new HospitalDate(HospitalDate.END_OF_TIME);
		Collection<Requirement> metRequirements = getMetRequirements(
				resourcePool, requirements, startDate);
		HashMap<LinkedList<Schedulable>, Integer> availableResources = getAvailableResources(
				resourcePool, requirements, metRequirements);
		removeDoubleBookings(availableResources);
		checkIfEnoughResources(availableResources);
		HashMap<LinkedList<Schedulable>, LinkedList<Integer>> usedResourcesList = produceUsedResourcesList(availableResources);
		LinkedList<Location> possibleLocations = getPossibleLocations(availableResources, schedulingData.getLocations());
		ScheduledTask bestScheduledTask = null;
		for (Location possibleLocation : possibleLocations) {
			ScheduledTask possibleScheduledTask;
			try {
				possibleScheduledTask = schedule(availableResources,
						usedResourcesList, possibleLocation, startDate,
						stopDate, description);
			} catch (InvalidSchedulingRequestException e) {
				possibleScheduledTask = null;
			}
			if (bestScheduledTask == null
					|| possibleScheduledTask.before(bestScheduledTask)) {
				bestScheduledTask = possibleScheduledTask;
			}
		}
		if (bestScheduledTask == null) {
			throw new InvalidSchedulingRequestException(
					"The task cannot be scheduled.");
		}
		return bestScheduledTask;
	}
	
	private ScheduledTask schedule(
			HashMap<LinkedList<Schedulable>, Integer> availableResources,
			HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosenResources,
			Location location, HospitalDate startDate, HospitalDate stopDate,
			TaskDescription taskDescription)
			throws InvalidSchedulingRequestException {
		LinkedList<Schedulable> bestOptionToFind = null;
		LinkedList<Schedulable> currentResourcePool = null;
		for (LinkedList<Schedulable> resourcePool : availableResources.keySet()) {
			if (availableResources.get(resourcePool) > chosenResources.get(
					resourcePool).size()) {
				currentResourcePool = resourcePool;
				bestOptionToFind = new LinkedList<Schedulable>(resourcePool);
				removeAlreadyUsedResources(bestOptionToFind,
						chosenResources.get(resourcePool));
				break;
			}
		}
		if (bestOptionToFind == null) {
			return produceScheduledTask(taskDescription, availableResources,
					chosenResources, startDate, stopDate, location);
		}
		int bestOption = findBestOption(bestOptionToFind, location, startDate,
				stopDate, taskDescription.getDuration());
		TimeSlot bestSlot = null;
		try {
			bestSlot = bestOptionToFind.get(bestOption)
					.getFirstFreeSlotBetween(location, startDate, stopDate,
							taskDescription.getDuration());
		} catch (Exception e) {
			throw new Error(e.toString());
		}
		HospitalDate newStartDate = bestSlot.getStartPoint().getHospitalDate();
		HospitalDate newStopDate = bestSlot.getStopPoint().getHospitalDate();
		try {
			return schedule(availableResources, cloneAndAddHashMapValues(chosenResources, currentResourcePool, bestOption), location,
					newStartDate, newStopDate, taskDescription);
		} catch (InvalidSchedulingRequestException e) {
			newStartDate = new HospitalDate(newStartDate.getTimeSinceStart() + 1);
			return schedule(availableResources, chosenResources, location,
					newStartDate, newStopDate, taskDescription);
		}
	}

	// Auxiliary methods for the main scheduling method.
	private Collection<Requirement> getMetRequirements(
			Collection<Schedulable> resourcePool,
			Collection<Requirement> requirements, HospitalDate startDate) {
		Collection<Requirement> isAlreadyMet = new LinkedList<Requirement>();
		for (Requirement requirement : requirements) {
			if (requirement.isMetOn(startDate)) {
				isAlreadyMet.add(requirement);
			}
		}
		return isAlreadyMet;
	}

	private HashMap<LinkedList<Schedulable>, Integer> getAvailableResources(
			Collection<Schedulable> resourcePool,
			Collection<Requirement> requirements,
			Collection<Requirement> metRequirements)
			throws InvalidSchedulingRequestException {
		Collection<Requirement> notMetYet = new LinkedList<Requirement>();
		for (Requirement requirement : requirements) {
			if (!metRequirements.contains(requirement)) {
				notMetYet.add(requirement);
			}
		}
		HashMap<LinkedList<Schedulable>, Integer> availableResources = new HashMap<LinkedList<Schedulable>, Integer>();
		for (Requirement requirement : notMetYet) {
			LinkedList<Schedulable> isMetBy = new LinkedList<Schedulable>();
			for (Schedulable schedulable : resourcePool) {
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
			HashMap<LinkedList<Schedulable>, Integer> availableResources)
			throws InvalidSchedulingRequestException {
		for (LinkedList<Schedulable> resourcePool : availableResources.keySet()) {
			for (LinkedList<Schedulable> curSchedulablePool : availableResources
					.keySet()) {
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

	private void checkIfEnoughResources(
			HashMap<LinkedList<Schedulable>, Integer> availableResources)
			throws InvalidSchedulingRequestException {
		for (LinkedList<Schedulable> resourcePool : availableResources.keySet()) {
			if (resourcePool.size() < availableResources.get(resourcePool)) {
				throw new InvalidSchedulingRequestException(
						"There are not enough resources to schedule this task.");
			}
		}
	}

	private HashMap<LinkedList<Schedulable>, LinkedList<Integer>> produceUsedResourcesList(
			HashMap<LinkedList<Schedulable>, Integer> availableResources) {
		HashMap<LinkedList<Schedulable>, LinkedList<Integer>> usedResources = new HashMap<LinkedList<Schedulable>, LinkedList<Integer>>();
		for (LinkedList<Schedulable> resourcePool : availableResources.keySet()) {
			usedResources.put(resourcePool, new LinkedList<Integer>());
		}
		return usedResources;
	}
	
	private LinkedList<Location> getPossibleLocations(
			HashMap<LinkedList<Schedulable>, Integer> availableResources, Collection<Location> locations) {
		LinkedList<Location> possibleLocations = new LinkedList<Location>(locations);
		boolean first = true;
		for (LinkedList<Schedulable> resourcePool : availableResources.keySet()) {
			boolean canTravel = false;
			LinkedList<Location> curPossibleLocations = new LinkedList<Location>();
			HashMap<Location, Integer> amountOfResources = new HashMap<Location, Integer>();
			for (Schedulable schedulable : resourcePool) {
				if(schedulable.canTravel()){
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
				if (amountOfResources.get(location) >= availableResources
						.get(resourcePool)) {
					curPossibleLocations.add(location);
				}
			}
			if(!canTravel){
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
	
	private ScheduledTask produceScheduledTask(
			TaskDescription taskDescription,
			HashMap<LinkedList<Schedulable>, Integer> availableResources,
			HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosenResources,
			HospitalDate startDate, HospitalDate stopDate, Location location) {
		Collection<Schedulable> usedResources = new LinkedList<Schedulable>();
		for (LinkedList<Schedulable> resourcePool : availableResources.keySet()) {
			LinkedList<Integer> usedInThisPool = chosenResources
					.get(resourcePool);
			for (int i : usedInThisPool) {
				usedResources.add(resourcePool.get(i));
			}
		}
		return new ScheduledTask(taskDescription, usedResources, new TimeSlot(
				new StartTimePoint(startDate), new StopTimePoint(stopDate)),
				location);
	}

	private void removeAlreadyUsedResources(LinkedList<Schedulable> resources,
			LinkedList<Integer> toRemove) {
		LinkedList<Schedulable> removed = new LinkedList<Schedulable>();
		for (int i = 0; i < resources.size(); i++) {
			if (!toRemove.contains(i)) {
				removed.add(resources.get(i));
			}
		}
		resources = removed;
	}

	private int findBestOption(LinkedList<Schedulable> bestOptionToFind,
			Location location, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException {
		int bestOption = -1;
		TimeSlot bestSlot = null;
		HospitalDate bestDate = null;
		for (int i = 0; i < bestOptionToFind.size(); i++) {
			TimeSlot curSlot;
			try {
				curSlot = bestOptionToFind.get(i).getFirstFreeSlotBetween(
						location, startDate, stopDate, duration);
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
	
	private HashMap<LinkedList<Schedulable>, LinkedList<Integer>> cloneAndAddHashMapValues(HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosenResources, LinkedList<Schedulable> resourcePool, int bestOption){
		HashMap<LinkedList<Schedulable>, LinkedList<Integer>> clonedHashMap = new HashMap<LinkedList<Schedulable>, LinkedList<Integer>>();
		for(LinkedList<Schedulable> currentResourcePool : chosenResources.keySet()){
			if(currentResourcePool != resourcePool){
				clonedHashMap.put(currentResourcePool, new LinkedList<Integer>(chosenResources.get(currentResourcePool)));
			}
			else{
				LinkedList<Integer> newLinkedList = new LinkedList<Integer>(chosenResources.get(currentResourcePool));
				newLinkedList.add(bestOption);
				clonedHashMap.put(currentResourcePool, newLinkedList);
			}
		}
		return clonedHashMap;
	}
}