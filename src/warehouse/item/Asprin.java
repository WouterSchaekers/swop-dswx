package warehouse.item;

import scheduler.HospitalDate;

public class Asprin extends WarehouseItem
{
	Asprin(HospitalDate expiryDate) {
		super(expiryDate);
	}

	@Override
	public boolean isExpired(HospitalDate date) {
		return date.after(this.expiryDate);
	}

	@Override
	public WarehouseItemType getType() {
		return new AsprinType();
	}

}
