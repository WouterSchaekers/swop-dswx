package warehouse.item;

import scheduler.HospitalDate;

public class SleepingTablets extends WarehouseItem
{
	SleepingTablets(HospitalDate expiryDate) {
		super(expiryDate);
	}

	@Override
	public boolean isExpiredAt(HospitalDate date) {
		return date.after(this.expiryDate);
	}

	@Override
	public WarehouseItemType getType() {
		return new SleepingTabletsType();
	}

	@Override
	public boolean equals(WarehouseItemType warehouseItemType) {
		return warehouseItemType instanceof SleepingTabletsType;
	}

}
