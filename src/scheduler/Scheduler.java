package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;
import javax.annotation.Resource;
import patient.PatientFile;
import users.Doctor;

public class Scheduler
{
	private TreeMap<Date, Collection<Appointment>> appointments;
	private TreeMap<Date, Collection<Resource>> resources;
	private Collection<Resource> availableResources;

	public Scheduler() {
		appointments = new TreeMap<Date, Collection<Appointment>>();
		resources = new TreeMap<Date, Collection<Resource>>();
		availableResources = new ArrayList<Resource>();
	}
	
	public Appointment addAppointment(PatientFile patient, Doctor doctor, int duration){
		Date freeDate = getFreeDate(doctor);
		Appointment newAppointment = new Appointment(patient, doctor, freeDate, duration);
		Collection<Appointment> oldAppointments = appointments.get(freeDate);
		if(oldAppointments == null){
			oldAppointments = new ArrayList<Appointment>();
		}
		oldAppointments.add(newAppointment);
		appointments.put(freeDate, oldAppointments);
		return newAppointment;
	}
	
	private Date getFreeDate(Doctor doctor){
		Appointment latestAppointment = getLatestAppointment(doctor);
		Date latestDate = latestAppointment.getDate();
		int duration = latestAppointment.getAppointmentDuration();
		return new Date(latestDate.getTime() + duration*60*1000);
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