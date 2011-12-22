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
		super(createRes(d), HospitalDate.ONE_MINUTE*30, oneoccurence(),HospitalDate.ONE_HOUR,currentsystemtime);
	}

	private static LinkedList<Integer> oneoccurence() {
		LinkedList<Integer> s =new LinkedList<Integer>();
		s.add(1);
		return s;
	}

	private static LinkedList<LinkedList<Schedulable>> createRes(Doctor d) {
		// TODO Auto-generated method stub
		LinkedList<LinkedList<Schedulable>> abra = new LinkedList<LinkedList<Schedulable>>();
		abra.add(new LinkedList<Schedulable>());
		abra.get(0).add(d);
		return abra;
	}
}
