package scheduler.task.unscheduled.treatment;

import help.Collections;
import help.Filter;
import java.util.LinkedList;
import machine.MachinePool;
import machine.SurgicalEquipment;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.task.Schedulable;
import users.UserManager;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

public class UnscheduledSurgery extends UnscheduledTreatment
{
	private MachinePool machinePool;
	
	/**
	 * @param p
	 * @param diagnose
	 * @param systemTime
	 * @param cast
	 * @param userManager
	 * @throws InvalidResourceException
	 * @throws InvalidDurationException
	 * @throws InvalidOccurencesException
	 * @throws InvalidAmountException
	 * @throws InvalidHospitalDateException
	 */
	public UnscheduledSurgery(PatientFile p, Diagnose diagnose,
			HospitalDate systemTime, UserManager userManager,
			MachinePool machinePool) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(p, diagnose, 3 * HospitalDate.ONE_HOUR, systemTime, userManager);
		this.machinePool = machinePool;
	}
	
	protected LinkedList<Schedulable> getMachinePool(){
		LinkedList<Schedulable> machinePool = new LinkedList<Schedulable>();
		machinePool.addAll(Collections.filter(this.machinePool.getAllMachines(), new Filter()
		{
			@Override
			public <T> boolean allows(T arg) {
				return arg instanceof SurgicalEquipment;
			}
		}));
		return machinePool;
	}

	@Override
	public boolean canBeScheduled() {
		return super.canBeScheduled() && this.getMachinePool().size() > 0;
	}

	@Override
	public LinkedList<Integer> getOccurences() {
		LinkedList<Integer> rv = new LinkedList<Integer>();
		rv.add(1);
		return rv;
	}
}