package medicaltest;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Holds on to all the medicaltest factories.
 */
public class MedicalTests
{
	public static Collection<MedicalTestFactory> factories() {
		Collection<MedicalTestFactory> medicaltests = new ArrayList<MedicalTestFactory>();
		medicaltests.clear();
		medicaltests.add(new UltraSoundScanFactory());
		medicaltests.add(new XRayScanFactory());
		medicaltests.add(new BloodAnalysisFactory());
		return new ArrayList<MedicalTestFactory>(medicaltests);
	}
}
