package warehouse.item;

import scheduler.HospitalDate;

public class Vitamin extends WarehouseItem
{

	Vitamin(HospitalDate expiryDate) {
		super(expiryDate);
	}
	
	@Override
	public boolean isExpiredAt(HospitalDate date) {
		return date.after(this.expiryDate);
	}

	@Override
	public WarehouseItemType getType() {
		return new VitaminType();
	}

	@Override
	public boolean equals(WarehouseItemType warehouseItemType) {
		return warehouseItemType instanceof VitaminType;
	}

}
