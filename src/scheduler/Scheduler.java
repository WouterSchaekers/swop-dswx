package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;
import javax.annotation.Resource;
import patient.PatientFile;
import users.Doctor;
import users.Nurse;

public class Scheduler
{
	private TreeMap<Date, Collection<Appointment>> appointments;
	private Collection<Nurse> nurses;
	private TreeMap<Date, Collection<Resource>> resources;
	private Collection<Resource> availableResources;
	private Date currentDate;
	private final int TIMEAFTERCURRENTDATE = 60*60*1000;

	public Scheduler() {
		appointments = new TreeMap<Date, Collection<Appointment>>();
		resources = new TreeMap<Date, Collection<Resource>>();
		availableResources = new ArrayList<Resource>();
		currentDate = new Date(0);
	}
	
	public Appointment addAppointment(PatientFile patient, Doctor doctor, int duration){
		Date freeDate = getFirstDate(doctor, duration);
		Appointment newAppointment = new Appointment(patient, doctor, freeDate, duration);
		Collection<Appointment> oldAppointments = appointments.get(freeDate);
		if(oldAppointments == null){
			oldAppointments = new ArrayList<Appointment>();
		}
		oldAppointments.add(newAppointment);
		appointments.put(freeDate, oldAppointments);
		return newAppointment;
	}
	
	public void addAppointment(PatientFile patient, Nurse nurse, Resource resource, int duration){
		Date freeDate = getFreeDate(nurse, resource);
	}
	
	private Date getFreeDate(Nurse nurse, Resource resource){
		
		return null;
	}
	//TODO: implement current date as first option
	private Date getFirstDate(Doctor doctor, int duration){
		Collection<Date> allDates = this.appointments.keySet();
		for(Date curDate : allDates){
			Collection<Appointment> colAppointments = this.appointments.get(curDate);
			for(Appointment curAppointment : colAppointments){
				Date endDate = new Date(curAppointment.getDate().getTime()+curAppointment.getAppointmentDuration());
				if(curAppointment.getDoctor() == doctor && isFree(doctor, duration, endDate)){
					return endDate;
				}
			}
		}
		return null;
	}
	
	private boolean isFree(Doctor doctor, int duration, Date date){
		Collection<Date> allDates = this.appointments.keySet();
		Date endDate = new Date(date.getTime() + duration);
		for(Date curDate : allDates){
			if(date.after(curDate)){
				Collection<Appointment> colAppointments = this.appointments.get(curDate);
				for(Appointment curAppointment : colAppointments){
					Date endCurDate = new Date(curDate.getTime() + curAppointment.getAppointmentDuration());
					if(curAppointment.getDoctor() == doctor && endCurDate.after(date)){
						return false;
					}
				}
			}
			else if(curDate.after(date) && endDate.after(curDate)){
				Collection<Appointment> colAppointments = this.appointments.get(curDate);
				for(Appointment curAppointment : colAppointments){
					if(curAppointment.getDoctor() == doctor){
						return false;
					}
				}
			}
			
		}
		return true;
	}
}