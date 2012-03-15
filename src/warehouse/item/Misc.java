package warehouse.item;

import scheduler.HospitalDate;

public class Misc extends WarehouseItem
{

	@Override
	public boolean isExpired(HospitalDate date) {
		return false;
	}

	@Override
	public WarehouseItemType getType() {
		return new MiscType();
	}

}
