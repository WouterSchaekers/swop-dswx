package ui.loginchain;

import java.util.Map;
import ui.UserinterfaceData;
import ui.usecase;
import controllers.DTOUser;

/**
 * In this class the user can give his name to log in. 
 * When this is done the system will proceed to the next step of the chain to see if this user can is validated to log in. 
 */
public class login extends usecase
{
	/**
	 * Constructor of the class where the map with all registered users is copied to map.
	 * 		|this.map = nameUserMap;
	 */
	Map<String, DTOUser> map;
	public login(UserinterfaceData data,Map<String,DTOUser> nameUserMap) {
		super(data);
		this.map=nameUserMap;
		// TODO Auto-generated constructor stub
	}

	/**
	 * The user enters his name, the system proceeds to the next step of the chain to see if the name entered is valid.
	 * The map of all registered users is given to the next together with the name of the current user.
	 */
	@Override
	public usecase Execute() {
		System.out.println("Enter User name:");
		String name = input.nextLine();
		return new validateLogin(data,map,name);
	}

}
