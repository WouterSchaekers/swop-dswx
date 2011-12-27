package scheduler.task.unscheduled.tests;

import help.Collections;
import help.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import machine.Machine;
import machine.MachinePool;
import machine.XRayScanner;
import medicaltest.XRayScan;
import scheduler.HospitalDate;
import scheduler.task.Requirement;
import scheduler.task.Schedulable;
import scheduler.task.UnscheduledTask;
import users.UserManager;
import controllers.interfaces.NurseIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidRequirementException;
import exceptions.InvalidResourceException;

public class UnscheduledXRayScan extends UnscheduledTask
{

	private MachinePool pool;
	private UserManager um;

	public UnscheduledXRayScan(XRayScan xray, HospitalDate currentSystemTime, UserManager um, MachinePool mp) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidAmountException, InvalidHospitalDateException, InvalidRequirementException {
			super(0, currentSystemTime,new ArrayList<Requirement>(), true);
			this.um=um;
			this.pool=mp;
		}

	/**
	 * This method will generate the linked list of linked lists that's needed
	 * by the scheduler.
	 * 
	 * @param um
	 *            The usermanager where all the nurses are stored.
	 * @param mp
	 *            The machinepool were all the xrays are scheduled.
	 * @return The linked list of linked lists that's needed by the scheduler.
	 */
	private static LinkedList<LinkedList<Schedulable>> genResources(
			UserManager um, MachinePool mp) {
		LinkedList<LinkedList<Schedulable>> rv = new LinkedList<LinkedList<Schedulable>>();
		Collection<NurseIN> nurses = um.getAllNurses();
		LinkedList<Schedulable> scheds = new LinkedList<Schedulable>();
		for (Schedulable s : nurses) {
			scheds.add(s);
		}
		rv.add(scheds);

		LinkedList<Schedulable> mach = new LinkedList<Schedulable>();
		Collection<Machine> colMach = Collections.filter(mp.getAllMachines(), new Filter()
		{
			@Override
			public <T> boolean allows(T arg) {
				// TODO Auto-generated method stub
				return arg instanceof XRayScanner;
			}
		});
		
		for(Machine m : colMach) {
			mach.add(m);
		}
		rv.add(mach);
		return rv;
	}

	/**
	 * @return A linked list that contains the occurences for an XRayScan.
	 */
	private static LinkedList<Integer> generateOccurences() {
		return new LinkedList<Integer>(Arrays.asList(1,1));
	}

	@Override
	public long getExtraTime() {
		return 0;
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		return genResources(this.um, this.pool);
	}

	@Override
	public Collection<Requirement> getRequirements() {
		return new ArrayList<Requirement>();
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		return generateOccurences();
	}


}
