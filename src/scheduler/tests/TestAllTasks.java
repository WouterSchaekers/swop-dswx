package scheduler.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Collection;
import machine.BloodAnalyserBuilder;
import machine.MachineBuilder;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTest;
import org.junit.Test;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.tasks.AppointmentDescription;
import scheduler.tasks.Task;
import system.Campus;
import system.Hospital;
import system.StandardHospitalBuilder;
import treatment.SurgeryFactory;
import treatment.Treatment;
import users.Doctor;
import users.DoctorFactory;
import users.NurseFactory;
import warehouse.item.MiscType;
import warehouse.item.WarehouseItemType;
import exceptions.CanNeverBeScheduledException;
import exceptions.DischargePatientException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidSerialException;
import exceptions.InvalidUserFactory;
import exceptions.UserAlreadyExistsException;

@SuppressWarnings("deprecation")
public class TestAllTasks
{
	Hospital hospital = new StandardHospitalBuilder().build();
	Campus location = hospital.getAllCampuses().iterator().next();

	@Test
	public void scheduleAppointment() throws Exception {
		DoctorFactory f = new DoctorFactory();
		f.setLocation(location);
		f.setName("Jonathan");
		Doctor user = (Doctor) hospital.getUserManager().createUser(f);
		PatientFile file = hospital.getPatientFileManager().registerPatient("jenny", location);
		AppointmentDescription appointment = new AppointmentDescription(user, file, hospital.getTimeKeeper()
				.getSystemTime());
		Task<AppointmentDescription> task = hospital.getTaskManager().add(appointment);
		assertTrue(task.isScheduled());
	}

	@Test
	public void scheduleMedicalTestBlood() throws UserAlreadyExistsException, InvalidNameException,
			InvalidLocationException, InvalidPatientFileException, InvalidSerialException,
			CanNeverBeScheduledException, FactoryInstantiationException, InvalidUserFactory {
		NurseFactory f = new NurseFactory();
		f.setName("Jenny");
		f.setLocation(location);
		hospital.getUserManager().createUser(f);
		PatientFile file = hospital.getPatientFileManager().registerPatient("jenny", location);
		BloodAnalyserBuilder builder = bloodanalyzer(location.getMachinePool().getAllBuilders());
		builder.setLocation(location);
		builder.setLocationWithinCampus("Randomstring");
		builder.setSerial(123);
		location.getMachinePool().addMachine(builder);
		// There is a blood analyzer a nurse and a patient
		BloodAnalysisFactory fact = new BloodAnalysisFactory();
		fact.setCreationDate(hospital.getTimeKeeper().getSystemTime());
		fact.setFocus("GG");
		fact.setNumberOfAnalysis(2);
		fact.setPatientFile(file);
		Task<MedicalTest> task = hospital.getTaskManager().add(fact.create());
		assertTrue(task.isScheduled());
		boolean exce = false;
		try {
			hospital.getPatientFileManager().checkOut(file);
		} catch (DischargePatientException e) {
			exce = true;
		}
		assertTrue(exce);
	}

	private BloodAnalyserBuilder bloodanalyzer(Collection<MachineBuilder> allBuilders) {
		for (MachineBuilder builder : allBuilders)
			if (builder instanceof BloodAnalyserBuilder)
				return (BloodAnalyserBuilder) builder;
		return null;
	}

	@Test
	public void scheduleTreatment() throws Exception{

		NurseFactory f = new NurseFactory();
		f.setName("Jenny");
		f.setLocation(location);
		hospital.getUserManager().createUser(f);
		DoctorFactory docFactory1 = new DoctorFactory();
		docFactory1.setName("Jonas");
		docFactory1.setLocation(location);
		Doctor doctorJasper = (Doctor) hospital.getUserManager().createUser(docFactory1);
		DoctorFactory docFactory2 = new DoctorFactory();
		docFactory2.setName("Jonathan");
		docFactory2.setLocation(location);
		hospital.getUserManager().createUser(docFactory2);
		PatientFile file = hospital.getPatientFileManager().registerPatient("jasmine", location);
		Diagnose diagnose = file.createDiagnose("We zijn gebuisd.", "Thibault fok of", doctorJasper, null);
		// add stuff to hospital.
		WarehouseItemType type = new MiscType();
		location.getWarehouse().add(type, HospitalDate.END_OF_TIME);
		SurgeryFactory surgeryfactory = new SurgeryFactory();
		surgeryfactory.setCreationDate(now());
		surgeryfactory.setDescription("Thibault verwijderen van de wereld.");
		surgeryfactory.setDiagnose(diagnose);
		Task<Treatment> surgery = hospital.getTaskManager().add(surgeryfactory.create());
		assertTrue(surgery.isScheduled());
	}

	@Test
	public void scheduleTreatment2() throws Exception {
		NurseFactory f = new NurseFactory();
		f.setName("Jenny");
		f.setLocation(location);
		hospital.getUserManager().createUser(f);
		DoctorFactory docFactory1 = new DoctorFactory();
		docFactory1.setName("Joey");
		docFactory1.setLocation(location);
		Doctor doctorJasper = (Doctor) hospital.getUserManager().createUser(docFactory1);
		DoctorFactory docFactory2 = new DoctorFactory();
		docFactory2.setName("Jonathan");
		docFactory2.setLocation(location);
		Doctor doctorJonathan = (Doctor) hospital.getUserManager().createUser(docFactory2);
		PatientFile file = hospital.getPatientFileManager().registerPatient("jasmine", location);
		Diagnose diagnose = file.createDiagnose("We zijn gebuisd.", "Thibault fok of", doctorJasper, doctorJonathan);
		// add stuff to hospital.
		WarehouseItemType type = new MiscType();
		SurgeryFactory surgeryfactory = new SurgeryFactory();
		surgeryfactory.setCreationDate(now());
		surgeryfactory.setDescription("Thibault verwijderen van de wereld.");
		surgeryfactory.setDiagnose(diagnose);
		Task<Treatment> surgery = hospital.getTaskManager().add(surgeryfactory.create());
		diagnose.approveBy(doctorJonathan);
		assertFalse(surgery.isScheduled());
		location.getWarehouse().add(type, HospitalDate.END_OF_TIME);
		assertTrue(surgery.isScheduled());
	}

	@Test
	public void scheduleTreatment3() throws Exception {
		NurseFactory f = new NurseFactory();
		f.setName("Jenny");
		f.setLocation(location);
		hospital.getUserManager().createUser(f);
		DoctorFactory docFactory1 = new DoctorFactory();
		docFactory1.setName("Jasper");
		docFactory1.setLocation(location);
		Doctor doctorJasper = (Doctor) hospital.getUserManager().createUser(docFactory1);
		DoctorFactory docFactory2 = new DoctorFactory();
		docFactory2.setName("Jonathan");
		docFactory2.setLocation(location);
		Doctor doctorJonathan = (Doctor) hospital.getUserManager().createUser(docFactory2);
		PatientFile file = hospital.getPatientFileManager().registerPatient("jasmine", location);
		Diagnose diagnose = file.createDiagnose("We zijn gebuisd.", "Thibault fok of", doctorJasper, doctorJonathan);
		// add stuff to hospital.
		WarehouseItemType type = new MiscType();
		SurgeryFactory surgeryfactory = new SurgeryFactory();
		surgeryfactory.setCreationDate(now());
		surgeryfactory.setDescription("Thibault verwijderen van de wereld.");
		surgeryfactory.setDiagnose(diagnose);
		Task<Treatment> surgery = hospital.getTaskManager().add(surgeryfactory.create());
		assertFalse(surgery.isScheduled());
		diagnose.approveBy(doctorJonathan);
		assertFalse(surgery.isScheduled());
		location.getWarehouse().add(type, HospitalDate.END_OF_TIME);
		assertTrue(surgery.isScheduled());
	}

	private HospitalDate now() {
		return hospital.getTimeKeeper().getSystemTime();
	}
}