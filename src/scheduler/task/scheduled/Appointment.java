package scheduler.task.scheduled;

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
