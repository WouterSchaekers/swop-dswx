package controllers;

import java.util.HashMap;
import java.util.Map;
import users.UserManager;

/**
 * This class manages all the managers used in this software package.
 * @author Stefaan, Dieter
 *
 */
public class Metamanager
{
	private Map<String, Controller> controllers;
	private Map<String, Manager> manager;

	public Metamanager() {
		controllers = new HashMap<String, Controller>();
		manager = new HashMap<String, Manager>();
		controllers.put("LoginController", new LoginController(this));
		manager.put("UserManager", new UserManager());
	}

	/**
	 * @param name
	 * the name of the requested controller 
	 * @return
	 * returns the requested controller 
	 */
	public Controller getController(String name) {
		Controller s = controllers.get(name);
		return s;
	}

	@SuppressWarnings("unchecked")
	public <T extends Manager> T getManager(String manager) {
		return (T) this.manager.get(manager);
	}
}
