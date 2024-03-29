package thrashcan.schedulerold.task.scheduled;

import scheduler.ScheduledTask;
import controllers.interfaces.AppointmentIN;

public class Appointment implements AppointmentIN
{
	private ScheduledTask task;

	public Appointment(ScheduledTask schedule) {
		task = schedule;
	}

	public String toString() {
		return task.toString();
	}

}
