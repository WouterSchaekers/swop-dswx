package medicaltest;

import result.Result;
import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.tasks.ScheduledTask;
import controllers.interfaces.MedicalTestIN;
import exceptions.InvalidDurationException;
import exceptions.InvalidResultException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a medical test.
 */
//TODO:Fix this to be an instance of taskdescription
public abstract class MedicalTest implements MedicalTestIN
{
	public final long DURATION;
	private TimeTable timeTable;
	private Result result;
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
	public MedicalTest(long duration) throws InvalidDurationException {
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
				&& this.canBeScheduledOn(t.getStartPoint().getHospitalDate(), t
						.getStopPoint().getHospitalDate());
	}

	public void updateTimeTable(HospitalDate newDate) {
		this.timeTable.updateTimeTable(newDate);
	}

	/**
	 * @return True if r is a valid Result.
	 */
	private boolean isValidResult(Result r) {
		return r != null;
	}

	public void setResult(Result r) throws InvalidResultException {
		if(! this.isValidResult(r))
			throw new InvalidResultException("Invalid result for medical test!");
		this.result = r;
	}

	public void setScheduledTask(ScheduledTask task) {
		this.ScheduledTask = task;

	}
	
	protected ScheduledTask getScheduledTask() {
		return ScheduledTask;
	}
	//TODO: remove this methjod
	public abstract String appointmentInfo();

	public boolean hasFinished() {
		return this.result != null;
	}

}
