package warehouse.item;

import scheduler.HospitalDate;

public class Plaster extends WarehouseItem
{

	@Override
	public boolean isExpiredAt(HospitalDate date) {
		return false;
	}

	@Override
	public WarehouseItemType getType() {
		return new PlasterType();
	}

}
