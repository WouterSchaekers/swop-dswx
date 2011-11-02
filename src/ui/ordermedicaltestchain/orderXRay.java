package ui.ordermedicaltestchain;

import medicaltest.XRayScan;
import ui.UserinterfaceData;
import ui.usecase;

public class orderXRay extends MedicalTestCommand
{

	public orderXRay(UserinterfaceData data, MedicalTestData medData) {
		super(data,medData);
	}

	@Override
	public usecase Execute() {
		XRayScan xray = new XRayScan();
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
		xray.setBodyPart(in); // set the input
		
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
		xray.setAmountOfImages(Integer.parseInt(in)); //set the input
		
		medData.setTest(xray); // save the xray in medData
	
		return new retrieveNeededResources(data,medData);
	}

}
