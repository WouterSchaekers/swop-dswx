package scheduler.task;

import java.util.LinkedList;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidRequirementException;
import exceptions.InvalidResourceException;
import scheduler.HospitalDate;
import users.Doctor;

public class UnscheduledAppointment extends UnscheduledTask
{
	public UnscheduledAppointment(Doctor d,HospitalDate currentsystemtime) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidRequirementException, InvalidAmountException, InvalidHospitalDateException
	{
		super(createResourceListOfOneDoctor(d), HospitalDate.ONE_MINUTE*30, oneoccurence(),HospitalDate.ONE_HOUR,currentsystemtime);
	}

	private static LinkedList<Integer> oneoccurence() {
		LinkedList<Integer> s =new LinkedList<Integer>();
		s.add(1);
		return s;
	}

	private static LinkedList<LinkedList<Schedulable>> createResourceListOfOneDoctor(Doctor d) {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		rv.add(new LinkedList<Schedulable>());
		rv.get(0).add(d);
		return rv;
	}
}
