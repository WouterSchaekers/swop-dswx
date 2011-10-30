package scheduler;

import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;
import patient.PatientFile;
import users.Doctor;

public class Scheduler
{
	private TreeMap<Date, Collection<Appointment>> appointments;

	public Scheduler() {
		appointments = new TreeMap<Date, Collection<Appointment>>();
	}
	
	public void addAppointment(PatientFile patient, Doctor doctor, int Duration){
		Date date = getFreeDate(doctor);
	}
	
	private Date getFreeDate(Doctor doctor){
		return null;
	}
	
	private Appointment getLatestAppointment(Doctor doctor){
		Collection<Collection<Appointment>> allAppointments = appointments.values();
		Appointment latestAppointment = null;
		Date latestDate = null;
		for(Collection<Appointment> colAppointments : allAppointments){
			for(Appointment curAppointment : colAppointments){
				if(curAppointment.getDoctor() == doctor){
					if(latestDate == null){
						latestAppointment = curAppointment;
						latestDate = curAppointment.getDate();
					}
					else{
						if(curAppointment.getDate().after(latestDate)){
							latestAppointment = curAppointment;
							latestDate = curAppointment.getDate();
						}
					}
				}
			}
		}
		return latestAppointment;
	}
}