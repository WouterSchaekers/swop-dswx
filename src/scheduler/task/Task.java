package scheduler.task;

import java.util.*;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * Represents a task in the system through the Schedulable element.
 */
public class Task
{
	private Schedulable myResource = null;

	public Task(Schedulable myResource) {
		if(!canHaveAsResource(myResource))
			throw new IllegalArgumentException("Invalid resource passed to Task constructor!");
		this.myResource = myResource;
	}

	@Basic
	public long duration() {
		return this.getEndTime().getTime() - this.getStartTime().getTime();
	}

	/**
	 * The date(moment) where this task starts and is scheduled.
	 * 
	 * @return
	 * 
	 */
	public Date getStartTime() {
		return null;
	}

	/**
	 * A method to return the moment this task ends.
	 * 
	 * @return The date object representing the end of this task.
	 */
	public Date getEndTime() {
		return null;
	}
	
	private boolean canHaveAsResource(Schedulable s) {
		return s != null;
	}
}
