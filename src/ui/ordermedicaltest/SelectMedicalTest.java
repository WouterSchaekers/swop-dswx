package ui.ordermedicaltest;

import java.util.Collection;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTestFactory;
import medicaltest.UltraSoundScanFactory;
import medicaltest.XRayScanFactory;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;

public class SelectMedicalTest extends OrderMedicalTestSuperClass
{

	public SelectMedicalTest(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {

		Collection<MedicalTestFactory> factories;
		try {
			factories	 = this.chaindata
					.getMedTestController().getMedicalTestFactories(
							data.getLoginController(),
							data.getPatientFileOpenController());
		} catch (InvalidLoginControllerException e) {
			System.out.println("not allowed to do  this");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileException e) {
			System.out.println("invalid patientfile");
			return new SelectUsecase(data);
		}
		// we now have all the factories that can create a medical test object we will now do so :)
		System.out.println("Select the medical test you would like to order for "+data.getPatientFileOpenController().getPatientFile().getName() +".");
		System.out.println("1: Ultra sound scan");
		System.out.println("2: Blood analysis");
		System.out.println("3: XrayScan");
		System.out.println("Q: quit");
		String in = input.nextLine();
		if(in.equalsIgnoreCase("q"))
			return new SelectUsecase(data);
		int menuoption;
		try{
			menuoption = new Integer(in);
		}catch(Exception e){
			System.out.println("invalid nubmer try again");
			return this;
		}
		switch(menuoption)
		{
		case 1:
			chaindata.setFactory(getultra(factories));
			return new OrderUltraSoundScan(data,chaindata);
		case 2:
			chaindata.setFactory(getBlood(factories));
			return new OrderBloodAnalysis(data, chaindata);
		case 3:
			chaindata.setFactory(getXray(factories));
			return new OrderXrayScan(data,chaindata);
			default:
				System.out.println("Not valid input !");
				return this;
		}
	}
	private UltraSoundScanFactory getultra(Collection<MedicalTestFactory> facs)
	{
		for(MedicalTestFactory f:facs)
			if(f instanceof UltraSoundScanFactory)
				return (UltraSoundScanFactory)f;
		return null;
	}private BloodAnalysisFactory getBlood(Collection<MedicalTestFactory> facs)
	{
		for(MedicalTestFactory f:facs)
			if(f instanceof BloodAnalysisFactory)
				return (BloodAnalysisFactory)f;
		return null;
	}private XRayScanFactory getXray(Collection<MedicalTestFactory> facs)
	{
		for(MedicalTestFactory f:facs)
			if(f instanceof XRayScanFactory)
				return (XRayScanFactory)f;
		return null;
	}


}
