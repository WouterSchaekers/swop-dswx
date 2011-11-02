package ui.ordermedicaltestchain;

import resources.MedicalTestTypes;
import ui.UserinterfaceData;
import ui.usecase;

public class selectMedicalTest extends MedicalTestCommand
{

	public selectMedicalTest(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}
	
	@Override
	public usecase Execute() {
		System.out.println("\nWhich one would you like to order? (please type the name as it appears on your screen)\n");
		String s = input.nextLine();
		MedicalTestTypes[] testtypes = MedicalTestTypes.values();
		
		for(int i = 0; i < testtypes.length;i++) {
			if(s.equalsIgnoreCase(testtypes[i].toString())) {
				medData.setTestOfChoice(MedicalTestTypes.valueOf(s));
				break;
			}
		}
		switch (medData.testOfChoice) {
		case xrayscan: // xrayscanner case
			return new XRayScanTest(data,medData);
		case ultrasoundscan:
			return new UltraSoundScanTest(data,medData);
		case bloodanalysis:
			return new BloodAnalysisTest(data,medData);
		default:
			System.out.println("Invalid treatment selected!");
			return this;
		}
	}

}
