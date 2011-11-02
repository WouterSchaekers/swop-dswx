package ui.ordermedicaltestchain;

import ui.UserinterfaceData;
import medicaltest.UltraSoundScan;
import ui.usecase;

public class orderUltraSoundScanTest extends MedicalTestCommand
{

	public orderUltraSoundScanTest(UserinterfaceData data,
			MedicalTestData medData) {
		super(data, medData);
	}

	@Override
	public usecase Execute() {
		UltraSoundScan ultrasound = new UltraSoundScan();
		String in = "";
		
		// ask for some input.
		System.out.println("What would you like to focus on in the tests? ");	
		in = input.nextLine();
		System.out.println("\n");
		
		// check if the input is valid.
		if (in.isEmpty()) {
			System.out.println("You did not specify any focusses.");
			return this;
		}
		try {
			Integer.parseInt(in);
		} catch (NumberFormatException e) {
			System.out.println("You specified something numeric and not a focus.");
			return this;
		}
		ultrasound.setFocus(in); // set the input
		
		// ask for more input.
		System.out.println("Would you like to record video?");
		in = (input.nextLine());
		System.out.println("\n");
		
		if(in.matches("[y|Y|j|J|yes|ja"))
			ultrasound.setVid(true);
		else if (in.matches("[n|N|no|nee"))
			ultrasound.setVid(false);
		else {
			System.out.println("Invalid answer! Please use Y/y/J/j/yes/N/n/no");
			return this;
		}
		
		// ask for more input.
		System.out.println("Would you like to record images?");
		in = (input.nextLine());
		System.out.println("\n");
		
		if(in.matches("[y|Y|j|J|yes|ja"))
			ultrasound.setImg(true);
		else if (in.matches("[n|N|no|nee"))
			ultrasound.setImg(false);
		else {
			System.out.println("Invalid answer! Please use Y/y/J/j/yes/N/n/no");
			return this;
		}
		medData.setTest(ultrasound); // save the bloodanalysis in medData
	
		return new retrieveNeededResources(data,medData);
	}

}
