package schedulerold.task.unscheduled.treatment;

import java.util.LinkedList;
import patient.Diagnose;
import patient.PatientFile;
import scheduler2.HospitalDate;
import schedulerold.task.Schedulable;
import schedulerold.task.scheduled.ScheduledTask;
import schedulerold.task.unscheduled.UnscheduledTask1;
import treatment.Treatment;
import users.UserManager;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

public abstract class UnscheduledTreatment extends UnscheduledTask1
{
	private UserManager userManager;
	private Diagnose diagnose;
	private final Treatment mytreatment;

	public UnscheduledTreatment(PatientFile p, Diagnose diagnose,
			long duration, HospitalDate systemTime, UserManager userManager,
			Treatment treatment) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(p, duration, systemTime, HospitalDate.ONE_HOUR, true);
		this.diagnose = diagnose;
		this.userManager = userManager;
		this.mytreatment = treatment;
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

	@Override
	public HospitalDate getFirstSchedulingDateSince(HospitalDate hospitalDate) {
		return hospitalDate;
	}

	public Treatment getMytreatment() {
		return mytreatment;
	}

	@Override
	public void setScheduled(ScheduledTask task) {
		this.mytreatment.setScheduled(task);
	}

}