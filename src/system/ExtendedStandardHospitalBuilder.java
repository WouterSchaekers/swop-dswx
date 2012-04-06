package system;

import machine.MachineBuilder;
import treatment.CastFactory;
import treatment.MedicationFactory;
import treatment.SurgeryFactory;
import users.DoctorFactory;
import users.NurseFactory;

@system.SystemAPI
public class ExtendedStandardHospitalBuilder
{
	/**
	 * Creates a hospital with : With "Campus 1" and "Campus 2" and a hospital
	 * admin with the name: "Thibault Leemans" nurse jenny </br> nurse
	 * jasmine</br> doctor jonathan</br> doctor stefaan</br> patient daan</br>
	 */
	@system.SystemAPI
	public ExtendedStandardHospitalBuilder() {
		;
	}

	/**
	 * @return An Extended Standard Hospital.
	 */
	@system.SystemAPI
	public Hospital build() {
		Hospital rv = new StandardHospitalBuilder().build();
		Campus campus = rv.getAllCampuses().iterator().next();
		addNurse(campus, "jenny");
		addNurse(campus, "Jasmine");
		addDoctor(campus, "jonathan");
		addDoctor(campus, "Stefaan");
		addPatient(campus, "daan");
		addAllMachines(campus);
		addMedicalTests(rv);
		return rv;
	}

	/**
	 * Adds TreatmentFactories to the given Hospital.
	 * 
	 * @param rv
	 *            The given Hospital.
	 */
	private void addMedicalTests(Hospital rv) {
		rv.addTreatmentFactory(new CastFactory());
		rv.addTreatmentFactory(new MedicationFactory());
		rv.addTreatmentFactory(new SurgeryFactory());
	}

	/**
	 * Adds a Doctor to the given Campus.
	 * 
	 * @param campus
	 *            The Campus where the Doctor has to be added to.
	 * @param doctor
	 *            The name of the Doctor.
	 */
	private void addDoctor(Campus campus, String doctor) {
		DoctorFactory factory = new DoctorFactory();
		factory.setLocation(campus);
		factory.setName(doctor);
		try {
			campus.getHospital().getUserManager().createUser(factory);
		} catch (Exception e) {
			;
		}

	}

	/**
	 * Adds Machines to the given Campus.
	 * 
	 * @param campus
	 *            The Campus where Machines need to be added to.
	 */
	private void addAllMachines(Campus campus) {
		int i = 0;
		for (MachineBuilder builder : campus.getMachinePool().getAllBuilders()) {
			builder.setLocation(campus);
			builder.setLocationWithinCampus("Room:" + i);
			builder.setSerial(i++);
			try {
				campus.getMachinePool().addMachine(builder);
			} catch (Exception e) {
				;
			}
		}

	}

	/**
	 * Adds a Patient to the given Campus.
	 * 
	 * @param campus
	 *            The Campus where the Patient has to be added to.
	 * @param patient
	 *            The name of the Patient.
	 */
	private void addPatient(Campus campus, String patient) {
		try {
			campus.getHospital().getPatientFileManager().registerPatient(patient, campus);
		} catch (Exception e) {
			;
		}
	}

	/**
	 * Adds a Nurse to the given Campus.
	 * 
	 * @param campus
	 *            The Campus where the Nurse has to be added to.
	 * @param nurse
	 *            The name of the Nurse.
	 */
	private void addNurse(Campus campus, String nurse) {
		NurseFactory factory = new NurseFactory();
		factory.setLocation(campus);
		factory.setName(nurse);
		try {
			campus.getHospital().getUserManager().createUser(factory);
		} catch (Exception e) {
			;
		}
	}
}