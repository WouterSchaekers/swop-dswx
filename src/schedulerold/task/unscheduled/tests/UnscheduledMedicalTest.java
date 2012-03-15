package schedulerold.task.unscheduled.tests;

import java.util.LinkedList;
import machine.MachinePool;
import medicaltest.MedicalTest;
import patient.PatientFile;
import scheduler.HospitalDate;
import schedulerold.task.Schedulable;
import schedulerold.task.scheduled.ScheduledTask;
import schedulerold.task.unscheduled.UnscheduledTask1;
import users.UserManager;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

public abstract class UnscheduledMedicalTest extends UnscheduledTask1
{
	private UserManager userManager;
	protected MachinePool machinePool;
	private MedicalTest analysis;

	public UnscheduledMedicalTest(PatientFile p, long duration,
			HospitalDate currentSystemTime, UserManager userManager,
			MachinePool machinePool, MedicalTest analysis)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(p, duration, currentSystemTime, HospitalDate.ONE_HOUR, true);
		this.userManager = userManager;
		this.machinePool = machinePool;
		this.analysis = analysis;
	}

	@Override
	public HospitalDate getFirstSchedulingDateSince(HospitalDate hospitalDate) {
		return hospitalDate;
	}

	@Override
	public boolean canBeScheduled() {
		return this.getMachinePool().size() > 0
				&& this.userManager.getAllNurses().size() > 0;
	}

	//TODO: Weinig uitbreidbaar gecoded? Wat als doctor nodig?
	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> list = new LinkedList<Schedulable>();
		list.addAll(this.userManager.getAllNurses());
		rv.add(list);
		return rv;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		LinkedList<Integer> rv = new LinkedList<Integer>();
		rv.add(1);
		rv.add(1);
		return rv;
	}

	protected abstract LinkedList<Schedulable> getMachinePool();

	@Override
	public void setScheduled(ScheduledTask task) {
		this.analysis.setScheduledTask(task);
	}
}