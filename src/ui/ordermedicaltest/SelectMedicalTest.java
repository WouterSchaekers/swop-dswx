package ui.ordermedicaltest;

import java.util.Collection;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTestFactory;
import medicaltest.UltraSoundScanFactory;
import medicaltest.XRayScanFactory;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class SelectMedicalTest extends OrderMedicalTestSuperClass
{

	public SelectMedicalTest(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		System.out
				.println("\nWhich one would you like to order? (please type the name as it appears on your screen)\n");
		String s = input.nextLine();
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
		
		
		return new SelectUsecase(data);
	}
	
	private String name(MedicalTestFactory fac) {
		if(fac instanceof UltraSoundScanFactory)
			return"UltraSoundScan ";
		if(fac instanceof XRayScanFactory)
			return"X-RayScan ";
		if(fac instanceof BloodAnalysisFactory)
			return"BloodAnalysis ";
		return "";
	}

	public static void main(String[] args) {

	}

}
