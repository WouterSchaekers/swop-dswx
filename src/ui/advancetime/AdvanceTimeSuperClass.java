package ui.advancetime;

import controllers.AdvanceTimeController;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidLoginControllerException;
import scheduler.HospitalDate;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class AdvanceTimeSuperClass extends Usecase
{

	public AdvanceTimeSuperClass(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Usecase Execute() {
		//Create controller
		AdvanceTimeController controller = null;
		try {
			controller = new AdvanceTimeController(data.getLoginController(), data.getDataPasser());
		} catch (InvalidLoginControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Advance the time to a new point in time:");
		System.out.println("Input: format is DD-MM-YYYY:hh:mm:ss");
		String date = input.nextLine();
		if(!date.matches("(([0-3])([0-9]))-(0|1)([0-9]))-(0|1|2|3)([0-9]){3}):([0-2])([0-9]):([0-6])([0-9]):([0-6])([0-9])"))
		{
			System.out.println("something went wrong");
			return new SelectUsecase(data);
		}
		
		String s = date.substring(0, 2);
		int day = new Integer(s);
		Integer.parseInt(s);
		 s = date.substring(0, 2);
		int month = new Integer(s);
		 s = date.substring(5, 7);
		int year = new Integer(s);
		 s = date.substring(0, 2);
		int hour = new Integer(s);
		s = date.substring(0, 2);
		int min = new Integer(s);
		s = date.substring(0, 2);
		int sec = new Integer(s);
		
		HospitalDate hospitalDate = null;
		hospitalDate = new HospitalDate(year,month,day,hour,min,sec);
		controller.setNewSystemTime(hospitalDate);
		System.out.println("succesfull !");
		// TODO Auto-generated method stub
		return new SelectUsecase(data);
	}
	public static void main(String[] args) throws InvalidHospitalDateArgument {
		HospitalDate d = new HospitalDate(1999,00,1,1,1,1);
		System.out.println(d);
	}
}
