package scheduler.task.unscheduled.tests;

import help.Collections;
import help.Filter;
import java.util.ArrayList;
import java.util.LinkedList;
import machine.BloodAnalyser;
import machine.MachinePool;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import scheduler.HospitalDate;
import scheduler.task.Requirement;
import scheduler.task.Schedulable;
import scheduler.task.UnscheduledTask;
import users.UserManager;

public class UnscheduledBloodTest extends UnscheduledTask
{
	private UserManager manager;
	private MachinePool pool;
	public UnscheduledBloodTest(long duration, HospitalDate creationTime,
			boolean backToBack,UserManager manager,MachinePool pool) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(duration, creationTime,new ArrayList<Requirement>(), backToBack);
		this.manager=manager;
	}

	@Override
	public long getExtraTime() {
		return HospitalDate.ONE_HOUR;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> l=new LinkedList<Schedulable>();
		l.addAll(this.manager.getAllNurses());		
		rv.add(l);
		LinkedList<Schedulable> machine = new LinkedList<Schedulable>();
		machine.addAll(Collections.filter(pool.getAllMachines(),new Filter()
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
