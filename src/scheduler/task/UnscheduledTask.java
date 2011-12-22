package scheduler.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
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
	private Collection<Requirement> myRequirements;
	private long duration;
	private LinkedList<Integer> occurences = new LinkedList<Integer>();
	private long extraTime;
	private HospitalDate creationTime;
	private boolean backToBack;
	
	/**
	 * Constructor without requirements.
	 * 
	 * @param myResources
	 *            The resourcepool for this unscheduled task.
	 * @param duration
	 *            The duration one would like this Task to eventually be
	 *            scheduled for.
	 * @param occurences
	 *            The amount of occurences for each of the Schedulables in
	 *            myResources.
	 * @param extraTime
	 *            The amount of extra time since the system start time.
	 * @param currentSystemTime
	 * 			  The time of the system when this method is called.  
	 * @throws InvalidResourceException
	 * @throws InvalidDurationException
	 * @throws InvalidOccurencesException
	 * @throws InvalidAmountException 
	 * @throws InvalidHospitalDateException 
	 */
	public UnscheduledTask(LinkedList<LinkedList<Schedulable>> myResources, LinkedList<Integer> occurences, long extraTime, HospitalDate creationTime) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidAmountException, InvalidHospitalDateException{
		if(!canHaveAsResourcePool(myResources)) 
			throw new InvalidResourceException("Invalid resource pool given to UnscheduledTask!");
		if(!super.isValidDuration(duration)) 
			throw new InvalidDurationException("Invalid duration given to UnscheduledTask!");
		if(!canHaveAsOccurences(occurences, myResources))
			throw new InvalidOccurencesException("Invalid occurcences given to UnscheduledTask!");
		if(!isValidAmountOfExtraTime(extraTime))
			throw new InvalidAmountException("Invalid amount of extra time since system start given to Unscheduled Task");
		if(!isValidSystemTime(creationTime))
			throw new InvalidHospitalDateException("Invalid systemtime given to Unscheduled Task");
		this.myResourcePool = myResources;
		this.duration = duration;
		this.occurences = occurences;
		this.creationTime = creationTime;
	}

	/**
	 * Default constructor
	 * 
	 * @param myResources
	 *            The resourcepool for this unscheduled task.
	 * @param duration
	 *            The duration one would like this Task to last for.
	 * @param reqs
	 *            The requirements that have to be ready before this unscheduled
	 *            task can become a scheduled task.
	 * @param occurences
	 *            The amount of occurences for each of the Schedulables in
	 *            myResources.
	 * @param extraTime
	 *            The amount of extra time since the system start time.
	 * @param currentSystemTime
	 *            The time of the system when this method is called.
	 * @throws InvalidResourceException
	 * @throws InvalidDurationException
	 * @throws InvalidOccurencesException
	 * @throws InvalidRequirementException
	 * @throws InvalidAmountException
	 * @throws InvalidHospitalDateException 
	 */
	public UnscheduledTask(LinkedList<LinkedList<Schedulable>> myResources, long duration, Collection<Requirement> reqs, LinkedList<Integer> occurences, long extraTime, HospitalDate creationTime) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidRequirementException, InvalidAmountException, InvalidHospitalDateException {
		if(!canHaveAsResourcePool(myResources)) 
			throw new InvalidResourceException("Invalid resource pool given to UnscheduledTask!");
		if(!super.isValidDuration(duration)) 
			throw new InvalidDurationException("Invalid duration given to UnscheduledTask!");
		if (!canHaveAsRequirements(reqs))
			throw new InvalidRequirementException("Invalid requirement collection given to Task constructor!");
		if(!canHaveAsOccurences(occurences, myResources))
			throw new InvalidOccurencesException("Invalid occurcences given to UnscheduledTask!");
		if(!isValidAmountOfExtraTime(extraTime))
			throw new InvalidAmountException("Invalid amount of extra time since system start given to Unscheduled Task");
		if(!isValidSystemTime(creationTime))
			throw new InvalidHospitalDateException("Invalid systemtime given to Unscheduled Task");
		this.myResourcePool = myResources;
		this.myRequirements = reqs;
		this.duration = duration;
		this.occurences = occurences;
		this.creationTime = creationTime;
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
	public void addRequirement(Requirement r) throws InvalidRequirementException {
		if (!canHaveAsRequirement(r))
			throw new InvalidRequirementException(
					"r is not a valid requirement in Task.addRequireent(r)!");
		this.myRequirements.add(r);
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
	 * @return True if currentSystemTime is a valid current sytem time.
	 */
	private boolean isValidSystemTime(HospitalDate currentSystemTime) {
		return currentSystemTime != null;
	}

	/**
	 * @return True if reqs is a valid collection of Requirements for this Task.
	 */
	private boolean canHaveAsRequirements(Collection<Requirement> reqs) {
		if(reqs == null)
			return false;
		for (Requirement r : reqs)
			if (!this.canHaveAsRequirement(r))
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
	 * @return True if l is a valid amount of time since the system start time
	 *         for this unscheduled task.
	 */
	private boolean isValidAmountOfExtraTime(long l) {
		return l >= 0;
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
	public long getExtraTime() {
		return this.extraTime;
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
	
	@Basic
	public HospitalDate getCreationTime() {
		return new HospitalDate(this.creationTime);
	}
	
}
