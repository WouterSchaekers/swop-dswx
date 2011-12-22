package test;

import exceptions.InvalidHospitalDateException;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;
import machine.BloodAnalyser;
import machine.MachinePool;
import machine.UltraSoundScanner;
import machine.XRayScanner;
import patient.PatientFile;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import scheduler.Scheduler;
import users.UserManager;
import warehouse.Warehouse;

/**
 * This class is used for testing purposes to avoid having to declare a couple
 * of variables many times.
 * <p>	um.createNurse("Jenny");
		um.createNurse("Jasmine");
		um.createDoctor("Stef");
		um.createDoctor("Simon");
		um.createHospitalAdmin("Frits");
		mp.addMachine(new XRayScanner(0, "Links"));
		mp.addMachine(new BloodAnalyser(1,"Extreem Rechts"));
		mp.addMachine(new UltraSoundScanner(2, "Kim Jung-Il"));
		pfm.registerPatient("Dieter");
		pfm.registerPatient("Wouter");
		PatientFile Dieter = pfm.getPatientFileFrom("Dieter");
		PatientFile Wouter = pfm.getPatientFileFrom("Wouter");
		pfm.checkIn(Dieter);
 */
public class DefaultHospital
{
	public UserManager um;
	public Scheduler s;
	public PatientFileManager pfm;
	public Warehouse wh;
	public MachinePool mp;
	
	/**
	 * Default constructor.
	 * 	<p>um.createNurse("Jenny");
		um.createNurse("Jasmine");
		um.createDoctor("Stef");
		um.createDoctor("Simon");
		um.createHospitalAdmin("Frits");
		mp.addMachine(new XRayScanner(0, "Links"));
		mp.addMachine(new BloodAnalyser(1,"Extreem Rechts"));
		mp.addMachine(new UltraSoundScanner(2, "Kim Jung-Il"));
		pfm.registerPatient("Dieter");
		pfm.registerPatient("Wouter");
		PatientFile Dieter = pfm.getPatientFileFrom("Dieter");
		PatientFile Wouter = pfm.getPatientFileFrom("Wouter");
		pfm.checkIn(Dieter);
	 * @throws InvalidHospitalDateException 
	 * @throws InvalidTimeSlotException 
	 * @throws InvalidNameException 
	 * @throws UserAlreadyExistsException 
	 * @throws InvalidSerialException 
	 * @throws InvalidLocationException 
	 */
	public DefaultHospital() throws InvalidHospitalDateException, UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException, InvalidLocationException, InvalidSerialException {
		um = new UserManager();
		s = new Scheduler();
		pfm = new PatientFileManager();
		wh = new Warehouse(new HospitalDate((new HospitalDate().getTimeSinceStart()) + 1));
		mp = new MachinePool();
		
		um.createNurse("Jenny");
		um.createNurse("Jasmine");
		um.createDoctor("Stef");
		um.createDoctor("Simon");
		um.createHospitalAdmin("Frits");
		mp.addMachine(new XRayScanner(0, "Links"));
		mp.addMachine(new BloodAnalyser(1,"Extreem Rechts"));
		mp.addMachine(new UltraSoundScanner(2, "Kim Jung-Il"));
		pfm.registerPatient("Dieter");
		pfm.registerPatient("Wouter");
		PatientFile Dieter = pfm.getPatientFileFrom("Dieter");
		pfm.checkIn(Dieter);
		
	}
}