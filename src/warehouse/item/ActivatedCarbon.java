package warehouse.item;

import scheduler.HospitalDate;

public class ActivatedCarbon extends WarehouseItem
{

	@Override
	public boolean isExpired(HospitalDate date) {
		return false;
	}

	@Override
	public WarehouseItemType getType() {
		return new ActivatedCarbonType();
	}

}
