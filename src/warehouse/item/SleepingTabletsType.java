package warehouse.item;

import scheduler.HospitalDate;

public class SleepingTabletsType extends WarehouseItemType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new SleepingTablets(expirydate);
	}

}
