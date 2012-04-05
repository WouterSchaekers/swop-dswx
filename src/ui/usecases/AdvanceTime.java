package ui.usecases;

import scheduler.HospitalDate;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import controllers.AdvanceTimeController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidSystemTimeException;

public class AdvanceTime extends UseCase
{

	private AdvanceTimeController controller;

	public AdvanceTime(UIData data) throws InvalidLoginControllerException, InvalidHospitalException {
		super(data, 2);
		controller = new AdvanceTimeController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		print("Advance time: current time is");
		printLn(controller.getTime().toString());
		printLn("Set new date in the following format:");
		printLn("yyyy-mm-dd hh:mm:ss");
		printLn("example: 2012/04/03 05:55:33");
		String input = read();
		if(input.length()!=19)
		{
			printLn("Input Length is not correct.");
		}
		try{
		int year =new Integer(input.substring(0,4 ));
		int month =new Integer(input.substring(5, 7));
		int day =new Integer(input.substring(8, 10));
		int hours =new Integer(input.substring(11,13));
		int minutes =new Integer(input.substring(14,16 ));
		int seconds =new Integer(input.substring(17,19 ));
		try {
			controller.setNewSystemTime(new HospitalDate(year, month, day, hours, minutes, seconds));
		} catch (InvalidSystemTimeException e) {
		printLn("New time must be after current time.");
		}
		}catch(Exception e){
			printLn(e.getMessage());
			return new MainMenu(data);
		}
		
		printLn("New systemtime is:"+controller.getTime().toString());
		return new MainMenu(data);
	}

}
