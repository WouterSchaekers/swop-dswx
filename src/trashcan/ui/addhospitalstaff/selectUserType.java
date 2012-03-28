package ui.addhospitalstaff;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.AddHospitalStaffController;

public class selectUserType extends Usecase
{

	public selectUserType(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		try {
			AddHospitalStaffController c = null;
			c = new AddHospitalStaffController(data.getLoginController());

			for (usertype u : usertype.values())
				System.out.println(u.ordinal() + ": " + u.toString());
			System.out.println("q to quit");
			String in = input.nextLine();
			if (in.equalsIgnoreCase("q"))
				return new SelectUsecase(data);
			Integer i;
			try {
				i = new Integer(in);
			} catch (Exception e) {
				System.out.println("Invalid option try again");
				return new selectUserType(data);
			}
			if (i == usertype.nurse.ordinal())
				return new NurseSelected(data, c);
			if (i == usertype.doctor.ordinal())
				return new DoctorSelected(data, c);
			System.out.println("Invalid option try again");
			return new selectUserType(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
