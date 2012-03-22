package scheduler.requirements;

import exceptions.InvalidWarehouseItemException;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;

public class WarehouseItemCondition implements Requirement
{
	private WarehouseItemType warehouseItemType_;
	private Warehouse warehouse_;
	
	public WarehouseItemCondition(WarehouseItemType warehouseItemType, Warehouse warehouse){
		this.warehouseItemType_ = warehouseItemType;
		this.warehouse_ = warehouse;
	}
	
	@Override
	public boolean isMetBy(Requirable requirable) {
		return false;
	}

	@Override
	public boolean isMet() {
		return this.warehouse_.has(this.warehouseItemType_, 1);
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
}