package scheduler.task.unscheduled.tests;

import help.Collections;
import help.Filter;
import java.util.LinkedList;
import machine.BloodAnalyser;
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

public class UnscheduledBloodTest extends UnscheduledTask
{
	private UserManager manager;

	public UnscheduledBloodTest(PatientFile p, long duration,
			HospitalDate currentSystemTime, UserManager manager)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(p, duration, currentSystemTime, 45 * HospitalDate.ONE_MINUTE,
				true);
		this.manager = manager;
	}
	
	@Override
	public boolean canBeScheduled(){
		
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> l = new LinkedList<Schedulable>();
		l.addAll(this.manager.getAllNurses());
		rv.add(l);
		LinkedList<Schedulable> machine = new LinkedList<Schedulable>();
		machine.addAll(Collections.filter(pool.getAllMachines(), new Filter()
		{
			@Override
			public <T> boolean allows(T arg) {
				return arg instanceof BloodAnalyser;
			}
		}));
		rv.add(machine);
		return rv;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		LinkedList<Integer> rv = new LinkedList<Integer>();
		rv.add(1);
		rv.add(1);
		return rv;
	}

}
