package warehouse.item;

import scheduler.HospitalDate;

public class PlasterType extends WarehouseItemType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new Plaster();
	}

}
