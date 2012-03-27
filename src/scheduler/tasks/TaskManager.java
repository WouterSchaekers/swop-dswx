package scheduler.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import system.Hospital;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import exceptions.QueueException;

/**
 * This class manages new and old Tasks. If a new Task is created, it will
 * schedule it immediately, if possible. If the new Task can't be scheduled
 * straight away, it will go to the waiting queue that's being kept in this
 * class. The reason these Tasks can't be scheduled is that one of their
 * requirements has not been met yet. Mind that you need to have an association
 * to TaskManager from each Requirement class so that it can call the
 * updateQueue() function every time its state changes. Once an unscheduled task
 * meets its requirements, it will be scheduled.
 */
public class TaskManager extends Observable
{

	private Hospital hospital_;
	private Collection<TaskDescription> unscheduledDescriptions_;

	public TaskManager(Hospital hospital) {
		hospital_ = hospital;
		unscheduledDescriptions_ = new LinkedList<TaskDescription>();
	}

	/**
	 * Returns a scheduled task if the given taskdescription can be scheduled.
	 * 
	 * @param description
	 * @return
	 * @throws InvalidSchedulingRequestException 
	 */
	public ScheduledTask add(TaskDescription description) throws InvalidSchedulingRequestException {
		UnscheduledTask task = new UnscheduledTask(description);

		try {
			return task.scheduleIn(hospital_);
		} catch (InvalidSchedulingRequestException e) {
			addForLater(description);
			throw e;
		}
	}

	private void addForLater(TaskDescription description) {
		unscheduledDescriptions_.add(description);

	}

}
