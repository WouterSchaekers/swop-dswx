package ui.loginchain;

import java.util.HashMap;
import java.util.Map;
import ui.UserinterfaceData;
import ui.usecase;
import controllers.UserDTO;

/**
 * This class prints all the names of the users that are logged in in the system. 
 * When this is done the system proceeds to the next step of the chain. 
 *
 */
public class displayAllNames extends usecase
{
	public displayAllNames(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Displays all the names of the users logged in. 
	 * Returns the next use case of the chain.
	 * In this method a hashmap is created where the name is the key and the corresponding user is the value.
	 * The data of the user and the created hashmap with all users in are given to the next use case in the chain.
	 */
	@Override
	public usecase Execute() {
		System.out.println("All users in the system:");
		Map<String, UserDTO> nameUserMap=new HashMap<String, UserDTO>();
		for(UserDTO u:data.getLoginController().getAllUsers2())
		{
			System.out.println(u.getName());
			nameUserMap.put(u.getName(), u);
		}
		return new login(data,nameUserMap);
	}

}
