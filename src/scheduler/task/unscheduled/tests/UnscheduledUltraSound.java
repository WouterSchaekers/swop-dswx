package scheduler.task.unscheduled.tests;

import help.Collections;
import help.Filter;
import java.util.LinkedList;
import machine.MachinePool;
import machine.UltraSoundScanner;
import medicaltest.BloodAnalysis;
import medicaltest.UltraSoundScan;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.task.Schedulable;
import scheduler.task.scheduled.ScheduledTask;
import users.UserManager;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

public class UnscheduledUltraSound extends UnscheduledMedicalTest
{
	public UnscheduledUltraSound(PatientFile p, HospitalDate currentSystemTime, UserManager userManager, MachinePool machinePool,UltraSoundScan ultraSoundScan)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(p, BloodAnalysis.DURATION, currentSystemTime, userManager, machinePool,ultraSoundScan);
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = super.getResourcePool();
		rv.add(this.getMachinePool());
		return rv;
	}
	
	protected LinkedList<Schedulable> getMachinePool(){
		LinkedList<Schedulable> curMachinePool = new LinkedList<Schedulable>();
		curMachinePool.addAll(Collections.filter(this.machinePool.getAllMachines(), new Filter()
		{
			@Override
			public <T> boolean allows(T arg) {
				return arg instanceof UltraSoundScanner;
			}
		}));
		return curMachinePool;
	}

	@Override
	public void setScheduled(ScheduledTask task) {
		// TODO Auto-generated method stub
		
	}
}