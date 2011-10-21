package users;

import java.util.Date;
import java.util.ArrayList;
import scheduler.Appointment;

public class Doctor extends User
{
	/**
	 * 
	 * Generate a list of maximal amount of appointments. if an appointment is
	 * not filled, it will be regarded as as "open".
	 * 
	 * @param name
	 * The name of the doctor.
	 */
	public Doctor(String name) {
		super(name);
		appointments = new ArrayList<Appointment>(14);
	}
					
	public static int slotTime = 30;
	private ArrayList<Appointment> appointments;

	public void addAppointment(Appointment appointment) {
		appointments.add(appointment);
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * 
	 * @return null if no more free slots.
	 */
	public Date getNextFreeSlot() {
		for (Appointment appointment : appointments) {
			if (appointment.isOpen())
				return appointment.getDate();
		}
		return null;
	}
}
