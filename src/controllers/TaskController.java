package controllers;

import scheduler.task.*;
import scheduler.*;

public class TaskController
{
	
	private TaskManager myTM = null;
	private Scheduler myScheduler = null;
	
	public TaskController(LoginController lc) {
		
	}
	
	/**
	 * This method creates a new Task and adds it to a TaskManager. It also
	 * immediately schedules the Task, if that's possible. If not, it queues the
	 * Task. The queued Task will be scheduled as soon as the
	 * schedule-requirements have been met.
	 */
	public void createTask()  {
		// TODO: implement
	}

}
