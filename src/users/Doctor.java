package users;

import java.util.ArrayList;

public class Doctor extends User{
	public Doctor(String name, int ssid){
		super(name, ssid);
		appointments = new ArrayList<Appointment>();
	}
	public static int slotTime = 30;
	ArrayList<Appointment> appointments;
	public void addAppointment(Appointment appointment){
		appointments.add(appointment);
	}
	public void getNextSlot(){
		for(Appointment appointment : appointments){
			
		}
	}
}
