package scheduler.task;

import java.util.*;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class represents an appointment that can't be scheduled yet at the time of creation.
 * It's kept in the TaskManager as long as it can't be scheduled. 
 */
public class Task
{
	private Collection<Collection<Schedulable>> myResources = null;
	private long duration = 0;

	/**
	 * We will ask the user if they would like to give this task a specific name
	 * or identifier. If they do, then that information gets stored here.
	 * 
	 * Once this task has been scheduled we can then say: "Task with ID "<user
	 * defined ID>" has been scheduled" So the user knows which tasks have been
	 * scheduled after the taskQueue in TaskManager has been updated.
	 */
	private String userDefinedID = "defaultID";
	
	/**
	 * Default constructor. Initialise the fields.
	 * @param myResource
	 * @param duration
	 */
	public Task(Collection<Collection<Schedulable>> myResource, long duration) {
		if(!canHaveAsResources(myResource))
			throw new IllegalArgumentException("Invalid resource passed to Task constructor!");
		this.myResources = myResource;
	}

	/**
	 * @return False if the given collection is not a valid resource-source.
	 */
	private boolean canHaveAsResources(Collection<Collection<Schedulable>> s) {
		return s != null && !s.isEmpty();
	}
	
	public boolean canBeScheduled() {
		return false;
	}
	
	@Basic
	public long getDuration() {
		return this.duration;
	}
	
	@Basic
	public Collection<Collection<Schedulable>> getResources() {
		return new ArrayList<Collection<Schedulable>>(this.myResources);
	}
	
	@Basic
	public String getUserDefinedID() {
		return this.userDefinedID;
	}
	
	@Basic
	public void setUserDefinedID(String id) {
		this.userDefinedID = id;
	}
}
