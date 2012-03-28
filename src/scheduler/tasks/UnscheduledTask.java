package scheduler.tasks;

import system.Hospital;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidSchedulingRequestException;

 class UnscheduledTask<T extends TaskDescription> extends Task<T>
{
	/**
	 * Creates an unscheduled task in your hospital.
	 * @param description
	 */
	 UnscheduledTask(T description) {
		super(description);
	}
	 
	public ScheduledTask<?> scheduleIn(Hospital hospital) throws InvalidSchedulingRequestException, CanNeverBeScheduledException {
			return new Scheduler().schedule(new SchedulingData(description_,hospital));
	}
}