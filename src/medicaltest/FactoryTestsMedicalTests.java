package medicaltest;

import static org.junit.Assert.*;
import java.util.Collection;
import org.junit.Test;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
@Deprecated
/**
 * Use Filter from the collections package instead
 */
public class FactoryTestsMedicalTests
{

	public static final UltraSoundScanFactory filterUltraSoundFactory(Collection<MedicalTestFactory> f)
	{
		for(MedicalTestFactory s:f)
			if(s instanceof UltraSoundScanFactory)
				return (UltraSoundScanFactory)s;
		throw new IllegalArgumentException();
	}
	public static final XRayScanFactory filterXrayScan(Collection<MedicalTestFactory> f)
	{
		for(MedicalTestFactory s:f)
			if(s instanceof XRayScanFactory)
				return (XRayScanFactory)s;
		throw new IllegalArgumentException();
	}
	public static final BloodAnalysisFactory filterBloodAnalysis(Collection<MedicalTestFactory> f)
	{
		for(MedicalTestFactory s:f)
			if(s instanceof BloodAnalysisFactory)
				return (BloodAnalysisFactory)s;
		throw new IllegalArgumentException();
	}
	
	MedicalTests m = new MedicalTests();
	@Test
	public void createUltraSoundScan1() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		UltraSoundScanFactory fac= filterUltraSoundFactory(m.factories());
		fac.setRecordImages(true);
		fac.setRecordVid(true);
		fac.setScanInfo("Scaninfo");
		UltraSoundScan scan = (UltraSoundScan) fac.create();
		assertTrue(scan.getScaninfo().equals("Scaninfo"));
		assertTrue(scan.hasImageRecordingEnabled());
		assertTrue(scan.hasVideoRecordingEnabled());
		
	}
	@Test
	public void createUltraSoundScan2() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		UltraSoundScanFactory fac= filterUltraSoundFactory(m.factories());
		fac.setRecordImages(false);
		fac.setRecordVid(true);
		fac.setScanInfo("Scaninfo");
		UltraSoundScan scan = (UltraSoundScan) fac.create();
		assertTrue(scan.getScaninfo().equals("Scaninfo"));
		assertTrue(scan.hasImageRecordingEnabled()==false);
		assertTrue(scan.hasVideoRecordingEnabled());
		
	}
	@Test
	public void createUltraSoundScan() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		UltraSoundScanFactory fac= filterUltraSoundFactory(m.factories());
		fac.setRecordImages(true);
		fac.setRecordVid(false);
		fac.setScanInfo("Scaninfo");
		UltraSoundScan scan = (UltraSoundScan) fac.create();
		assertTrue(scan.getScaninfo().equals("Scaninfo"));
		assertTrue(scan.hasImageRecordingEnabled());
		assertTrue(scan.hasVideoRecordingEnabled()==false);
		
	}
	@Test
	public void createUltraSoundScan3() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		UltraSoundScanFactory fac= filterUltraSoundFactory(m.factories());
		fac.setRecordImages(false);
		fac.setRecordVid(false);
		fac.setScanInfo("Scaninfo");
		UltraSoundScan scan = (UltraSoundScan) fac.create();
		assertTrue(scan.getScaninfo().equals("Scaninfo"));
		assertTrue(scan.hasImageRecordingEnabled()==false);
		assertTrue(scan.hasVideoRecordingEnabled()==false);
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void createUltraSoundScanError1() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		UltraSoundScanFactory fac= filterUltraSoundFactory(m.factories());
		fac.create();
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void createUltraSoundScanError2() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		UltraSoundScanFactory fac= filterUltraSoundFactory(m.factories());
		fac.setRecordImages(true);
		fac.create();
	}
	@Test(expected=IllegalArgumentException.class)
	public void createUltraSoundScanError3() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		UltraSoundScanFactory fac= filterUltraSoundFactory(m.factories());
		fac.setRecordImages(true);
		fac.setRecordVid(true);
		fac.create();
	}
	@Test(expected=IllegalArgumentException.class)
	public void createUltraSoundScanError4() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		UltraSoundScanFactory fac= filterUltraSoundFactory(m.factories());
		fac.setRecordImages(true);
		fac.setRecordVid(true);
		fac.setScanInfo(null);
		fac.create();
	}
	@Test
	public void createBloodAnalysisTest1() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		BloodAnalysisFactory fac= filterBloodAnalysis(m.factories());
		fac.setFocus("focus");
		fac.setNumberOfAnalysis(3);
		BloodAnalysis scan = (BloodAnalysis) fac.create();
		assertTrue(scan.getAmount()==3);
		assertTrue(scan.getFocus().equals("focus"));
		
	}
	@Test
	public void createBloodAnalysisTest2() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		BloodAnalysisFactory fac= filterBloodAnalysis(m.factories());
		fac.setFocus("focus");
		fac.setNumberOfAnalysis(4);
		BloodAnalysis scan = (BloodAnalysis) fac.create();
		assertTrue(scan.getAmount()==4);
		assertTrue(scan.getFocus().equals("focus"));
		
	}
	@Test(expected = IllegalArgumentException.class)
	public void createBloodAnalysisTesterror1() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		BloodAnalysisFactory fac= filterBloodAnalysis(m.factories());
		fac.setFocus("focus");
		fac.setNumberOfAnalysis(0);
		fac.create();		
	}
	@Test(expected = IllegalArgumentException.class)
	public void createBloodAnalysisTesterror2() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		BloodAnalysisFactory fac= filterBloodAnalysis(m.factories());
		fac.create();		
	}
	@Test(expected = IllegalArgumentException.class)
	public void createBloodAnalysisTesterror3() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		BloodAnalysisFactory fac= filterBloodAnalysis(m.factories());
		fac.setFocus("f");
		fac.create();		
	}
	@Test(expected = IllegalArgumentException.class)
	public void createBloodAnalysisTesterror4() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException
	{
		BloodAnalysisFactory fac= filterBloodAnalysis(m.factories());
		fac.setNumberOfAnalysis(4);
		fac.create();		
	}

}
