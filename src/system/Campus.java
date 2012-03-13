package system;

import machine.MachinePool;
import users.UserManager;
import warehouse.Warehouse;

public class Campus
{
	UserManager _usm = new UserManager();
	MachinePool _mp = new MachinePool();
	Warehouse _wh = new Warehouse();
	
	public UserManager getUserManager(){
		return _usm;
	}
}