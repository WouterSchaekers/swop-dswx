package system;

import machine.MachinePool;
import scheduler.HospitalDate;
import users.CampusUserManager;
import warehouse.Warehouse;

public class Campus
{
	private String _cpn;
	private CampusUserManager _cusm = new CampusUserManager();
	private MachinePool _mp = new MachinePool();
	private Warehouse _wh = new Warehouse(this);
	private Hospital _hpt;
	
	public Campus(String campusName, Hospital hospital){
		this._cpn = campusName;
		this._hpt = hospital;
	}
	
	public CampusUserManager getUserManager(){
		return _cusm;
	}
	
	public void setUserManager(CampusUserManager campusUserManager){
		this._cusm = campusUserManager;
	}
	
	public Hospital getHospital(){
		return this._hpt;
	}
	
	public void setHospital(Hospital hospital){
		this._hpt = hospital;
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
	
	public HospitalDate getSystemTime(){
		return this._hpt.getSystemTime();
	}
	
	public String getCampusName(){
		return this._cpn;
	}
}