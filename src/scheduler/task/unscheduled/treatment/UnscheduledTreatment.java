package scheduler.task.unscheduled.treatment;

import java.util.LinkedList;
import patient.Diagnose;
import patient.PatientFile;
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
	private UserManager userManager;
	private Diagnose diagnose;

	public UnscheduledTreatment(PatientFile p, Diagnose diagnose,
			long duration, HospitalDate systemTime, UserManager userManager) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(p, duration, systemTime, HospitalDate.ONE_HOUR, true);
		this.diagnose = diagnose;
		this.userManager = userManager;
	}

	@Override
	public boolean canBeScheduled() {
		return this.diagnose.isApproved()
				&& this.userManager.getAllNurses().size() > 0;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> sched = new LinkedList<Schedulable>();
		rv.add(sched);
		sched.addAll(this.userManager.getAllNurses());
		return rv;
	}
}