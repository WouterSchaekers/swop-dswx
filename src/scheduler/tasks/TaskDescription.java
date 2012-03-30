package scheduler.tasks;

import java.util.Collection;
import scheduler.HospitalDate;
import scheduler.requirements.Requirement;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidAmountException;
import exceptions.InvalidHospitalDateException;

public abstract class TaskDescription
{
	private final long duration_;
	private final long extraTime_;
	private final HospitalDate creationDate_;

	public TaskDescription(long duration, long extraTime,
			HospitalDate creationDate) throws InvalidAmountException,
			InvalidHospitalDateException {
		if (!isValidAmountOfExtraTime(extraTime))
			throw new InvalidAmountException(
					"Invalid amount of extra time since system start given to Unscheduled Task");
		if (!isValidSystemTime(creationDate))
			throw new InvalidHospitalDateException(
					"Invalid creationTime given to Unscheduled Task");
		this.duration_ = duration;
		this.extraTime_ = extraTime;
		this.creationDate_ = creationDate;
	}

	public abstract Collection<Requirement> getAllRequirements();

	@Basic
	public final HospitalDate getCreationTime() {
		return new HospitalDate(this.creationDate_);
	}

	@Basic
	public final long getDuration() {
		return this.duration_;
	}

	@Basic
	public final long getExtraTime() {
		return this.extraTime_;
	}
	/**
	 * 
	 * @param task
	 */
	public abstract <T extends TaskDescription>  void initTask(Task<T> task);
	public abstract <T extends TaskDescription> void deInit(Task<T> task);
	private boolean isValidAmountOfExtraTime(long extraTime) {
		return extraTime >= 0;
	}

	private boolean isValidSystemTime(HospitalDate currentSystemTime) {
		return currentSystemTime != null;
	}

}