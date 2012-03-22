package scheduler;

import java.util.Collection;
import java.util.LinkedList;
import exceptions.IsNotReadyException;
import scheduler.requirements.Requirement;
import scheduler.tasks.ScheduledTask;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.UnscheduledTask;

public class Scheduler
{
	public Scheduler(){}
	
	public ScheduledTask schedule(SchedulingData schedulingDate) throws IsNotReadyException{
		Collection<Schedulable> schedulablePool = schedulingDate.getAllSchedulables();
		HospitalDate currentDate = schedulingDate.getTimeLord().getSystemTime();
		UnscheduledTask unscheduledTask = schedulingDate.getUnscheduledTask();
		TaskDescription description = unscheduledTask.getDescription();
		Collection<Requirement> requirements = description.getAllRequirements();
		LinkedList<LinkedList<Schedulable>> availableSchedulables = this.getAvailableSchedulables(schedulablePool, requirements);
		HospitalDate minimumDate = new HospitalDate(description.getCreationTime().getTimeSinceStart() + description.getExtraTime());
		HospitalDate stopDate = new HospitalDate(HospitalDate.END_OF_TIME);
		return schedule(availableSchedulables, 0, HospitalDate.getMaximum(currentDate, minimumDate), stopDate, description.getDuration());
	}
	
	private ScheduledTask schedule(LinkedList<LinkedList<Schedulable>> availableSchedulables, int iteration, HospitalDate startDate, HospitalDate stopDate, long duration){
		return null;
	}
	
	private LinkedList<LinkedList<Schedulable>> getAvailableSchedulables(Collection<Schedulable> schedulablePool, Collection<Requirement> requirements) throws IsNotReadyException{
		Collection<Requirement> notMetYet = new LinkedList<Requirement>();
		for(Requirement requirement : requirements){
			if(!requirement.isMet()){
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
				throw new IsNotReadyException("Some of the requirements are not ready yet.");
			}
			availableSchedulables.add(isMetBy);
		}
		return availableSchedulables;
	}
}