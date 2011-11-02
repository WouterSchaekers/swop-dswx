package ui.ordermedicaltestchain;

import help.ValidUseCaseInputChecker;
import ui.UserinterfaceData;
import ui.usecase;

public class orderXRay extends MedicalTestCommand
{

	public orderXRay(UserinterfaceData data, MedicalTestData medData) {
		super(data,medData);
	}

	@Override
	public usecase Execute() {
		String in = "";
		
		// BODYPART
		System.out.println("Which bodypart would you like to xrayscan? ");	
		in = input.nextLine();
		System.out.println("\n");
	
		if(ValidUseCaseInputChecker.isValidStringResponse(in))
			medData.setBodyPart(in); // set bodypart
		else {
			System.out.println("Invalid bodypart!");
			return this;
		}
		
		// AMOUNT OF IMAGES
		System.out.println("How many images would you like to take? ");
		in = (input.nextLine());
		System.out.println("\n");

		if (!ValidUseCaseInputChecker.isNumeric(in)) {
			System.out.println("You did not specify a valid amount of images.");
			return this;
		}
		medData.setAmount(Integer.parseInt(in));
		
		// ZOOMLEVEL
		System.out.println("What zoomlevel would you like to be used? (1-3) ");
		in = (input.nextLine());
		System.out.println("\n");

		if (!(ValidUseCaseInputChecker.isNumeric(in) && Integer.parseInt(in) >= 1 && Integer.parseInt(in) <= 3)) {
			System.out.println("You did not specify a valid zoomlevel.");
			return this;
		}
		medData.setZoomlevel(Integer.parseInt(in));
	
		return new scheduleXRay(data,medData);
	}

}
