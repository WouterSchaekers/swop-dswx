package ui.addhospitalstaff;

import controllers.CreateUserController;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class selectUserType extends Usecase
{
	
	public selectUserType(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		CreateUserController c = null;
		try {
			c = new CreateUserController(data.getLoginController(), data.getDataPasser());
		} catch (InvalidLoginControllerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidHospitalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(usertype u:usertype.values())
			System.out.println(u.ordinal()+": "+u.toString());
		System.out.println("q to quit");
		String in = input.nextLine();
		if(in.equalsIgnoreCase("q"))
			return new SelectUsecase(data);
		Integer i;
		try{
			 i = new Integer(in);
		}catch(Exception e)
		{
			System.out.println("Invalid option try again");
			return new selectUserType(data);
		}
		if(i==usertype.nurse.ordinal())
			return new NurseSelected(data,c);
		if(i==usertype.doctor.ordinal())
			return new DoctorSelected(data,c);
		System.out.println("Invalid option try again");
		return new selectUserType(data);
	}

}
