package scheduler.task;

import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import exceptions.*;
import scheduler.Scheduler;

/**
 * This class serves as a form of waiting room for unscheduled tasks. If a Task
 * can't be scheduled at creation, it will be stored in here. Once there's
 * change to the state of the system, the usecase should notify this class to
 * update it's pool of "waiting tasks". It then, if needed can tell the
 * scheduler to schedule tasks if they suddenly do meet the requirements to be
 * scheduled.
 */
public class TaskManager
{
	private Scheduler scheduler = new Scheduler();
	private Collection<Task> taskQueue = new LinkedList<Task>();

	/**
	 * This method has to be called in order to update the Queue at the right
	 * times in the flow of the program. It will check if any of the Tasks in
	 * the Queue can be scheduled. If so, it will ask the scheduler to schedule
	 * them.
	 * 
	 * @return A map from Task to Date where the Date is the Date the Task has
	 *         been scheduled at.
	 * @throws InvalidSchedulingRequestException
	 * @throws InvalidDurationException
	 * @throws QueueException
	 * @throws InvalidSchedulingRequestException 
	 * @throws InvalidTimeSlotException 
	 */
	public HashMap<Task,Date> updateQueue() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		HashMap<Task,Date> returnValue = new HashMap<Task, Date>();
		
		for(Task curTask: this.taskQueue) {
			if(curTask.canBeScheduled()) {
				returnValue.put(curTask, this.scheduler.schedule(curTask.getDuration(), curTask.getResources()));
				this.removeTask(curTask);
			}
		}
		return returnValue;
	}
	
	/**
	 * This method will add a Task to this TaskManager's queue.
	 * @param t
	 * The task to add.
	 */
	public void addTask(Task t) {
		if (!isValidTask(t))
			throw new IllegalArgumentException("Task t in addTask of the TaskManager is not a valid queable Task!");
		this.taskQueue.add(t);
	}

	/**
	 * This method will remove Task t from the task queue.
	 * 
	 * @param t
	 *            The task to remove.
	 * @throws QueueException
	 *             if(!taskQueue.contains(t))
	 */
	private void removeTask(Task t) throws QueueException {
		if(!this.taskQueue.remove(t))
			throw new QueueException("Task t is not in taskQueue of TaskManager in removeTask!");
	}
	
	/**
	 * @return True if t is a valid Task that can be queued in this TM.
	 */
	private boolean isValidTask(Task t) {
		return t != null && !t.getResources().isEmpty();
	}
	
	@Basic
	public Collection<Task> getTaskQueue() {
		return new ArrayList<Task>(taskQueue);
	}
}
