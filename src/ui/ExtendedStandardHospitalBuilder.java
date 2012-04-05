package ui;

import machine.MachineBuilder;
import system.Campus;
import system.Hospital;
import system.StandardHospitalBuilder;
import users.DoctorFactory;
import users.NurseFactory;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidSerialException;
import exceptions.UserAlreadyExistsException;

public class ExtendedStandardHospitalBuilder
{

	public Hospital build() {
		Hospital rv = new StandardHospitalBuilder().build();
		Campus campus =rv.getAllCampuses().iterator().next();
		addNurse(campus,"Jenny");
		addNurse(campus,"Jasmine");
		addDoctor(campus,"jonathan");
		addDoctor(campus,"Stefaan");
		addPatient(campus,"daan");
		addAllMachines(campus);
		return rv;
	}

	private void addDoctor(Campus campus, String string) {
		DoctorFactory factory = new DoctorFactory();
		factory.setLocation(campus);
		factory.setName(string);
		try {
			campus.getHospital().getUserManager().createUser(factory);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addAllMachines(Campus campus) {
		int i = 0;
		for(MachineBuilder builder : campus.getMachinePool().getAllBuilders())
		{
			builder.setLocation(campus);
			builder.setLocationWithinCampus("Room:"+i);
			builder.setSerial(i++);
			try {
				campus.getMachinePool().addMachine(builder);
			} catch (InvalidLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidSerialException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void addPatient(Campus campus, String string) {
		try {
			campus.getHospital().getPatientFileManager().registerPatient(string, campus);
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPatientFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addNurse(Campus campus, String string) {
		NurseFactory factory = new NurseFactory();
		factory.setLocation(campus);
		factory.setName(string);
		try {
			campus.getHospital().getUserManager().createUser(factory);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
