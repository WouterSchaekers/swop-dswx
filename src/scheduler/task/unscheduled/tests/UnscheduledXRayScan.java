package scheduler.task.unscheduled.tests;

import help.Collections;
import help.Filter;
import java.util.LinkedList;
import machine.MachinePool;
import machine.XRayScanner;
import medicaltest.BloodAnalysis;
import medicaltest.XRayScan;
import patient.PatientFile;
import scheduler.task.Schedulable;
import scheduler.task.scheduled.ScheduledTask;
import scheduler2.HospitalDate;
import users.UserManager;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

public class UnscheduledXRayScan extends UnscheduledMedicalTest
{
	public UnscheduledXRayScan(PatientFile p, HospitalDate currentSystemTime,
			UserManager userManager, MachinePool machinePool, XRayScan analysis)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(p, BloodAnalysis.DURATION, currentSystemTime, userManager,
				machinePool, analysis);
	}

	@Override
	public HospitalDate getFirstSchedulingDateSince(HospitalDate hospitalDate) {
		return this.patient.getFirstNewXRaySchedDate(hospitalDate);
	}

	@Override
	public LinkedList<LinkedList<Schedulable>> getResourcePool() {
		LinkedList<LinkedList<Schedulable>> rv = super.getResourcePool();
		rv.add(this.getMachinePool());
		return rv;
	}

	protected LinkedList<Schedulable> getMachinePool() {
		LinkedList<Schedulable> curMachinePool = new LinkedList<Schedulable>();
		curMachinePool.addAll(Collections.filter(
				this.machinePool.getAllMachines(), new Filter()
				{
					@Override
					public <T> boolean allows(T arg) {
						return arg instanceof XRayScanner;
					}
				}));
		return curMachinePool;
	}

	@Override
	public void setScheduled(ScheduledTask task) {

	}
}