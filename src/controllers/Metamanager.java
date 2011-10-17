package controllers;

import java.util.HashMap;
import java.util.Map;
import users.UserManager;

public class Metamanager
{
	private Map<String,Controller> controllers;
	private Map<String,Manager> manager;
	public Metamanager()
	{
		
	}
	public void intializeSystem()
	{
		controllers =new HashMap<String, Controller>();
		manager = new HashMap<String, Manager>();
		controllers.put("LoginController", new LoginController(this));
		manager.put("UserManager", new UserManager());
	}
	public Controller getController(String name)
	{
		Controller s =controllers.get(name);
		return s;
	}
	public <T extends Manager> T getManager(String manager)
	{
		return (T) this.manager.get(manager);
	}
}
