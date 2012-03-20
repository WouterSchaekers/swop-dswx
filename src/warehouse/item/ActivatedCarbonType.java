package warehouse.item;

import scheduler.HospitalDate;

public class ActivatedCarbonType extends MedicationType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new ActivatedCarbon();
	}

}
