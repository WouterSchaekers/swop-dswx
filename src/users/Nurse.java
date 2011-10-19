package users;
import java.util.ArrayList;


public class Nurse extends Person{
	public Nurse(String name, int ssid){
		super(name, ssid);
	}
	public ArrayList<Patient> getPatientList(){
		return Database.getPatients();
	}
	public void registerPatient(Patient patient){
		Database.addPatient(patient);
	}
	public Appointment makeAppointment(Patient patient, Doctor doctor){
		
	}
}
