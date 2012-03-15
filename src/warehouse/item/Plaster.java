package warehouse.item;

import scheduler.HospitalDate;

public class Plaster extends WarehouseItem
{

	@Override
	public boolean isExpired(HospitalDate date) {
		return false;
	}

	@Override
	public WarehouseItemType getType() {
		return new PlasterType();
	}

}
