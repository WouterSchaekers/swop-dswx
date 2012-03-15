package ui.advancetime;

import scheduler2.HospitalDate;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.AdvanceTimeController;

public class AdvanceTimeSuperClass extends Usecase
{

	public AdvanceTimeSuperClass(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		// Create controller
		try {
			AdvanceTimeController controller = new AdvanceTimeController(
					data.getLoginController());

			System.out.println("Advance the time to a new point in time:");
			System.out.println("Input: format is DD-MM-YYYY:hh:mm:ss");
			String date = input.nextLine();
			if (!date
					.matches("(([0-3])([0-9]))-(0|1)([0-9]))-(0|1|2|3)([0-9]){3}):([0-2])([0-9]):([0-6])([0-9]):([0-6])([0-9])")) {
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

			HospitalDate hospitalDate = new HospitalDate(year, month, day,
					hour, min, sec);

			controller.setNewSystemTime(hospitalDate);

			System.out.println("succesfull !");
			return new SelectUsecase(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}
}
