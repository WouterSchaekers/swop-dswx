package scheduler;

import java.util.LinkedList;
import scheduler.task.Schedulable;
import scheduler.task.unscheduled.UnscheduledTask;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

/**
 * Class created for testing purposes only!
 * DO NOT USE THIS!
 */
public class UnscheduledTaskTestClass extends UnscheduledTask
{

	private LinkedList<Schedulable> requirements;
	private LinkedList<LinkedList<Schedulable>> schedulables;
	private LinkedList<Integer> occurences;

	public UnscheduledTaskTestClass(long duration, HospitalDate creationTime,
			long extraTime, LinkedList<Schedulable> r,
			LinkedList<LinkedList<Schedulable>> s, LinkedList<Integer> occ,
			boolean backToBack) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(null, duration, creationTime, extraTime, backToBack);
		this.requirements = r;
		this.schedulables = s;
		this.occurences = occ;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		return schedulables;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		return occurences;
	}

	@Override
	public boolean canBeScheduled() {
		return false;
	}
}