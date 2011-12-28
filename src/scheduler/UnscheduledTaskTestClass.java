package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import scheduler.task.Schedulable;
import scheduler.task.unscheduled.UnscheduledTask;
/**
 * Class created for testing purposes only !!!F!!!!!!!!!@!$!Q@#$!@#$
 *
 */
public class UnscheduledTaskTestClass extends UnscheduledTask
{

	private LinkedList<LinkedList<Schedulable>> s;
	private LinkedList<Integer> a;

	public UnscheduledTaskTestClass(long duration, HospitalDate creationTime,
			Collection<Schedulable> requirements, long extraTime, boolean backToBack, LinkedList<LinkedList<Schedulable>> s,LinkedList<Integer> occ)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(null, duration, creationTime, extraTime, backToBack);
		this.s=s;
		this.a=occ;
		// TODO Auto-generated constructor stub
	}



	@Override
	public long getExtraTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		// TODO Auto-generated method stub
		return a;
	}



	@Override
	public boolean canBeScheduled() {
		// TODO Auto-generated method stub
		return false;
	}

}
