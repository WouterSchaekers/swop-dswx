package ui.loginchain;

import java.util.Map;
import ui.UserinterfaceData;
import ui.usecase;
import controllers.UserDTO;

/**
 * This class will see if the name entered by the current user is allowed to log in.
 */
public class validateLogin extends usecase
{
	/**
	 * Constructor where hashmap of registered users is copied to map.
	 * 		|this.map = map
	 * Name of the current user is copied to name.
	 * 		|this.name = name
	 */
	Map<String, UserDTO> map;
	String name;
	public validateLogin(UserinterfaceData data,Map<String,UserDTO> map,String name) {
		super(data);
		this.map=map;
		this.name=name;
	}

	/**
	 * Method to find out if the map with the name of all the registered users contains the name of the current user.
	 * If not the system will return to the use case display all names, where he sees all names and later he can reenter his name.
	 * If the map contains the name of the current user, the system will proceed to the next step in the chain. 
	 */
	@Override
	public usecase Execute() {
		if(map.containsKey(name))
			return new loginToSystem(data,map.get(name));
		return new displayAllNames(data);
	}

}
