package scheduler;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import scheduler.requirements.Requirement;
import scheduler.tasks.ScheduledTask;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.UnscheduledTask;
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
		return schedule(availableResources, produceUsedResourcesList(availableResources), startDate, stopDate, description.getDuration());
	}
	
	private ScheduledTask schedule(HashMap<LinkedList<Schedulable>, Integer> availableResources, LinkedList<LinkedList<Integer>> chosenResources, HospitalDate startDate, HospitalDate stopDate, long duration){
		
		return null;
	}
	
	private Collection<Requirement> getMetRequirements(Collection<Schedulable> schedulablePool, Collection<Requirement> requirements, HospitalDate startDate){
		Collection<Requirement> isAlreadyMet = new LinkedList<Requirement>();
		for(Requirement requirement : requirements){
			if(requirement.isMetOn(startDate)){
				isAlreadyMet.add(requirement);
			}
		}
		return isAlreadyMet;
	}
	
	private HashMap<LinkedList<Schedulable>, Integer> getAvailableResources(Collection<Schedulable> schedulablePool, Collection<Requirement> requirements, Collection<Requirement> metRequirements) throws InvalidSchedulingRequestException{
		Collection<Requirement> notMetYet = new LinkedList<Requirement>();
		for(Requirement requirement : requirements){
			if(!metRequirements.contains(requirement)){
				notMetYet.add(requirement);
			}
		}
		HashMap<LinkedList<Schedulable>, Integer> availableSchedulables = new HashMap<LinkedList<Schedulable>, Integer>();
		for(Requirement requirement : notMetYet){
			LinkedList<Schedulable> isMetBy = new LinkedList<Schedulable>();
			for(Schedulable schedulable : schedulablePool){
				if(requirement.isMetBy(schedulable)){
					isMetBy.add(schedulable);
				}
			}
			if(isMetBy.size() == 0){
				throw new InvalidSchedulingRequestException("Some of the necessary conditions were not satisfied.");
			}
			availableSchedulables.put(isMetBy, requirement.getAmount());
		}
		return availableSchedulables;
	}
	
	private void removeDoubleBookings(HashMap<LinkedList<Schedulable>, Integer> availableSchedulables) throws InvalidSchedulingRequestException{
		for(LinkedList<Schedulable> schedulablePool : availableSchedulables.keySet()){
			for(LinkedList<Schedulable> curSchedulablePool : availableSchedulables.keySet()){
				for(Schedulable schedulable : curSchedulablePool){
					if(schedulablePool.contains(schedulable)){
						if(curSchedulablePool.size() == 1){
							schedulablePool.remove(schedulable);
						}
						else{
							throw new InvalidSchedulingRequestException("There is something wrong with the setup of your description. There are duplicate requirements.");
						}
					}
				}
			}
		}
	}
	
	private void checkIfEnoughResources(HashMap<LinkedList<Schedulable>, Integer> availableSchedulables) throws InvalidSchedulingRequestException{
		for(LinkedList<Schedulable> schedulablePool : availableSchedulables.keySet()){
			if(schedulablePool.size() < availableSchedulables.get(schedulablePool)){
				throw new InvalidSchedulingRequestException("There are not enough resources to schedule this task.");
			}
		}
	}
	
	private LinkedList<LinkedList<Integer>> produceUsedResourcesList(HashMap<LinkedList<Schedulable>, Integer> availableSchedulables){
		LinkedList<LinkedList<Integer>> usedResources = new LinkedList<LinkedList<Integer>>();
		for(int i = 0; i < availableSchedulables.size(); i++){
			usedResources.add(new LinkedList<Integer>());
		}
		return usedResources;
	}
}