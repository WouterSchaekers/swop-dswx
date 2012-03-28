package scheduler.requirements;

import scheduler.HospitalDate;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import exceptions.InvalidWarehouseItemException;

public class WarehouseItemCondition implements Requirement
{
	private WarehouseItemType warehouseItemType_;
	private Warehouse warehouse_;
	private int amount_;
	
	public WarehouseItemCondition(WarehouseItemType warehouseItemType, Warehouse warehouse, int amount){
		this.warehouseItemType_ = warehouseItemType;
		this.warehouse_ = warehouse;
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
	public boolean isMetOn(HospitalDate hospitalDate) {
		return this.warehouse_.hasAt(this.warehouseItemType_, 1, hospitalDate);
	}

	@Override
	public void collect() {
		try {
			this.warehouse_.take(this.warehouseItemType_);
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
}