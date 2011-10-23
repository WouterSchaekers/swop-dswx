package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import machine.Machine;
import medicaltest.*;
import patient.PatientFile;

public class TreatmentAndMedicalTestScheduler
{
	public TreatmentAndMedicalTestScheduler(){
		medicalTests = new HashMap<PatientFile, ArrayList<MedicalTest>>();
		treatments = new HashMap<PatientFile, ArrayList<Machine>>();
	}
	
	private HashMap<PatientFile, ArrayList<MedicalTest>> medicalTests;
	private HashMap<PatientFile, ArrayList<Machine>> treatments;
	
	public void addMedicalTest(PatientFile patient, MedicalTest medicalTest){
		Collection patientFileCollection = medicalTests.values();
		java.util.Iterator patientFileIterator = patientFileCollection.iterator();
		while(patientFileIterator.hasNext()){
			PatientFile currentPatient = (PatientFile) patientFileIterator.next();
		}
		//To be implemented
	}
}