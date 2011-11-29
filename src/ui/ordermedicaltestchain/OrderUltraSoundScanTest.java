package ui.ordermedicaltestchain;

import help.ValidUseCaseInputChecker;
import ui.UserinterfaceData;
import ui.Usecase;

public class OrderUltraSoundScanTest extends MedicalTestCommand
{

	public OrderUltraSoundScanTest(UserinterfaceData data,
			MedicalTestData medData) {
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

		// RECORD VIDEO
		System.out.println("Would you like to record video?");
		in = (input.nextLine());
		System.out.println("\n");

		if (ValidUseCaseInputChecker.isValidBooleanResponse(in))
			medData.setVid(ValidUseCaseInputChecker.toBool(in));
		else {
			System.out.println("Invalid yes or no response!");
			return this;
		}

		// RECORD IMAGES
		System.out.println("Would you like to record images?");
		in = (input.nextLine());
		System.out.println("\n");

		if (ValidUseCaseInputChecker.isValidBooleanResponse(in))
			medData.setImg(ValidUseCaseInputChecker.toBool(in));
		else {
			System.out.println("Invalid yes or no response!");
			return this;
		}

		return new ScheduleUltraSound(data, medData);
	}

}
