package scheduler.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.*;

/**
 * This class represents a Task that can't immediately be scheduled at system
 * runtime.
 */
public class UnscheduledTask extends Task
{
	private LinkedList<LinkedList<Schedulable>> myResourcePool = null;
	/**
	 * @see Requirement
	 */
	private Collection<Requirement> myRequirements = new ArrayList<Requirement>();
	private long duration = 0;
	private LinkedList<Integer> occurences = new LinkedList<Integer>();
	
	/**
	 * Default constructor.
	 * @param reqs
	 *            The objects that this Task is waiting for to be ready.
	 * @throws InvalidResourceException 
	 * @throws InvalidDurationException 
	 * @throws InvalidOccurencesException 
	 * @see this(myResource,duration)
	 */
	public UnscheduledTask(LinkedList<LinkedList<Schedulable>> myResources, long duration,Collection<Requirement> reqs, LinkedList<Integer> occurences) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException {
		if (!canHaveAsRequirements(reqs))
			throw new InvalidResourceException("Invalid requirement collection given to Task constructor!");
		if(!super.isValidDuration(duration)) 
			throw new InvalidDurationException("Invalid duration given to UnscheduledTask!");
		if(!canHaveAsResourcePool(myResources)) 
			throw new InvalidResourceException("Invalid resource pool given to UnscheduledTask!");
		if(!canHaveAsOccurences(occurences, myResources))
			throw new InvalidOccurencesException("Invalid occurcences given to UnscheduledTask!");
		this.myResourcePool = myResources;
		this.myRequirements = reqs;
		this.duration = duration;
		this.occurences = occurences;
	}

	/**
	 * @return True if this unscheduled task is ready to be scheduled.
	 */
	public boolean canBeScheduled() {
		for (Requirement r : this.myRequirements)
			if (!r.isReady())
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
		if (!canHaveAsRequirement(r))
			throw new IllegalArgumentException(
					"r is not a valid requirement in Task.addRequireent(r)!");
	}
	
	/**
	 * This method removes a requirement needed for this Task.
	 */
	public void removeRequirement(Requirement r) {
		if(this.myRequirements.contains(r))
			this.myRequirements.remove(r);
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
		for (Requirement r : reqs)
			if (reqs == null || !this.canHaveAsRequirement(r))
				return false;
		return true;
	}
	
	/**
	 * @return True if toCheck is a collection that can serve as resource pool;
	 */
	private static boolean canHaveAsResourcePool(LinkedList<LinkedList<Schedulable>> toCheck) {
		if(toCheck == null  || toCheck.isEmpty()) return false;
		for(Collection<Schedulable> colSched : toCheck) {
			if(colSched == null || colSched.isEmpty()) 
				return false;
		}
		return true;
	}
	
	/**
	 * @return True if toCheck is valid occurences-list for the given resource-collection.
	 */
	private static boolean canHaveAsOccurences(LinkedList<Integer> toCheck, LinkedList<LinkedList<Schedulable>> resources) {
		return toCheck != null && toCheck.size() == resources.size();
	}

	/**
	 * @return If this unscheduled task has r as a requirement.
	 */
	public boolean hasRequirement(Requirement r){
		return this.myRequirements.contains(r);
	}
	
	/**
	 * @return A clone of the given collection of requirements.
	 */
	private static Collection<Requirement> cloneReqCollection(Collection<Requirement> toClone) {
		Collection<Requirement> rv= new ArrayList<Requirement>();
		for(Requirement req: toClone)
			rv.add(req);
		
		return rv;
	}
	
	@Basic
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		for(Collection<Schedulable> colSched : this.myResourcePool)
			rv.add(super.cloneCollection(colSched));
		return rv;
	}
	
	@Basic
	public long getDuration() {
		return this.duration;
	}

	@Basic
	public Collection<Requirement> getRequirements()  {
		return cloneReqCollection(this.myRequirements);
	}
	
	@Basic
	public LinkedList<Integer> getOccurences() {
		return new LinkedList<Integer>(this.occurences);
	}
	
}
