package users;

import java.util.ArrayList;
import scheduler.Appointment;

public class Doctor extends User
{
	private ArrayList<Appointment> appointments;
	
	public Doctor(String name) {
		super(name);
		appointments = new ArrayList<Appointment>(14);
	}

	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void orderMedicalTest(){
		
	}

	
	@Override
	public usertype type() {
		// TODO Auto-generated method stub
		return usertype.Doctor;
	}
}
