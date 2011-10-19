package users;

import java.util.ArrayList;

public class Database {
	public Database(){
	}
	private static ArrayList<Patient> patients = new ArrayList<Patient>();
	public static ArrayList<Patient> getPatients() {
		return patients;
	}
	public void setPatients(ArrayList<Patient> patients) {
		Database.patients = patients;
	}
	public static void addPatient(Patient patient){
		patients.add(patient);
	}
	public void deletePatient(Patient patient){
		patients.remove(patient);
	}
	private static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(ArrayList<Doctor> doctors) {
		Database.doctors = doctors;
	}
	public void addDoctor(Doctor doctor){
		doctors.add(doctor);
	}
	public void deleteDoctor(Doctor doctor){
		doctors.remove(doctor);
	}
	private static ArrayList<Nurse> nurses = new ArrayList<Nurse>();
	public ArrayList<Nurse> getNurses() {
		return nurses;
	}
	public void setNurses(ArrayList<Nurse> nurses) {
		Database.nurses = nurses;
	}
	public void addNurse(Nurse nurse){
		nurses.add(nurse);
	}
	public void deleteNurse(Nurse nurse){
		nurses.remove(nurse);
	}
}
