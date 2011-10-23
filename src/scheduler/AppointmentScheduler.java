package scheduler;

import help.DateCalculator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import patient.PatientFile;
import users.Doctor;

public class AppointmentScheduler
{
	public AppointmentScheduler(){
		appointments = new HashMap<Doctor, ArrayList<Appointment>>();
	}
	
	private HashMap<Doctor, ArrayList<Appointment>> appointments;
	private static int appointmentDuration = 30;
	
	public void addAppointment(PatientFile patient, Doctor doctor){
		Date nextDate = getNextAppointmentMoment(doctor);
		Appointment appointment = new Appointment(patient, doctor, nextDate);
		appointments.get(doctor).add(appointment);
	}
	
	public ArrayList<Appointment> getAppointments(Doctor doctor){
		return (ArrayList<Appointment>) appointments.get(doctor).clone();
	}
	
	public Date getLatestAppointmentMoment(Doctor doctor){
		Date latestDate = null;
		ArrayList<Appointment> doctorAppointments = appointments.get(doctor);
		for(Appointment appointment : doctorAppointments){
			Date currentDate = appointment.getDate();
			if(currentDate.after(latestDate)){
				latestDate = currentDate;
			}
		}
		return latestDate;
	}
	
	public Date getNextAppointmentMoment(Doctor doctor){
		Date latestDate = getLatestAppointmentMoment(doctor);
		Date newDate = DateCalculator.addTimeToDate(latestDate, appointmentDuration*60);
		return newDate;
	}
}