package medicaltest;

import result.Result;
import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.task.Schedulable;
import scheduler.task.scheduled.ScheduledTask;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
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
}
