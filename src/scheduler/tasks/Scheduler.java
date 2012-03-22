package scheduler.tasks;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.SchedulingData;
import scheduler.requirements.Requirement;
import exceptions.InvalidSchedulingRequestException;

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
		HashMap<LinkedList<Schedulable>, Integer> availableResources = this.getAvailableResources(resourcePool, requirements, metRequirements);
		this.removeDoubleBookings(availableResources);
		this.checkIfEnoughResources(availableResources);
		return schedule(availableResources, produceUsedResourcesList(availableResources), startDate, stopDate, description.getDuration(), description);
	}
	
	private ScheduledTask schedule(HashMap<LinkedList<Schedulable>, Integer> availableResources, HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosenResources, HospitalDate startDate, HospitalDate stopDate, long duration, TaskDescription taskDescription) throws InvalidSchedulingRequestException{
		LinkedList<Schedulable> bestOptionToFind = null;
		for(LinkedList<Schedulable> resourcePool : availableResources.keySet()){
			if(availableResources.get(resourcePool) > chosenResources.get(resourcePool).size()){
				bestOptionToFind = resourcePool;
			}
		}
		if(bestOptionToFind == null){
			return produceScheduledTask(taskDescription, availableResources, chosenResources, startDate, stopDate);
		}
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
	private ScheduledTask produceScheduledTask(TaskDescription taskDescription, HashMap<LinkedList<Schedulable>, Integer> availableResources, HashMap<LinkedList<Schedulable>, LinkedList<Integer>> chosenResources, HospitalDate startDate, HospitalDate stopDate){
		Collection<Schedulable> usedResources = new LinkedList<Schedulable>();
		for(LinkedList<Schedulable> resourcePool : availableResources.keySet()){
			
		}
		return new ScheduledTask(taskDescription, null, null, null);
	}
}