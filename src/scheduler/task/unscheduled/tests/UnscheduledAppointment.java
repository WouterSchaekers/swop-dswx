package scheduler.task.unscheduled.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidRequirementException;
import exceptions.InvalidResourceException;
import scheduler.HospitalDate;
import scheduler.task.Requirement;
import scheduler.task.Schedulable;
import scheduler.task.UnscheduledTask;
import users.Doctor;

public class UnscheduledAppointment extends UnscheduledTask
{
	private Doctor requiredDoctor;

	public UnscheduledAppointment(Doctor d,HospitalDate currentsystemtime) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidRequirementException, InvalidAmountException, InvalidHospitalDateException
	{
		super(30*HospitalDate.ONE_MINUTE, currentsystemtime, true);
		this.requiredDoctor=d;
	}

	private static LinkedList<LinkedList<Schedulable>> createResourceListOfOneDoctor(Doctor d) {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		rv.add(new LinkedList<Schedulable>());
		rv.get(0).add(d);
		return rv;
	}





	@Override
	public
	Collection<Requirement> getRequirements() {
		return new ArrayList<Requirement>();
	}

	@Override
	public
	LinkedList<Integer> getOccurences() {
		LinkedList<Integer> rv=new LinkedList<Integer>();
		rv.add(1);
		return rv;
	}

	@Override
	public long getExtraTime() {
		return HospitalDate.ONE_HOUR;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		 return createResourceListOfOneDoctor(requiredDoctor);
	}
}
