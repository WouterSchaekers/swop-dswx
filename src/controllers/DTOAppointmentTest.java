package controllers;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.HashMap;
import org.junit.*;
import patient.PatientFile;
import scheduler.Appointment;
import scheduler.TimeType;
import task.Resource;
import users.Nurse;
import scheduler.TimePoint;

public class DTOAppointmentTest
{
	PatientFile patient;
	HashMap<TimePoint, Resource> resources;
	Appointment appointment;
	Date date;
	long duration;

	@Before
	public void setUp() {
		date = new Date(0);
		duration = 15;
		resources = new HashMap<TimePoint, Resource>();
		resources.put(new TimePoint(TimeType.start, date), new Nurse("Janine"));
		appointment = new Appointment(patient, resources, date, duration);
	}

	@Test
	public void getAppointment() {
		DTOAppointment dtoAppointment = new DTOAppointment(appointment);
		assertTrue(dtoAppointment.getAppointment().getResource().size() == 1);
		assertTrue(dtoAppointment.getAppointment().getDate().getTime() == 0);
	}
}
