package ui.addhospitalstaff;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.AddHospitalStaffController;

public class DoctorSelected extends Usecase
{

	private AddHospitalStaffController c;

	public DoctorSelected(UserinterfaceData data, AddHospitalStaffController c) {
		super(data);
		this.c = c;
	}

	@Override
	public Usecase Execute() {
		try {
		System.out.println("Enter the doctors name:");
		String arg = input.nextLine();
		System.out.println("Enter doctor's whereabouts: ");
//		String arg2 = input.nextLine();
//			c.addDoctor(arg, arg2);
//		
		System.out.println("Doctor " + arg + " was succesfully created");
		return new SelectUsecase(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
