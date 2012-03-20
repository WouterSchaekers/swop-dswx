package warehouse.item;

import scheduler.HospitalDate;

public class MiscType extends MedicationType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new Misc();
	}

}
