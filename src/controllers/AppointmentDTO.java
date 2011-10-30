package controllers;

import scheduler.Appointment;

public class AppointmentDTO
{
	private Appointment app;

	public AppointmentDTO(Appointment addAppointment) {
		this.app=addAppointment;
	}

}
