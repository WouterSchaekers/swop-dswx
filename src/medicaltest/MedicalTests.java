package medicaltest;

import java.util.ArrayList;
import java.util.Collection;

public class MedicalTests
{

	private final Collection<MedicalTestFactory> medicaltests = new ArrayList<MedicalTestFactory>();
	
	public Collection<MedicalTestFactory> factories() {
		medicaltests.clear();
		medicaltests.add(new UltraSoundScanFactory());
		medicaltests.add(new XRayScanFactory());
		medicaltests.add(new BloodAnalysisFactory());
		return new ArrayList<MedicalTestFactory>(medicaltests);
	}
}
