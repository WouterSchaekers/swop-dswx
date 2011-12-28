package ui.ordermedicaltest;

import help.ValidUseCaseInputChecker;
import ui.UserinterfaceData;
import ui.Usecase;

public class OrderBloodAnalysis extends MedicalTestCommand
{

	public OrderBloodAnalysis(UserinterfaceData data, MedicalTestData medData) {
		super(data, medData);
	}

	@Override
	public Usecase Execute() {
		String in = "";

		// FOCUS
		System.out.println("What would you like to focus on in the tests? ");
		in = input.nextLine();
		System.out.println("\n");

		if (ValidUseCaseInputChecker.isValidStringResponse(in))
			medData.setFocus(in);
		else {
			System.out.println("You did not specify a valid focus!");
			return this;
		}

		// AMOUNT OF ANALYSIS
		System.out.println("How many analyses would you like to make? ");
		in = (input.nextLine());
		System.out.println("\n");

		if (ValidUseCaseInputChecker.isNumeric(in))
			medData.setAmount(Integer.parseInt(in));
		else {
			System.out.println("Invalid amount of analyses!");
			return this;
		}

		return new ScheduleBloodAnalysis(data, medData);
	}

}