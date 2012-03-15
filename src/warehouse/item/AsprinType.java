package warehouse.item;

import scheduler.HospitalDate;

public class AsprinType extends WarehouseItemType
{

	@Override
	public WarehouseItem create(HospitalDate expiryDate) {
		return new Asprin(expiryDate);
	}

}
