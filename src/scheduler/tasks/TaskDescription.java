package scheduler.tasks;

import java.util.Collection;
import result.Result;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.DescriptionIN;

/**
 * This class is an abstract representation of a TaskDescription. The
 * TaskDescription contains all the information needed to create and schedule a
 * Task.
 */
public abstract class TaskDescription implements DescriptionIN
{
	private final long duration_;
	private final long extraTime_;
	private final HospitalDate creationDate_;
	protected Result result_;

	/**
	 * Default constructor of the TaskDescription.
	 * 
	 * @param duration
	 *            The duration of the task.
	 * @param extraTime
	 *            The amount of time this task need to be scheduled later than
	 *            the current time.
	 * @param creationDate
	 *            The date on which this description has been created.
	 */
	public TaskDescription(long duration, long extraTime, HospitalDate creationDate){
		if (!isValidAmountOfExtraTime(extraTime))
			throw new IllegalArgumentException(
					"Invalid amount of extra time since system start given to Unscheduled Task");
		if (!isValidSystemTime(creationDate))
			throw new IllegalArgumentException("Invalid creationTime given to Unscheduled Task");
		this.duration_ = duration;
		this.extraTime_ = extraTime;
		this.creationDate_ = creationDate;
	}

	/**
	 * Returns all the requirements that are needed to forfill this task.
	 * 
	 * @return All the requirements that are needed to forfill this task.
	 */
	public abstract Collection<Requirement> getAllRequirements();

	/**
	 * Returns the duration of the TaskDescription.
	 * 
	 * @return The duration of the TaskDescription.
	 */
	@Basic
	public final long getDuration() {
		return this.duration_;
	}

	/**
	 * Returns the amount of time this task need to be scheduled later than the
	 * current time of the TaskDescription.
	 * 
	 * @return The amount of time this task need to be scheduled later than the
	 *         current time of the TaskDescription.
	 */
	@Basic
	public final long getExtraTime() {
		return this.extraTime_;
	}

	/**
	 * Returns the creationDate of the TaskDescription.
	 * 
	 * @return The creationDate of the TaskDescription.
	 */
	@Basic
	public final HospitalDate getCreationTime() {
		return new HospitalDate(this.creationDate_);
	}

	/**
	 * Initializes the task.
	 * 
	 * @param task
	 *            The task that has to be initialized.
	 */
	public abstract <T extends TaskDescription> void initTask(Task<T> task);

	/**
	 * Deinitializes the task. Undoes the things that have been done in
	 * 'initTask'.
	 * 
	 * @param task
	 *            The task that has to be deinitialized.
	 */
	public abstract <T extends TaskDescription> void deInit(Task<T> task);

	/**
	 * Checks whether the given extraTime is valid.
	 * 
	 * @param extraTime
	 *            The extraTime that has to be checked.
	 * @return True if the given extraTime is not negative.
	 */
	private boolean isValidAmountOfExtraTime(long extraTime) {
		return extraTime >= 0;
	}

	/**
	 * Checks whether the given systemTime is valid.
	 * 
	 * @param systemTime
	 *            The systemTime that has to be checked.
	 * @return True if the given systemTime is not null.
	 */
	private boolean isValidSystemTime(HospitalDate systemTime) {
		return systemTime != null;
	}
	@Override
	public boolean equals(Object o)
	{
		return o ==this;
	}
}