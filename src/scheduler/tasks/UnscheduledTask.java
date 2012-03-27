package scheduler.tasks;

import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidSchedulingRequestException;
import system.Hospital;

 class UnscheduledTask extends Task
{
	/**
	 * Creates an unscheduled task in your hospital.
	 * @param description
	 */
	 UnscheduledTask(TaskDescription description) {
		super(description);
	}
	 
	public ScheduledTask scheduleIn(Hospital hospital) throws InvalidSchedulingRequestException, CanNeverBeScheduledException {
			return new Scheduler().schedule(new SchedulingData(description_,hospital));
	}
}