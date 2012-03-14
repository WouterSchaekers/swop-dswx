package ui.addhospitalstaff;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.AddHospitalStaffController;

public class NurseSelected extends Usecase
{
	private AddHospitalStaffController c;

	public NurseSelected(UserinterfaceData data, AddHospitalStaffController c) {
		super(data);
		this.c = c;
	}

	@Override
	public Usecase Execute() {
		try {
			System.out.println("Enter the name of the nurse!");
			String arg = input.nextLine();
			System.out.println("Enter nurse location: ");
			String arg2 = input.nextLine();
			c.addNurse(arg, arg2);

			System.out.println("Nurse " + arg + "succesfully created");
			return new SelectUsecase(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
