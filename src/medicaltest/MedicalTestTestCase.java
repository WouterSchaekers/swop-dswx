package medicaltest;

import static org.junit.Assert.*;
import org.junit.Test;

public class MedicalTestTestCase
{
	@Test
	public void CreateMedicalTest()
	{
		MedicalTest one = new BloodAnalysis();
		one.getTestName().replace("B","C");
		assertTrue(one.getTestName().equals("BloodAnalysis"));
	}
}
