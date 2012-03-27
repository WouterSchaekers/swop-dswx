package scheduler.tasks;

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

	
	Scheduler getScheduler()
	{
		return new Scheduler();
	}


	public ScheduledTask scheduleIn(Hospital hospital) throws InvalidSchedulingRequestException {
			return this.getScheduler().schedule(new SchedulingData(description_,hospital));
		
		
	}
}