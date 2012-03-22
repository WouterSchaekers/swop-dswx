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
import system.Campus;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public class Scheduler
{
	public Scheduler(){}
	
	public ScheduledTask schedule(SchedulingData schedulingData) throws InvalidSchedulingRequestException{
		Collection<Schedulable> resourcePool = schedulingData.getAllResources();
		HospitalDate currentDate = schedulingData.getTimeLord().getSystemTime();
		UnscheduledTask unscheduledTask = schedulingData.getUnscheduledTask();
		TaskDescription description = unscheduledTask.getDescription();
		Collection<Requirement> requirements = description.getAllRequirements();
		HospitalDate minimumDate = new HospitalDate(description.getCreationTime().getTimeSinceStart() + description.getExtraTime());
		HospitalDate startDate = HospitalDate.getMaximum(currentDate, minimumDate);
		HospitalDate stopDate = new HospitalDate(HospitalDate.END_OF_TIME);
		Collection<Requirement> metRequirements = getMetRequirements(resourcePool, requirements, startDate);
		HashMap<LinkedList<Schedulable>, Integer> availableResources = getAvailableResources(resourcePool, requirements, metRequirements);
		removeDoubleBookings(availableResources);
		checkIfEnoughResources(availableResources);
		return schedule(availableResources, produceUsedResourcesList(availableResources), null, startDate, stopDate, description.getDuration(), description);
	}
	
	@SuppressWarnings("unchecked")
	private ScheduledTask schedule(HashMap<LinkedList<Schedulable>, Integer> availableResources, HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosenResources, Campus location, HospitalDate startDate, HospitalDate stopDate, long duration, TaskDescription taskDescription) throws InvalidSchedulingRequestException{
		LinkedList<Schedulable> bestOptionToFind = null;
		for(LinkedList<Schedulable> resourcePool : availableResources.keySet()){
			if(availableResources.get(resourcePool) > chosenResources.get(resourcePool).size()){
				bestOptionToFind = (LinkedList<Schedulable>) resourcePool.clone();
				removeAlreadyUsedResources(bestOptionToFind, chosenResources.get(resourcePool));
				break;
			}
		}
		if(bestOptionToFind == null){
			return produceScheduledTask(taskDescription, availableResources, chosenResources, startDate, stopDate, null);
		}
		int bestOption = findBestOption(bestOptionToFind, location, startDate, stopDate, duration);
		return null;
	}
	
	//Auxiliary methods for the main scheduling method.
	private Collection<Requirement> getMetRequirements(Collection<Schedulable> resourcePool, Collection<Requirement> requirements, HospitalDate startDate){
		Collection<Requirement> isAlreadyMet = new LinkedList<Requirement>();
		for(Requirement requirement : requirements){
			if(requirement.isMetOn(startDate)){
				isAlreadyMet.add(requirement);
			}
		}
		return isAlreadyMet;
	}
	
	private HashMap<LinkedList<Schedulable>, Integer> getAvailableResources(Collection<Schedulable> resourcePool, Collection<Requirement> requirements, Collection<Requirement> metRequirements) throws InvalidSchedulingRequestException{
		Collection<Requirement> notMetYet = new LinkedList<Requirement>();
		for(Requirement requirement : requirements){
			if(!metRequirements.contains(requirement)){
				notMetYet.add(requirement);
			}
		}
		HashMap<LinkedList<Schedulable>, Integer> availableResources = new HashMap<LinkedList<Schedulable>, Integer>();
		for(Requirement requirement : notMetYet){
			LinkedList<Schedulable> isMetBy = new LinkedList<Schedulable>();
			for(Schedulable schedulable : resourcePool){
				if(requirement.isMetBy(schedulable)){
					isMetBy.add(schedulable);
				}
			}
			if(isMetBy.size() == 0){
				throw new InvalidSchedulingRequestException("Some of the necessary conditions were not satisfied.");
			}
			availableResources.put(isMetBy, requirement.getAmount());
		}
		return availableResources;
	}
	
	private void removeDoubleBookings(HashMap<LinkedList<Schedulable>, Integer> availableResources) throws InvalidSchedulingRequestException{
		for(LinkedList<Schedulable> resourcePool : availableResources.keySet()){
			for(LinkedList<Schedulable> curSchedulablePool : availableResources.keySet()){
				for(Schedulable schedulable : curSchedulablePool){
					if(resourcePool.contains(schedulable)){
						if(curSchedulablePool.size() == 1){
							resourcePool.remove(schedulable);
						}
						else{
							throw new InvalidSchedulingRequestException("There is something wrong with the setup of your description. There are duplicate requirements.");
						}
					}
				}
			}
		}
	}
	
	private void checkIfEnoughResources(HashMap<LinkedList<Schedulable>, Integer> availableResources) throws InvalidSchedulingRequestException{
		for(LinkedList<Schedulable> resourcePool : availableResources.keySet()){
			if(resourcePool.size() < availableResources.get(resourcePool)){
				throw new InvalidSchedulingRequestException("There are not enough resources to schedule this task.");
			}
		}
	}
	
	private HashMap<LinkedList<Schedulable>, LinkedList<Integer>> produceUsedResourcesList(HashMap<LinkedList<Schedulable>, Integer> availableResources){
		HashMap<LinkedList<Schedulable>, LinkedList<Integer>> usedResources = new HashMap<LinkedList<Schedulable>, LinkedList<Integer>>();
		for(LinkedList<Schedulable> resourcePool: availableResources.keySet()){
			usedResources.put(resourcePool, new LinkedList<Integer>());
		}
		return usedResources;
	}
	
	//Auxiliary methods for the scheduling algorithm.
	private ScheduledTask produceScheduledTask(TaskDescription taskDescription, HashMap<LinkedList<Schedulable>, Integer> availableResources, HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosenResources, HospitalDate startDate, HospitalDate stopDate, Campus location){
		Collection<Schedulable> usedResources = new LinkedList<Schedulable>();
		for(LinkedList<Schedulable> resourcePool : availableResources.keySet()){
			LinkedList<Integer> usedInThisPool = chosenResources.get(resourcePool);
			for(int i : usedInThisPool){
				usedResources.add(resourcePool.get(i));
			}
		}
		return new ScheduledTask(taskDescription, usedResources, new TimeSlot(new StartTimePoint(startDate), new StopTimePoint(stopDate)), location);
	}
	
	private void removeAlreadyUsedResources(LinkedList<Schedulable> resources, LinkedList<Integer> toRemove){
		LinkedList<Schedulable> removed = new LinkedList<Schedulable>();
		for(int i = 0; i < resources.size(); i++){
			if(!toRemove.contains(i)){
				removed.add(resources.get(i));
			}
		}
		resources = removed;
	}
	
	private int findBestOption(LinkedList<Schedulable> bestOptionToFind, Campus location, HospitalDate startDate, HospitalDate stopDate, long duration) throws InvalidSchedulingRequestException{
		int bestOption = -1;
		TimeSlot bestSlot = null;
		HospitalDate bestDate = null;
		for (int i = 0; i < bestOptionToFind.size(); i++) {
			TimeSlot curSlot;
			try {
				curSlot = bestOptionToFind.get(i)
						.getFirstFreeSlotBetween(startDate, stopDate,
								duration);
				HospitalDate curStartDate = curSlot.getStartPoint().getHospitalDate();
				if (bestOption == -1
						|| (curStartDate.before(bestDate) || (curStartDate
								.equals(bestDate) && curSlot.getLength() > bestSlot
								.getLength()))) {
					bestOption = i;
					bestSlot = curSlot;
					bestDate = curStartDate;
				}
			} catch (InvalidTimeSlotException e) {
				System.out.println(e);
			} catch (InvalidHospitalDateArgument e) {
				System.out.println(e);
			}
			//TODO: fix deze vuile exceptions
		}
		if (bestOption == -1) {
			throw new InvalidSchedulingRequestException(
					"No Schedulable of this list can be schedulabled.");
		}
		return bestOption;
	}
}