package resources;

import static org.junit.Assert.*;
import org.junit.Test;
import patient.PatientFile;
import patient.PatientFileManager;


public class MedicalTestTestCase
{
	@Test
	public void CreateMedicalTest()
	{
		PatientFileManager t = new PatientFileManager();
		PatientFile file = new PatientFile("jonathan", t);
		MedicalTest one = new BloodAnalysis(file);
		one.getTestName().replace("B","C");
		assertTrue(one.getTestName().equals("BloodAnalysis"));
	}
}
