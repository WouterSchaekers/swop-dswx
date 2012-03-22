package system;

import machine.MachinePool;
import scheduler.HospitalDate;
import warehouse.Warehouse;

/**
 * This class represents a campus that's part of a hospital. Campusses have
 * their own MachinePool, Warehouse and name. Use this name to identify them.
 */
public class Campus implements Location
{
	private String campusName_;
	private MachinePool machinePool_ = new MachinePool();
	private Warehouse warehouse_ = new Warehouse(this);
	private Hospital hospital_;
	
	public Campus(String campusName, Hospital hospital){
		this.campusName_ = campusName;
		this.hospital_ = hospital;
	}
	
	public Hospital getHospital(){
		return this.hospital_;
	}
	
	public void setHospital(Hospital hospital){
		this.hospital_ = hospital;
	}
	
	public Warehouse getWarehouse(){
		return this.warehouse_;
	}
	
	public void setWarehouse(Warehouse warehouse){
		this.warehouse_ = warehouse;
	}
	
	public MachinePool getMachinePool(){
		return this.machinePool_;
	}
	
	public void setMachinePool(MachinePool machinePool){
		this.machinePool_ = machinePool;
	}
	
	public HospitalDate getSystemTime(){
		return this.hospital_.getSystemTime();
	}
	
	public String getCampusName(){
		return this.campusName_;
	}
}