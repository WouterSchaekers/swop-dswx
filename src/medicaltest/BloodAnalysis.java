package medicaltest;

import machine.MachinePool;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import scheduler.task.unscheduled.tests.UnscheduledBloodTest;
import scheduler.task.unscheduled.tests.UnscheduledMedicalTest;
import users.UserManager;
import warehouse.Warehouse;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a bloodanalysis test.
 */
public class BloodAnalysis extends MedicalTest
{
	/**
	 * amount of times an analysis has to be run
	 */
	private final int amount;
	/**
	 * The focus of this bloodanalysis
	 */
	private final String focus;

	public final static long DURATION = 45 * HospitalDate.ONE_MINUTE;

	/**
	 * Constructor called by the BloodAnalysisFactory
	 * 
	 * @throws InvalidTimeSlotException
	 */
	BloodAnalysis(int amount, String focus) throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {
		super(BloodAnalysis.DURATION);
		this.amount = amount;
		this.focus = focus;
	}

	@Basic
	public int getAmount() {
		return amount;
	}

	@Basic
	public String getFocus() {
		return this.focus;
	}

	@Override
	public void setResult(Result r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UnscheduledMedicalTest getUnscheduled(UserManager userm,
			Warehouse warehouse, PatientFile file, TimeLord systemtime,
			TaskManager taskmanager,MachinePool pool) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidAmountException, InvalidHospitalDateException {
		return new UnscheduledBloodTest(file, systemtime.getSystemTime(), userm, pool, this);
	}

	

}