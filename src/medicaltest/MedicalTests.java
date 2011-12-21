package medicaltest;

import java.util.ArrayList;
import java.util.Collection;
import medicaltest.factories.BloodAnalysisFactory;
import medicaltest.factories.MedicalTestFactory;
import medicaltest.factories.UltraSoundScanFactory;
import medicaltest.factories.XRayScanFactory;

public class MedicalTests
{
	
	private final Collection<MedicalTestFactory> medicaltests = new ArrayList<MedicalTestFactory>();
	public MedicalTests()
	{
		medicaltests.add(new UltraSoundScanFactory());
		medicaltests.add(new XRayScanFactory());
		medicaltests.add(new BloodAnalysisFactory());
	}
	public Collection<MedicalTestFactory> factories()
	{
		return new ArrayList<MedicalTestFactory>(medicaltests);
	}
	
}
