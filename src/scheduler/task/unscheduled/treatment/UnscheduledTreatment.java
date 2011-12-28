package scheduler.task.unscheduled.treatment;

import java.util.LinkedList;
import patient.Diagnose;
import scheduler.HospitalDate;
import scheduler.task.Schedulable;
import scheduler.task.unscheduled.UnscheduledTask;
import users.UserManager;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

public abstract class UnscheduledTreatment extends UnscheduledTask
{
	UserManager um;
	Diagnose diagnose;

	public UnscheduledTreatment(long duration, HospitalDate creationTime,
			boolean backToBack, Diagnose diagnose, UserManager manager)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(duration, creationTime, backToBack);
		this.um = manager;
		this.diagnose = diagnose;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> sched = new LinkedList<Schedulable>();
		rv.add(sched);
		sched.addAll(this.um.getAllNurses());
		return rv;
	}

}
