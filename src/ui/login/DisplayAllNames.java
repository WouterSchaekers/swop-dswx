package ui.login;

import java.util.Collection;
import ui.UserinterfaceData;
import ui.Usecase;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.HospitalAdminIN;
import controllers.interfaces.NurseIN;
import controllers.interfaces.UserIN;
import controllers.interfaces.WarehouseAdminIN;

/**
 * This class prints all the names of the users that are logged in in the
 * system. When this is done the system proceeds to the next step of the chain.
 * 
 */
public class DisplayAllNames extends LoginCommand
{
	public DisplayAllNames(UserinterfaceData uiData, LoginData loginData) {
		super(uiData, loginData);
	}

	/**
	 * Displays all the names of the users logged in. Returns the next use case
	 * of the chain. In this method a hashmap is created where the name is the
	 * key and the corresponding user is the value. The data of the user and the
	 * created hashmap with all users in are given to the next use case in the
	 * chain.
	 */
	@Override
	public Usecase Execute() {
		System.out.println("All users registered in the system:");
		System.out.println("Enter the user's name to log in as that user!");
		Collection<UserIN> userCol = data.getLoginController().getAllUsers();

		for (UserIN u : userCol) {
			System.out.println("* " + u.getName()+" "+type(u) );
		}

		return new Login(data, loginData);
	}

	private String type(UserIN u) {
		if(u instanceof DoctorIN)
			return"Doc";
		if(u instanceof NurseIN)
			return "Nurse";
		if(u instanceof HospitalAdminIN)
			return "Hospital admin";
		if(u instanceof WarehouseAdminIN)
			return "Warehouse admin";
		return "unknown";
	}

}
