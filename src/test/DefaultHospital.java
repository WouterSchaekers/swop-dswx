package test;

import machine.MachinePool;
import patient.PatientFile;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import scheduler.Scheduler;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import users.UserManager;
import users.WarehouseAdmin;
import warehouse.Warehouse;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeLordException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

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
	public TimeLord tl;
	public WarehouseAdmin wha;
	public TaskManager tm;
	
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
	 * @throws InvalidTimeLordException 
	 */
	public DefaultHospital() throws InvalidHospitalDateException, UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException, InvalidLocationException, InvalidSerialException, InvalidTimeLordException {
		um = new UserManager();
		wh = new Warehouse(new HospitalDate((new HospitalDate().getTimeSinceStart()) + 1));
		pfm = new PatientFileManager();
		wha = new WarehouseAdmin(wh, pfm);
		tm = new TaskManager(s);
		tl = new TimeLord();
		s = new Scheduler(tl);
		mp = new MachinePool();
		
		um.createNurse("Jenny");
		um.createNurse("Jasmine");
		um.createDoctor("Stef");
		um.createDoctor("Simon");
		um.createHospitalAdmin("Frits");
		mp.createBloodAnalyser(1, "plaza");
		mp.createXrayScanner(2,"A");
		mp.createUltraSoundScanner(3, "lobby");
		pfm.registerPatient("Zieke Dieter");
		pfm.registerPatient("Wouter");
		PatientFile Dieter = pfm.getPatientFileFrom("Zieke Dieter");
		pfm.checkIn(Dieter);
		
	}
}
