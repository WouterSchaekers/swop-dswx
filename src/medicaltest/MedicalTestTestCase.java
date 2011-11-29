package medicaltest;

import static org.junit.Assert.*;
import org.junit.Test;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;

public class MedicalTestTestCase
{
	@Test
	public void CreateMedicalTest() {
		MedicalTest one = null;
		try {
			one = new BloodAnalysis();
		} catch (InvalidNameException e) {
			System.out.println(e.getMessage());
		} catch (InvalidDurationException e) {
			System.out.println(e.getMessage());
		}
		one.getTestName().replace("B", "C");
		assertTrue(one.getTestName().equals("BloodAnalysis"));
	}
}
