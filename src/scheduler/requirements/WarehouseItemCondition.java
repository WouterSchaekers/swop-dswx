package scheduler.requirements;

import scheduler.HospitalDate;
import system.Campus;
import system.Location;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import exceptions.InvalidWarehouseItemException;

public class WarehouseItemCondition implements Requirement
{
	private WarehouseItemType warehouseItemType_;
	private int amount_;
	
	public WarehouseItemCondition(WarehouseItemType warehouseItemType, int amount){
		this.warehouseItemType_ = warehouseItemType;
		this.amount_ = amount;
	}
	
	@Override
	public boolean isMetBy(Requirable requirable) {
		return false;
	}
	
	@Override
	public boolean isCrucial(){
		return false;
	}

	@Override
	public boolean isMetOn(HospitalDate hospitalDate, Location location) {
		if(location instanceof Campus)
			return ((Campus) location).getWarehouse().hasAt(this.warehouseItemType_, this.amount_, hospitalDate);
		return false;
	}

	@Override
	public void collect(Warehouse warehouse) {
		try {
			warehouse.take(this.warehouseItemType_);
		} catch (InvalidWarehouseItemException e) {
			throw new Error(e.getMessage());
		}
	}

	@Override
	public boolean backToBack() {
		return false;
	}
	
	public int getAmount(){
		return this.amount_;
	}

	@Override
	public boolean isMarkedForDeletion() {
		return false;
	}
}