package medicaltest;

import static org.junit.Assert.*;
import org.junit.Test;
import patient.PatientFile;


public class MedicalTestTestCase
{
	@Test
	public void CreateMedicalTest()
	{
//		PatientFileManager t = new PatientFileManager();
		PatientFile file = new PatientFile("jonathan");
		MedicalTest one = new BloodAnalysis(file);
		one.getTestName().replace("B","C");
		assertTrue(one.getTestName().equals("BloodAnalysis"));
	}
}
