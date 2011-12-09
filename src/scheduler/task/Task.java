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
	 * @see Requirement
	 */
	private Collection<Requirement> reqs = new ArrayList<Requirement>();
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
	 * Default constructor.
	 * 
	 * @param myResource
	 *            A collection of collections that contains all wanted
	 *            resourcetypes.
	 *            <p>
	 *            E.g. If one would like to make a task for 2 Nurses, one would
	 *            have to give a collection of 2 collections. Each of those 2
	 *            collections is a collection of all Nurses.
	 *            </p>
	 * @param duration
	 *            The wanted length of the reservation of this Task in the
	 *            TimeTable of the resources.
	 * @throws IllegalArgumentException
	 *             if(!canHaveAsResources(myResource)
	 */
	public Task(Collection<Collection<Schedulable>> myResource, long duration) throws IllegalArgumentException {
		if(!canHaveAsResources(myResource))
			throw new IllegalArgumentException("Invalid resource passed to Task constructor!");
		this.myResources = myResource;
		this.reqs = new LinkedList<Requirement>();
	}
	
	/**
	 * @param reqs
	 *            The objects that this Task is waiting for to be ready.
	 * @see this(myResource,duration)
	 * @throws IllegalArgumentException
	 *             if(!canHaveAsRequirements(reqs)
	 */
	public Task(Collection<Collection<Schedulable>> myResource, long duration, Collection<Requirement> reqs) {
		this(myResource,duration);
		if(!canHaveAsRequirements(reqs))
			throw new IllegalArgumentException("Invalid requirement collection passed to Task constructor!");
		this.reqs = reqs;
	}

	/**
	 * @return True if this Task suddenly can be scheduled.
	 */
	public boolean canBeScheduled() {
		for(Requirement r : this.reqs)
			if(!r.isReady())
				return false;
		return true;
	}
	
	/**
	 * This method adds a requirement to this Task.
	 * 
	 * @param r
	 *            The requirement to add.
	 * @throws IllegalArgumentException
	 *             if (!canHaveAsRequirement(r)
	 */
	public void addRequirement(Requirement r) throws IllegalArgumentException {
		if(!canHaveAsRequirement(r))
			throw new IllegalArgumentException("r is not a valid requirement in Task.addRequireent(r)!");
	}
	
	/**
	 * @return True if req is a valid Requirements for this Task.
	 */
	private boolean canHaveAsRequirement(Requirement req) {
		return req != null;
	}
	
	/**
	 * @return True if reqs is a valid collection of Requirements for this Task.
	 */
	private boolean canHaveAsRequirements(Collection<Requirement> reqs) {
		for(Requirement r : reqs)
			if(reqs == null || !this.canHaveAsRequirement(r))
				return false;
		return true;
	}

	/**
	 * @return False if the given collection is not a valid resource-source.
	 */
	private boolean canHaveAsResources(Collection<Collection<Schedulable>> s) {
		return s != null && !s.isEmpty();
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
