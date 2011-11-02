package ui.ordermedicaltestchain;

import medicaltest.BloodAnalysis;
import ui.UserinterfaceData;
import ui.usecase;

public class orderBloodAnalysis extends MedicalTestCommand
{

	public orderBloodAnalysis(UserinterfaceData data, MedicalTestData medData) {
		super(data,medData);
	}

	@Override
	public usecase Execute() {
		BloodAnalysis blood = new BloodAnalysis();
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
		blood.setFocus(in); // set the input
		
		// ask for more input.
		System.out.println("How many analyses would you like to make? ");
		in = (input.nextLine());
		System.out.println("\n");
		
		//check if the input is valid.
		if (in.isEmpty()) {
			System.out.println("You did not specify an amount of analyses.");
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
		blood.setAmountOfAnalyses(Integer.parseInt(in)); //set the input
		
		medData.setTest(blood); // save the bloodanalysis in medData
	
		return new retrieveNeededResources(data,medData);
	}

}