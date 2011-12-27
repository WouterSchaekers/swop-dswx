package scheduler.task.unscheduled.tests;

import java.util.Collection;
import java.util.LinkedList;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import scheduler.HospitalDate;
import scheduler.task.Requirement;
import scheduler.task.Schedulable;
import scheduler.task.UnscheduledTask;

public class UnscheduledUltraSound extends UnscheduledTask
{

	public UnscheduledUltraSound(long duration, HospitalDate creationTime,
			Collection<Requirement> requirements, boolean backToBack)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(duration, creationTime, requirements, backToBack);
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
		return null;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		// TODO Auto-generated method stub
		return null;
	}

}
