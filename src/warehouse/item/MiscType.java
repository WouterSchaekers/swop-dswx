package warehouse.item;

import scheduler.HospitalDate;

public class MiscType extends WarehouseItemType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new Misc();
	}

}
