package testscenarios;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTestFactory;
import medicaltest.UltraSoundScanFactory;
import medicaltest.XRayScanFactory;
import patient.PatientFile;
import system.Hospital;
import system.StandardHospitalBuilder;
import treatment.CastFactory;
import treatment.MedicationFactory;
import treatment.SurgeryFactory;
import treatment.TreatmentFactory;
import users.UserFactory;
import exceptions.DischargePatientException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;

public class TestingScenario
{
	private Hospital hospital;
	private Collection<MedicalTestFactory> medicalTestFactories = new LinkedList<MedicalTestFactory>(
			Arrays.asList(new MedicalTestFactory[] { new BloodAnalysisFactory(), new XRayScanFactory(),
					new UltraSoundScanFactory() }));
	private Collection<TreatmentFactory> treatmentFactories = new LinkedList<TreatmentFactory>(
			Arrays.asList(new TreatmentFactory[] { new MedicationFactory(), new SurgeryFactory(), new CastFactory() }));

	public TestingScenario() throws InvalidNameException, InvalidPatientFileException, DischargePatientException {
		// build the hospital
		StandardHospitalBuilder shb = new StandardHospitalBuilder();
		hospital = shb.build();
		addFactories();
		createUsers();
		initialisePatientFiles();
	}
	
	private void addFactories() {
		for (MedicalTestFactory mtf : medicalTestFactories)
			hospital.addMedicalTestFactory(mtf);
		for (TreatmentFactory tf : treatmentFactories)
			hospital.addTreatmentFactory(tf);
	}
	
	private void createUsers() {
		Collection<UserFactory> facs = hospital.getUserManager().getUserFactories();
		if(facs == null)
			System.out.println("fok joe");
		//this.hospital.getUserManager().createUser(factory)
	}
	
	private void initialisePatientFiles() throws InvalidNameException, InvalidPatientFileException, DischargePatientException {
		hospital.getPatientFileManager().registerPatient("Dieter", hospital.getCampus("Campus 2"));
		hospital.getPatientFileManager().registerPatient("Stefaan", hospital.getCampus("Campus 1"));
		hospital.getPatientFileManager().registerPatient("Wouter", hospital.getCampus("Campus 1"));
		PatientFile thibault = hospital.getPatientFileManager().registerPatient("Thibault", hospital.getCampus("Campus 1"));
		hospital.getPatientFileManager().checkOut(thibault);
		//thibault.createDiagnose("Coughing, difficutly breathing", "A fatal variant of pneumonia", user, secOp);
	}

	public static void main(String[] args) {
		try {
			new TestingScenario();
		} catch (Exception e) {
			throw new Error(e);
		}
		
	}
}
