package system;

import machine.MachinePool;
import users.UserManager;
import warehouse.Warehouse;

public class Campus
{
	private UserManager _usm = new UserManager();
	private MachinePool _mp = new MachinePool();
	private Warehouse _wh = new Warehouse(); 
	Hospital _h = new Hospital();
	
	public UserManager getUserManager(){
		return _usm;
	}
	
	public void setUserManager(UserManager userManager){
		this._usm = userManager;
	}
	
	public Hospital getHospital(){
		return this._h;
	}
	
	public void setHospital(Hospital hospital){
		this._h = hospital;
	}
	
	public Warehouse getWarehouse(){
		return this._wh;
	}
	
	public void setWarehouse(Warehouse warehouse){
		this._wh = warehouse;
	}
	
	public MachinePool getMachinePool(){
		return this._mp;
	}
	
	public void setMachinePool(MachinePool machinePool){
		this._mp = machinePool;
	}
}