package scheduler;

import java.util.Collection;
import java.util.LinkedList;
import exceptions.ConditionNotMetException;
import scheduler.requirements.Requirement;
import scheduler.tasks.ScheduledTask;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.UnscheduledTask;

public class Scheduler
{
	public Scheduler(){}
	
	public ScheduledTask schedule(SchedulingData schedulingDate) throws ConditionNotMetException{
		Collection<Schedulable> schedulablePool = schedulingDate.getAllSchedulables();
		HospitalDate currentDate = schedulingDate.getTimeLord().getSystemTime();
		UnscheduledTask unscheduledTask = schedulingDate.getUnscheduledTask();
		TaskDescription description = unscheduledTask.getDescription();
		Collection<Requirement> requirements = description.getAllRequirements();
		HospitalDate minimumDate = new HospitalDate(description.getCreationTime().getTimeSinceStart() + description.getExtraTime());
		HospitalDate startDate = HospitalDate.getMaximum(currentDate, minimumDate);
		HospitalDate stopDate = new HospitalDate(HospitalDate.END_OF_TIME);
		LinkedList<LinkedList<Schedulable>> availableSchedulables = this.getAvailableSchedulables(schedulablePool, requirements, startDate);
		return schedule(availableSchedulables, 0, HospitalDate.getMaximum(currentDate, minimumDate), stopDate, description.getDuration());
	}
	
	private ScheduledTask schedule(LinkedList<LinkedList<Schedulable>> availableSchedulables, int iteration, HospitalDate startDate, HospitalDate stopDate, long duration){
		return null;
	}
	
	private LinkedList<LinkedList<Schedulable>> getAvailableSchedulables(Collection<Schedulable> schedulablePool, Collection<Requirement> requirements, HospitalDate startDate) throws ConditionNotMetException{
		Collection<Requirement> notMetYet = new LinkedList<Requirement>();
		for(Requirement requirement : requirements){
			if(!requirement.isMetOn(startDate)){
				notMetYet.add(requirement);
			}
		}
		LinkedList<LinkedList<Schedulable>> availableSchedulables = new LinkedList<LinkedList<Schedulable>>();
		for(Requirement requirement : notMetYet){
			LinkedList<Schedulable> isMetBy = new LinkedList<Schedulable>();
			for(Schedulable schedulable : schedulablePool){
				if(requirement.isMetBy(schedulable)){
					isMetBy.add(schedulable);
				}
			}
			if(isMetBy.size() == 0){
				throw new ConditionNotMetException("Some of the necessary conditions were not satisfied.");
			}
			availableSchedulables.add(isMetBy);
		}
		return availableSchedulables;
	}
}