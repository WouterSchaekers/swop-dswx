package scheduler.task.unscheduled.tests;

import help.Collections;
import help.Filter;
import java.util.LinkedList;
import machine.BloodAnalyser;
import machine.MachinePool;
import medicaltest.BloodAnalysis;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.task.Schedulable;
import users.UserManager;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

public class UnscheduledBloodTest extends UnscheduledMedicalTest
{
	public UnscheduledBloodTest(PatientFile p, HospitalDate currentSystemTime,
			UserManager userManager, MachinePool machinePool,
			BloodAnalysis analysis) throws InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException {
		super(p, BloodAnalysis.DURATION, currentSystemTime, userManager,
				machinePool, analysis);

	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = super.getResourcePool();
		rv.add(this.getMachinePool());
		return rv;
	}

	@Override
	protected LinkedList<Schedulable> getMachinePool() {
		LinkedList<Schedulable> curMachinePool = new LinkedList<Schedulable>();
		curMachinePool.addAll(Collections.filter(
				this.machinePool.getAllMachines(), new Filter()
				{
					@Override
					public <T> boolean allows(T arg) {
						return arg instanceof BloodAnalyser;
					}
				}));
		return curMachinePool;

	}
}