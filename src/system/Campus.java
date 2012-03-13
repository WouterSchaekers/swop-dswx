package system;

import machine.MachinePool;
import users.UserManager;
import warehouse.Warehouse;

public class Campus
{
	private UserManager _usm = new UserManager();
	private MachinePool _mp = new MachinePool();
	private Warehouse _wh = new Warehouse(); 
	
	public UserManager getUserManager(){
		return _usm;
	}
}