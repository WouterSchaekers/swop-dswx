package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashMap;
import machine.Machine;
import medicaltest.*;
import patient.PatientFile;

public class TreatmentAndMedicalTestScheduler
{

	private HashMap<PatientFile, ArrayList<MedicalTest>> medicalTests;
	private HashMap<PatientFile, ArrayList<Machine>> treatments;

	public TreatmentAndMedicalTestScheduler() {
		medicalTests = new HashMap<PatientFile, ArrayList<MedicalTest>>();
		treatments = new HashMap<PatientFile, ArrayList<Machine>>();
	}

	public void addMedicalTest(PatientFile patient, MedicalTest medicalTest) {
		Collection<PatientFile> patientFileCollection = medicalTests.keySet();
		Iterator<PatientFile> patientFileIterator = patientFileCollection.iterator();
		while (patientFileIterator.hasNext()) {
			PatientFile currentPatient = (PatientFile) patientFileIterator.next();

		}
		// To be implemented
	}
}