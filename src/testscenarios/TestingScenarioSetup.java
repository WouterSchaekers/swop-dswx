package testscenarios;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import machine.BloodAnalyserBuilder;
import machine.MachineBuilder;
import machine.UltraSoundScannerBuilder;
import machine.XRayScannerBuilder;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTestFactory;
import medicaltest.UltraSoundScanFactory;
import medicaltest.XRayScanFactory;
import org.junit.Test;
import patient.Diagnose;
import patient.PatientFile;
import patient.PatientFileManager;
import scheduler.HospitalDate;
import scheduler.tasks.TaskManager;
import system.Campus;
import system.Hospital;
import system.Location;
import system.StandardHospitalBuilder;
import treatment.CastFactory;
import treatment.MedicationFactory;
import treatment.SurgeryFactory;
import treatment.TreatmentFactory;
import ui.UserFilter;
import users.Doctor;
import users.DoctorFactory;
import users.NurseFactory;
import users.UserFactory;
import users.UserManager;
import users.WarehouseAdminFactory;
import exceptions.CanNeverBeScheduledException;
import exceptions.DischargePatientException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidComplaintsException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidSerialException;
import exceptions.InvalidUserFactory;
import exceptions.UserAlreadyExistsException;

public class TestingScenarioSetup
{
	private Hospital hospital;
	private UserManager userMan;
	private PatientFileManager pfMan;
	private TaskManager taskMan;
	private Campus campus1;
	private Campus campus2;

	@Test
	public void testScenario() throws Exception {
		System.out.print("Setting up the hospital... ");
		// build the hospital
		StandardHospitalBuilder shb = new StandardHospitalBuilder();
		this.hospital = shb.build();
		this.userMan = hospital.getUserManager();
		this.taskMan = hospital.getTaskManager();
		this.pfMan = hospital.getPatientFileManager();
		this.campus1 = hospital.getCampus("Campus 1");
		this.campus2 = hospital.getCampus("Campus 2");
		addFactories();
		addUserTypes();
		createMachines();
		createUsers();
		initialisePatientFiles();
		System.out.println("Initialisation of hospital state was successful!");
		System.out.println("-------------------------------------------------\n\n");
		System.out.println("Starting the execution of the testing scenario...");
		new TestScenario(hospital);
	}

	private void addFactories() {
		Collection<MedicalTestFactory> medicalTestFactories = new LinkedList<MedicalTestFactory>(
				Arrays.asList(new MedicalTestFactory[] { new BloodAnalysisFactory(), new XRayScanFactory(),
						new UltraSoundScanFactory() }));
		Collection<TreatmentFactory> treatmentFactories = new LinkedList<TreatmentFactory>(
				Arrays.asList(new TreatmentFactory[] { new MedicationFactory(), new SurgeryFactory(), new CastFactory() }));

		for (MedicalTestFactory mtf : medicalTestFactories)
			hospital.addMedicalTestFactory(mtf);
		for (TreatmentFactory tf : treatmentFactories)
			hospital.addTreatmentFactory(tf);
	}

	private void addUserTypes() {
		userMan.addType(new DoctorFactory());
		userMan.addType(new NurseFactory());
		userMan.addType(new WarehouseAdminFactory());
	}

	private void createMachines() throws InvalidSerialException, InvalidLocationException {
		addXRayScanner(1000, campus1, "X-Ray departement");
		addXRayScanner(1001, campus1, "X-Ray departement");
		addXRayScanner(1002, campus1, "X-Ray departement");
		addUltraSoundScanner(2000, campus1, "Ultra sound scanner department");
		addUltraSoundScanner(2100, campus2, "Ultra sound scanner department");
		addBloodAnalyser(3100, campus2, "Blood analysis laboratory");
		addBloodAnalyser(3101, campus2, "Blood analysis laboratory");
		addBloodAnalyser(3102, campus2, "Blood analysis laboratory");
	}

	private void addXRayScanner(int serial, Campus campus, String subLocation) throws InvalidSerialException,
			InvalidLocationException {
		Collection<MachineBuilder> builders = campus.getMachinePool().getAllBuilders();
		for (MachineBuilder mb : builders) {
			if (mb instanceof XRayScannerBuilder) {
				mb.setLocation(campus);
				mb.setLocationWithinCampus(subLocation);
				mb.setSerial(serial);
				campus.getMachinePool().addMachine(mb);
				return;
			}
		}
		throw new IllegalStateException("Apparently we're not allowed to create XRayScanners..");
	}

	private void addBloodAnalyser(int serial, Campus campus, String subLocation) throws InvalidSerialException,
			InvalidLocationException {
		Collection<MachineBuilder> builders = campus.getMachinePool().getAllBuilders();
		for (MachineBuilder mb : builders) {
			if (mb instanceof BloodAnalyserBuilder) {
				mb.setLocation(campus);
				mb.setLocationWithinCampus(subLocation);
				mb.setSerial(serial);
				campus.getMachinePool().addMachine(mb);
				return;
			}
		}
		throw new IllegalStateException("Apparently we're not allowed to create XRayScanners..");
	}

	private void addUltraSoundScanner(int serial, Campus campus, String subLocation) throws InvalidSerialException,
			InvalidLocationException {
		Collection<MachineBuilder> builders = campus.getMachinePool().getAllBuilders();
		for (MachineBuilder mb : builders) {
			if (mb instanceof UltraSoundScannerBuilder) {
				mb.setLocation(campus);
				mb.setLocationWithinCampus(subLocation);
				mb.setSerial(serial);
				campus.getMachinePool().addMachine(mb);
				return;
			}
		}
		throw new IllegalStateException("Apparently we're not allowed to create XRayScanners..");
	}

	private void createUsers() throws Exception {
		addDoctor("Jonathan", campus1);
		addDoctor("Jens", campus1);
		addDoctor("Jelle", campus1);
		addDoctor("Joanne", campus1);
		addNurse("Jenny", campus1);
		addNurse("Jenna", campus1);
		addNurse("Jeffrey", campus1);

		addDoctor("Joe", campus2);
		addDoctor("Jennifer", campus2);
		addNurse("Joy", campus2);
		addNurse("Janna", campus2);
	}

	private void addDoctor(String name, Location location) throws UserAlreadyExistsException, InvalidNameException,
			InvalidLocationException, InvalidUserFactory {
		Collection<UserFactory> facs = hospital.getUserManager().getUserFactories();
		for (UserFactory userFac : facs)
			if (userFac instanceof DoctorFactory) {
				userFac.setName(name);
				userFac.setLocation(location);
				userMan.createUser((DoctorFactory) userFac);
				return;
			}
		throw new IllegalStateException("Something went wrong... requested user type does not exist?");
	}

	private void addNurse(String name, Location location) throws UserAlreadyExistsException, InvalidNameException,
			InvalidLocationException, InvalidUserFactory {
		Collection<UserFactory> facs = hospital.getUserManager().getUserFactories();
		for (UserFactory userFac : facs)
			if (userFac instanceof NurseFactory) {
				userFac.setName(name);
				userFac.setLocation(location);
				userMan.createUser((NurseFactory) userFac);
				return;
			}
		throw new IllegalStateException("Something went wrong... requested user type does not exist?");
	}

	private void initialisePatientFiles() throws Exception {
		createPatientFiles();
		fillFilesWithData();
	}

	/******************
	 * Helper methods *
	 *****************/

	private void createPatientFiles() throws InvalidNameException, InvalidPatientFileException {
		pfMan.registerPatient("Dieter", hospital.getCampus("Campus 2"));
		pfMan.registerPatient("Stefaan", hospital.getCampus("Campus 1"));
		pfMan.registerPatient("Wouter", hospital.getCampus("Campus 1"));
		pfMan.registerPatient("Thibault", hospital.getCampus("Campus 1"));
	}

	private void fillFilesWithData() throws Exception {
		setThibaultHistory(getPatientFileFrom("Thibault"));
		setStefHistory(getPatientFileFrom("Stefaan"));
		setWouterHistory(getPatientFileFrom("Wouter"));
		setDieterHistory(getPatientFileFrom("Dieter"));

		// now we advance the time some so we can enter the results next.
		advanceTime(new HospitalDate(now().getTimeSinceStart() + HospitalDate.ONE_DAY * 2));
		this.pfMan.checkOut(getPatientFileFrom("Thibault"));
	}

	private void setStefHistory(PatientFile stef) throws InvalidPatientFileException, CanNeverBeScheduledException,
			FactoryInstantiationException, IllegalAccessException, InvalidDiagnoseException, InvalidDoctorException,
			InvalidComplaintsException {
		this.pfMan.checkIn(stef);
		addMedicalTestTo(getBloodFactory("Cancerogen chemicals", 3, stef));
		Doctor jonathan = (Doctor) (UserFilter.SpecificDoctorFilter(userMan.getAllUserINs(), "Jonathan"));
		Doctor jennifer = (Doctor) (UserFilter.SpecificDoctorFilter(userMan.getAllUserINs(), "Jennifer"));
		Diagnose diag = stef.createDiagnose("Patient is very sleepy", "Cancer", jonathan, jennifer);
		addTreatmentTo(getSurgeryFactory("Chemotherapy", diag));
	}

	private void setWouterHistory(PatientFile wouter) throws InvalidPatientFileException {
		this.pfMan.checkIn(wouter);
	}

	private void setDieterHistory(PatientFile dieter) throws InvalidPatientFileException, CanNeverBeScheduledException,
			FactoryInstantiationException, IllegalAccessException, InvalidDiagnoseException, InvalidDoctorException,
			InvalidComplaintsException {
		this.pfMan.checkIn(dieter);
		addMedicalTestTo(getBloodFactory("Virusses, bacteria, anything abnormal", 10, dieter));
		addMedicalTestTo(getXRayScanFactory("head", 10, (float) 2.9, dieter));

		Doctor joanne = (Doctor) (UserFilter.SpecificDoctorFilter(userMan.getAllUserINs(), "Joanne"));
		dieter.createDiagnose("Feels sick ALL the time", "Hypochondria", joanne, null);
	}

	private void setThibaultHistory(PatientFile thibault) throws CanNeverBeScheduledException,
			FactoryInstantiationException, DischargePatientException, InvalidPatientFileException,
			IllegalAccessException, InvalidDiagnoseException, InvalidDoctorException, InvalidComplaintsException {
		this.pfMan.checkIn(thibault);
		addMedicalTestTo(getBloodFactory("Virusses", 5, thibault));

		addMedicalTestTo(getUltraSoundScanFactory("Lungs", true, true, thibault));
		addMedicalTestTo(getXRayScanFactory("Virusses", 5, (float) 1.5, thibault));

		Doctor docPneumo = (Doctor) (UserFilter.SpecificDoctorFilter(userMan.getAllUserINs(), "Jelle"));
		Diagnose diag = thibault.createDiagnose("Coughing, difficutly breathing", "A fatal variant of pneumonia",
				docPneumo, null);
		addTreatmentTo(getSurgeryFactory("Lung transplant", diag));
	}

	private void advanceTime(HospitalDate newDate) throws InvalidHospitalDateException {
		hospital.getTimeKeeper().setSystemTime(newDate);
	}

	private void addMedicalTestTo(MedicalTestFactory factory) throws CanNeverBeScheduledException,
			FactoryInstantiationException {
		this.taskMan.add(factory.create());
	}

	@SuppressWarnings("deprecation")
	private void addTreatmentTo(TreatmentFactory factory) throws CanNeverBeScheduledException,
			FactoryInstantiationException {
		this.taskMan.add(factory.create());
	}

	private PatientFile getPatientFileFrom(String name) {
		for (PatientFile pf : this.pfMan.getAllPatientFiles())
			if (pf.getPatientIN().getName().equals(name))
				return (PatientFile) pf;
		throw new IllegalArgumentException("Patient file not found");
	}

	private BloodAnalysisFactory getBloodFactory(String focus, int number, PatientFile pf) {
		Collection<MedicalTestFactory> facs = hospital.getMedicalTests();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof BloodAnalysisFactory) {
				((BloodAnalysisFactory) curFac).setFocus(focus);
				((BloodAnalysisFactory) curFac).setNumberOfAnalysis(number);
				curFac.setCreationDate(now());
				curFac.setPatientFile(pf);
				return (BloodAnalysisFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no BloodAnalysisFactory exists?");
	}

	private UltraSoundScanFactory getUltraSoundScanFactory(String focus, boolean recVid, boolean recImg, PatientFile pf) {
		Collection<MedicalTestFactory> facs = hospital.getMedicalTests();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof UltraSoundScanFactory) {
				((UltraSoundScanFactory) curFac).setFocus(focus);
				((UltraSoundScanFactory) curFac).setRecordImages(recImg);
				((UltraSoundScanFactory) curFac).setRecordVid(recVid);
				curFac.setCreationDate(now());
				curFac.setPatientFile(pf);
				return (UltraSoundScanFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no UltraSoundScanFactory exists?");
	}

	private XRayScanFactory getXRayScanFactory(String bodyPart, int number, float zoom, PatientFile pf) {
		Collection<MedicalTestFactory> facs = hospital.getMedicalTests();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof XRayScanFactory) {
				((XRayScanFactory) curFac).setBodyPart(bodyPart);
				((XRayScanFactory) curFac).setNumberOfNeededImages(number);
				((XRayScanFactory) curFac).setZoomLevel(zoom);
				curFac.setCreationDate(now());
				curFac.setPatientFile(pf);
				return (XRayScanFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no XRayScanFactory exists?");
	}

	private SurgeryFactory getSurgeryFactory(String description, Diagnose diag) {
		Collection<TreatmentFactory> facs = hospital.getTreatments();
		for (TreatmentFactory curFac : facs) {
			if (curFac instanceof SurgeryFactory) {
				curFac.setDiagnose(diag);
				curFac.setCreationDate(now());
				((SurgeryFactory) curFac).setDescription(description);
				return (SurgeryFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently there are no SurgeryFactories?");
	}

	private HospitalDate now() {
		return hospital.getTimeKeeper().getSystemTime();
	}
}
