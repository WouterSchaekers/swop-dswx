package system;

import machine.MachineBuilder;
import medicaltest.BloodAnalysisFactory;
import treatment.CastFactory;
import treatment.MedicationFactory;
import treatment.SurgeryFactory;
import users.DoctorFactory;
import users.NurseFactory;

@system.SystemAPI
public class ExtendedStandardHospitalBuilder
{
	/**
	 * Creates a hospital with :
	 * With "Campus 1" and "Campus 2"
	 * and a hospital admin with the name: "Thibault Leemans"
	 * nurse jenny </br>
	 * nurse jasmine</br>
	 * doctor jonathan</br>
	 * doctor stefaan</br>
	 * patient daan</br>
	 */
	@system.SystemAPI
	public ExtendedStandardHospitalBuilder()
	{
		;
	}
	@system.SystemAPI
	public Hospital build() {
		Hospital rv = new StandardHospitalBuilder().build();
		Campus campus =rv.getAllCampuses().iterator().next();
		addNurse(campus,"jenny");
		addNurse(campus,"Jasmine");
		addDoctor(campus,"jonathan");
		addDoctor(campus,"Stefaan");
		addPatient(campus,"daan");
		addAllMachines(campus);
		addMedicalTests(rv);
		return rv;
	}

	private void addMedicalTests(Hospital rv) {
		rv.addTreatmentFactory(new CastFactory());
		rv.addTreatmentFactory(new MedicationFactory());
		rv.addTreatmentFactory(new SurgeryFactory());
		
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
