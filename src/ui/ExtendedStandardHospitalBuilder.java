package ui;

import machine.MachineBuilder;
import system.Campus;
import system.Hospital;
import system.StandardHospitalBuilder;
import users.DoctorFactory;
import users.NurseFactory;

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
		} catch (Exception e) {;
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
			} catch (Exception e) {;
			}
		}
		
	}

	private void addPatient(Campus campus, String string) {
		try {
			campus.getHospital().getPatientFileManager().registerPatient(string, campus);
		} catch (Exception e) {;
		}
		
	}

	private void addNurse(Campus campus, String string) {
		NurseFactory factory = new NurseFactory();
		factory.setLocation(campus);
		factory.setName(string);
		try {
			campus.getHospital().getUserManager().createUser(factory);
		} catch (Exception e) {;
		}
		
	}

	
}
