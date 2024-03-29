package warehouse.item;

import scheduler.HospitalDate;

public class ActivatedCarbon extends WarehouseItem
{

	@Override
	public boolean isExpiredAt(HospitalDate date) {
		return false;
	}

	@Override
	public WarehouseItemType getType() {
		return new ActivatedCarbonType();
	}

	@Override
	public boolean equals(WarehouseItemType warehouseItemType) {
		return warehouseItemType instanceof ActivatedCarbonType;
	}

}
