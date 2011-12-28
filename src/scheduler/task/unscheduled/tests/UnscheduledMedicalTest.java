package scheduler.task.unscheduled.tests;

import java.util.LinkedList;
import machine.MachinePool;
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

public abstract class UnscheduledMedicalTest extends UnscheduledTask
{
	private UserManager userManager;
	protected MachinePool machinePool;

	public UnscheduledMedicalTest(PatientFile p, long duration,
			HospitalDate currentSystemTime, UserManager userManager, MachinePool machinePool)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(p, duration, currentSystemTime, HospitalDate.ONE_HOUR,
				true);
		this.userManager = userManager;
		this.machinePool = machinePool;
	}
	
	@Override
	public boolean canBeScheduled() {
		return this.getMachinePool().size() > 0 && this.userManager.getAllNurses().size() > 0;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> l = new LinkedList<Schedulable>();
		l.addAll(this.userManager.getAllNurses());
		rv.add(l);
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
}