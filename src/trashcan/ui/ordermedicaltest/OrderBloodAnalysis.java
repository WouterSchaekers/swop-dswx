package ui.ordermedicaltest;

import medicaltest.BloodAnalysisFactory;
import ui.Usecase;
import ui.UserinterfaceData;

public class OrderBloodAnalysis extends OrderMedicalTestSuperClass
{

	public OrderBloodAnalysis(UserinterfaceData data, MedicalTestData medData) {
		super(data, medData);
	}

	@Override
	public Usecase Execute() {
		String in = "";
		BloodAnalysisFactory factory = (BloodAnalysisFactory) chaindata
				.getFactory();

		System.out.println("What would you like to focus on in the tests? ");
		in = input.nextLine();
		System.out.println("\n");
		try {
			factory.setFocus(in);
		} catch (IllegalArgumentException e) {
			System.out.println("focus invalid");
			System.out.println("You did not specify a valid focus!");
			return this;
		}

		// AMOUNT OF ANALYSIS
		System.out.println("How many analyses would you like to make? ");
		in = (input.nextLine());
		System.out.println("\n");
		try {
			factory.setNumberOfAnalysis(Integer.parseInt(in));
		} catch (Exception e) {
			System.out.println("Invalid number of analysis provided try again");
			return this;
		}

		return new ScheduleMedicalTest(data, chaindata);
	}

}