package schedulerold.task.unscheduled;

import java.util.LinkedList;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.ScheduledTask;
import schedulerold.task.Task;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;

/**
 * This class represents a Task that can't immediately be scheduled at system
 * runtime.
 */
public abstract class UnscheduledTask1 extends Task
{
	private long duration;
	private long extraTime;
	private boolean backToBack;
	private HospitalDate systemTime;

	/**
	 * Constructor without requirements.
	 * 
	 * @param myResources
	 *            The resourcepool for this unscheduled task.
	 * @param duration
	 *            The duration one would like this Task to eventually be
	 *            scheduled for.
	 * @param occurences
	 *            The amount of occurences for each of the Schedulables in
	 *            myResources.
	 * @param extraTime
	 *            The amount of extra time since the system start time.
	 * @param currentSystemTime
	 *            The time of the system when this method is called.
	 * @throws InvalidResourceException
	 * @throws InvalidDurationException
	 * @throws InvalidOccurencesException
	 * @throws InvalidAmountException
	 * @throws InvalidHospitalDateException
	 */
	public UnscheduledTask1(PatientFile p, long duration,
			HospitalDate systemTime, long extraTime, boolean backToBack)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException {
		super(p);
		if (!isValidAmountOfExtraTime(extraTime))
			throw new InvalidAmountException(
					"Invalid amount of extra time since system start given to Unscheduled Task");
		if (!isValidSystemTime(systemTime))
			throw new InvalidHospitalDateException(
					"Invalid systemtime given to Unscheduled Task");
		this.systemTime = systemTime;
		this.backToBack = backToBack;
		this.duration = duration;
		this.extraTime = extraTime;
	}

	/**
	 * @return True if currentSystemTime is a valid current sytem time.
	 */
	private boolean isValidSystemTime(HospitalDate currentSystemTime) {
		return currentSystemTime != null;
	}

	/**
	 * @return True if l is a valid amount of time since the system start time
	 *         for this unscheduled task.
	 */
	private boolean isValidAmountOfExtraTime(long l) {
		return l >= 0;
	}

	@Basic
	public long getExtraTime() {
		return this.extraTime;
	}

	@Basic
	public long getDuration() {
		return this.duration;
	}

	@Basic
	public HospitalDate getCreationTime() {
		return new HospitalDate(this.systemTime);
	}

	@Basic
	public boolean mustBeBackToBack() {
		return this.backToBack;
	}

	/**
	 * @return True if this unscheduled task is ready to be scheduled.
	 */
	public abstract boolean canBeScheduled();

	@Basic
	public abstract LinkedList<Integer> getOccurences();

	public abstract LinkedList<LinkedList<Schedulable>> getResourcePool();

	public abstract HospitalDate getFirstSchedulingDateSince(
			HospitalDate hospitalDate);

	public abstract void setScheduled(ScheduledTask task);
}