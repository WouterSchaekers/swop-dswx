package scheduler.task.unscheduled.treatment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import patient.Diagnose;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import scheduler.HospitalDate;
import scheduler.task.Requirement;
import scheduler.task.Schedulable;
import scheduler.task.UnscheduledTask;

public abstract class UnscheduledTreatment extends UnscheduledTask
{
	public UnscheduledTreatment(long duration, HospitalDate creationTime,
			Collection<Requirement> requirements, boolean backToBack,Diagnose diagnose)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(duration, creationTime, genReq(diagnose), backToBack);
		// TODO Auto-generated constructor stub
	}

	private static Collection<Requirement> genReq(Diagnose diagnose2) {
		ArrayList<Requirement> req = new ArrayList<Requirement>();
		req.add(diagnose2);
		return req;
	}


}
