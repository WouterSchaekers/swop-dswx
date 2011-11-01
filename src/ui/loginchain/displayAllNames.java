package ui.loginchain;

import java.util.Collection;
import ui.UserinterfaceData;
import ui.usecase;
import controllers.DTOUser;

/**
 * This class prints all the names of the users that are logged in in the system. 
 * When this is done the system proceeds to the next step of the chain. 
 *
 */
public class displayAllNames extends LoginCommand
{
	public displayAllNames(UserinterfaceData uiData, LoginData loginData) {
		super(uiData,loginData);
	}

	/**
	 * Displays all the names of the users logged in. 
	 * Returns the next use case of the chain.
	 * In this method a hashmap is created where the name is the key and the corresponding user is the value.
	 * The data of the user and the created hashmap with all users in are given to the next use case in the chain.
	 */
	@Override
	public usecase Execute() {
		System.out.println("All users registered in the system:");
		Collection<DTOUser> userCol = data.getLoginController().getAllUsers();
		
		for(DTOUser u: userCol) {
			System.out.println("* " + u.getName() + "\n");
		}
		
		return new login(data,loginData);
	}

}
