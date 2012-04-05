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

	/**
	 * Initialises the hospital the way we want it to.
	 * @throws InvalidPatientFileException 
	 * @throws InvalidNameException 
	 * @throws DischargePatientException 
	 */
	public TestingScenario() throws InvalidNameException, InvalidPatientFileException, DischargePatientException {
		// build the hospital
		StandardHospitalBuilder shb = new StandardHospitalBuilder();
		hospital = shb.build();
		// add medical test- and treatment factories
		for (MedicalTestFactory mtf : medicalTestFactories)
			hospital.addMedicalTestFactory(mtf);
		for (TreatmentFactory tf : treatmentFactories)
			hospital.addTreatmentFactory(tf);
		// add nurses, doctors, warehouse admins
		// add patient files from Dieter, Stefaan and Wouter
		PatientFile dieter = hospital.getPatientFileManager().registerPatient("Dieter", hospital.getCampus("Campus 2"));
		PatientFile stef = hospital.getPatientFileManager().registerPatient("Stefaan", hospital.getCampus("Campus 1"));
		PatientFile wouter = hospital.getPatientFileManager().registerPatient("Wouter", hospital.getCampus("Campus 1"));
		PatientFile thibault = hospital.getPatientFileManager().registerPatient("Thibault", hospital.getCampus("Campus 1"));
		hospital.getPatientFileManager().checkOut(thibault);
		thibault.createDiagnose("Coughing, difficutly breathing", "A fatal variant of pneumonia", user, secOp)
		
		// change the contents and states of the patient files accordingly.
		
	}

	public static void main(String[] args) {
		try {
			new TestingScenario();
		} catch (Exception e) {
			throw new Error(e);
		}
		
	}
}
