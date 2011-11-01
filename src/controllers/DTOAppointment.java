package controllers;

import scheduler.Appointment;

public class DTOAppointment
{
	private Appointment app;

	public DTOAppointment(Appointment addAppointment) {
		this.app=addAppointment;
	}

}
