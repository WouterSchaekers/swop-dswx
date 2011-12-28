package ui.addhospitalstaff;

import controllers.CreateUserController;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class selectUserType extends Usecase
{
	
	public selectUserType(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usecase Execute() {
		CreateUserController c = new CreateUserController(data.getLoginController(), data.getDataPasser());
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
