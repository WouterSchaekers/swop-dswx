package scheduler.task.unscheduled.tests;

import help.Collections;
import help.Filter;
import java.util.Collection;
import java.util.LinkedList;
import controllers.interfaces.NurseIN;
import machine.Machine;
import machine.MachinePool;
import medicaltest.UltraSoundScan;
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

public class UnscheduledUltraSound extends UnscheduledTask
{
	private MachinePool machienePool;
	private UserManager usermanager;
	public UnscheduledUltraSound(long duration, HospitalDate creationTime,
			Collection<Requirement> requirements, boolean backToBack,MachinePool machinePool,UserManager usermanager)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(duration, creationTime, requirements, backToBack);
		this.machienePool=machinePool;
		this.usermanager=usermanager;
}

	@Override
	public long getExtraTime() {
		return HospitalDate.ONE_HOUR;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> sch = new LinkedList<Schedulable>();
		for(Machine m :Collections.filter(machienePool.getAllMachines(),new Filter()
		{
			
			@Override
			public <T> boolean allows(T arg) {
				return arg instanceof UltraSoundScan;
			}
		}))
			sch.add(m);
		rv.add(sch);
		sch=new LinkedList<Schedulable>();
		for(NurseIN nurse:this.usermanager.getAllNurses())
		{
			sch.add(nurse);
		}
			
		rv.add(sch);
		return rv;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		LinkedList<Integer> ar = new LinkedList<Integer>();
		ar.add(1);
		ar.add(1);
		return ar;
	}

}
