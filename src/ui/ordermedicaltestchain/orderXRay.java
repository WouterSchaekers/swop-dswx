package ui.ordermedicaltestchain;

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
		
		// ask for some input.
		System.out.println("Which bodypart would you like to xrayscan? ");	
		in = input.nextLine();
		System.out.println("\n");
		
		// check if the input is valid.
		if (in.isEmpty()) {
			System.out.println("You did not specify any body parts.");
			return this;
		}
		try {
			Integer.parseInt(in);
		} catch (NumberFormatException e) {
			System.out.println("You specified something numeric and not a bodypart.");
			return this;
		}
		medData.setBodyPart(in); // set the input
		
		// ask for more input.
		System.out.println("How many images would you like to take? ");
		in = (input.nextLine());
		System.out.println("\n");
		
		//check if the input is valid.
		if (in.isEmpty()) {
			System.out.println("You did not specify an amount of images.");
			return this;
		}
		try {
			int i = Integer.parseInt(in);
			if (i<=0) {
				System.out.println("Please specify an amount > 0");
				return this;
			}
		} catch (NumberFormatException e) {
			System.out.println("You specified something non-numeric.");
			return this;
		}
		medData.setAmount(Integer.parseInt(in)); //set the input
		
		// ask for more input.
		System.out.println("What zoomlevel would you like to be used? (1-3) ");
		in = (input.nextLine());
		System.out.println("\n");
		
		//check if the input is valid.
		if (in.isEmpty()) {
			System.out.println("You did not specify a zoomlevel.");
			return this;
		}
		try {
			int i = Integer.parseInt(in);
			if (i<1 || i > 3) {
				System.out.println("Please specify a valid zoomlevel");
				return this;
			}
		} catch (NumberFormatException e) {
			System.out.println("You specified an invalid zoomlevel (either non-numeric or not within the range of 1-3).");
			return this;
		}
		medData.setZoomlevel(Integer.parseInt(in));
	
		return new scheduleXRay(data,medData);
	}

}
