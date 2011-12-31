package medicaltest;

import machine.MachinePool;
import patient.PatientFile;
import result.Result;
import scheduler.HospitalDate;
import scheduler.TimeLord;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.task.Schedulable;
import scheduler.task.TaskManager;
import scheduler.task.scheduled.ScheduledTask;
import scheduler.task.unscheduled.tests.UnscheduledMedicalTest;
import users.UserManager;
import warehouse.Warehouse;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a medical test.
 */
public abstract class MedicalTest
{
	public final long DURATION;
	private TimeTable timeTable;
	private Result myResult;
	private ScheduledTask ScheduledTask;

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            The name of this medical test.
	 * @param duration
	 *            The duration of this medical test.
	 * @throws InvalidDurationException
	 *             If(!isValidDuration(duration))
	 * @throws InvalidTimeSlotException
	 */
	public MedicalTest(long duration) throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException {

		if (!isValidDuration(duration))
			throw new InvalidDurationException(
					"The duration of a MedicalTest must be strictly positive!");

		this.DURATION = duration;
		this.timeTable = new TimeTable();
	}

	/**
	 * @return True if d is a valid duration for this medical test.
	 */
	private boolean isValidDuration(long d) {
		return d > 0;
	}

	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException {
		return this.timeTable.hasFreeSlotAt(startDate, stopDate);
	}

	public TimeTable getTimeTable() throws InvalidTimeSlotException {
		return new TimeTable(this.timeTable.getTimeSlots());
	}

	public void scheduleAt(TimeSlot timeSlot)
			throws InvalidSchedulingRequestException {
		if (!isValidTimeSlot(timeSlot))
			throw new InvalidSchedulingRequestException(
					"Trying to schedule an invalid TimeSlot!");

		this.timeTable.addTimeSlot(timeSlot);
	}

	private boolean isValidTimeSlot(TimeSlot t)
			throws InvalidSchedulingRequestException {
		return t != null
				&& this.canBeScheduledOn(t.getStartPoint().getDate(), t
						.getStopPoint().getDate());
	}

	public void updateTimeTable(HospitalDate newDate) {
		this.timeTable.updateTimeTable(newDate);
	}

	public abstract void setResult(Result r);

	public void setScheduledTask(ScheduledTask task) {
		this.ScheduledTask = task;

	}

	public abstract UnscheduledMedicalTest getUnscheduled(UserManager userm, Warehouse warehouse, PatientFile file, TimeLord systemtime, TaskManager taskmanager,MachinePool pool) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidAmountException, InvalidHospitalDateException ;
}
