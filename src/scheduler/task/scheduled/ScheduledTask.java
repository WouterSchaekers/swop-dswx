package scheduler.task.scheduled;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import be.kuleuven.cs.som.annotate.Basic;
import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler.task.Schedulable;
import scheduler.task.Task;
import exceptions.*;

/**
 * This class represents a Task that has been scheduled. It is to be stored in
 * the according TimeTables so that the Schedulables know what Task they need to
 * perform at a certain given TimeSlot.
 */
public class ScheduledTask extends Task
{
	private LinkedList<Schedulable> myResources = null;
	private TimeSlot mySlot = null;
	
	/**
	 * Default constructor.
	 * @param resources
	 * The resources that are being used by this Task.
	 * @param timeSlot
	 * The TimeSlot during which this Task has been scheduled at.
	 * @throws InvalidResourceException 
	 */
	public ScheduledTask(PatientFile p, Collection<Schedulable> resources, TimeSlot timeSlot) throws InvalidResourceException{
		super(p);
		if(!super.canHaveAsResources(resources)){
			throw new InvalidResourceException("Invalid resource passed to Task constructor!");
		}
		this.myResources = Task.cloneCollection(resources);
		this.addTaskToResources();
		this.mySlot = timeSlot;
	}
	
	@Basic
	public TimeSlot getTimeSlot() {
		return mySlot.clone();
	}
	
	@Basic
	public LinkedList<Schedulable> getResources() {
		return super.cloneCollection(myResources);
	}
	
	private void addTaskToResources(){
		for(int i = 0; i < this.myResources.size(); i++){
			this.myResources.get(i).addScheduledTask(this);
		}
	}
	
	/**
	 * @return The length of the duration of this ScheduledTask.
	 */
	public long getDuration() {
		return mySlot.getStopPoint().getTime() - mySlot.getStartPoint().getTime();
	}
	
	/**
	 * @return The start date of this Task.
	 */
	public HospitalDate getStartDate() {
		return new HospitalDate(this.mySlot.getStartPoint().getDate());
	}
	public String toString()
	{
		StringBuilder asdf = new StringBuilder();
		for(Schedulable s : myResources)
			{asdf.append(s);
			asdf.append('\n');
			}return mySlot.toString();
	}
}
