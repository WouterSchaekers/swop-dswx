package warehouse.item;

import scheduler.HospitalDate;

public class VitaminType extends WarehouseItemType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new Vitamin(expirydate);
	}

}
