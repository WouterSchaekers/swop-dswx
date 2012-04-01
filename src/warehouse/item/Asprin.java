package warehouse.item;

import scheduler.HospitalDate;

public class Asprin extends WarehouseItem
{
	Asprin(HospitalDate expiryDate) {
		super(expiryDate);
	}

	@Override
	public boolean isExpiredAt(HospitalDate date) {
		return date.after(this.expiryDate);
	}

	@Override
	public WarehouseItemType getType() {
		return new AsprinType();
	}

	@Override
	public boolean equals(WarehouseItemType warehouseItemType) {
		return warehouseItemType instanceof AsprinType;
	}

}
