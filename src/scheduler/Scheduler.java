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
	
	public ScheduledTask schedule(SchedulingData schedulingDate) throws InvalidSchedulingRequestException{
		Collection<Schedulable> schedulablePool = schedulingDate.getAllSchedulables();
		HospitalDate currentDate = schedulingDate.getTimeLord().getSystemTime();
		UnscheduledTask unscheduledTask = schedulingDate.getUnscheduledTask();
		TaskDescription description = unscheduledTask.getDescription();
		Collection<Requirement> requirements = description.getAllRequirements();
		HospitalDate minimumDate = new HospitalDate(description.getCreationTime().getTimeSinceStart() + description.getExtraTime());
		HospitalDate startDate = HospitalDate.getMaximum(currentDate, minimumDate);
		HospitalDate stopDate = new HospitalDate(HospitalDate.END_OF_TIME);
		Collection<Requirement> metRequirements = getMetRequirements(schedulablePool, requirements, startDate);
		HashMap<LinkedList<Schedulable>, Integer> availableSchedulables = this.getAvailableSchedulables(schedulablePool, requirements, metRequirements);
		this.removeDoubleBookings(availableSchedulables);
		this.checkIfEnoughResources(availableSchedulables);
		return schedule(availableSchedulables, 0, startDate, stopDate, description.getDuration());
	}
	
	private ScheduledTask schedule(HashMap<LinkedList<Schedulable>, Integer> availableSchedulables, int iteration, HospitalDate startDate, HospitalDate stopDate, long duration){
		
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
	
	private HashMap<LinkedList<Schedulable>, Integer> getAvailableSchedulables(Collection<Schedulable> schedulablePool, Collection<Requirement> requirements, Collection<Requirement> metRequirements) throws InvalidSchedulingRequestException{
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
	
	private void removeDoubleBookings(HashMap<LinkedList<Schedulable>, Integer> availableSchedulables){
		for(LinkedList<Schedulable> schedulablePool : availableSchedulables.keySet()){
			for(LinkedList<Schedulable> curSchedulablePool : availableSchedulables.keySet()){
				if(curSchedulablePool.size() == 1){
					schedulablePool.remove(curSchedulablePool.get(0));
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
}