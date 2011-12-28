package ui.ordermedicaltest;

import ui.Usecase;
import ui.UserinterfaceData;

public class SelectMedicalTest extends MedicalTestCommand
{

	public SelectMedicalTest(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		// TODO: fix
//		System.out
//				.println("\nWhich one would you like to order? (please type the name as it appears on your screen)\n");
//		String s = input.nextLine();
//		MedicalTestTypes[] testtypes //= MedicalTestTypes.values();
//		MedicalTestTypes testOfChoice = null;
//
//		for (int i = 0; i < testtypes.length; i++) {
//			if (s.equalsIgnoreCase(testtypes[i].toString())) {
//				testOfChoice = testtypes[i];
//				break;
//			}
//		}
//
//		if (testOfChoice == null) {
//			System.out.println("You made an invalid choice!");
//			return this;
//		}
//
//		switch (testOfChoice) {
//		case xrayscan:
//			return new OrderXRay(data, medData);
//		case ultrasoundscan:
//			return new OrderUltraSoundScanTest(data, medData);
//		case bloodanalysis:
//			return new OrderBloodAnalysis(data, medData);
//		default:
//			throw new IllegalStateException("This is not supposed to happen...");
//		}
		return null;
	}

}
