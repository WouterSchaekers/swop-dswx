package scheduler2;

import java.util.Collection;
import scheduler.HospitalDate;
import scheduler.task.Schedulable;

public class ScheduledTask extends Task
{
	Collection<Schedulable> _resources;
	HospitalDate _startDate;
	HospitalDate _endDate;

	ScheduledTask(TaskDescription description,
			Collection<Schedulable> resources, HospitalDate startDate,
			HospitalDate endDate) {
		super(description);
		this._resources = resources;
		this._startDate = startDate;
		this._endDate = endDate;
	}

	public Collection<Schedulable> getUsedResources() {
		return this._resources;
	}

	public HospitalDate getStartDate() {
		return this._startDate;
	}

	public HospitalDate getEndDate() {
		return this._endDate;
	}
}
