package task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import scheduler.ScheduledElement;
import task.requirement.Requirement;
/**
 * Represents a task in the system through the scheduled element all info about when and what is scheduled can be requested.
 * @author Dieter
 *
 */
public abstract class Task
{
	private ScheduledElement scheduledObject;
	public Collection<Resource> getAllUsedResources(){
		return new ArrayList<Resource>(scheduledObject.getResources());
	}
	/**
	 * The time this task takes in ms.
	 * @return
	 * 
	 */
	public long duration(){
		return this.getEndTime().getTime()-this.getStart().getTime();
	}
	/**
	 * The date(moment) where this task starts and is scheduled.
	 * @return
	 * 	
	 */
	public Date getStart(){
		return new Date(this.scheduledObject.getStartTime().getTime());
	}
	/**
	 * A method to return the moment this task ends.
	 * @return
	 * 	The date object representing the end of this task.
	 */
	public Date getEndTime(){
		return new Date(this.scheduledObject.getEndTime().getTime());
	}
	protected abstract Collection<Requirement> getRequirements();
	
}
