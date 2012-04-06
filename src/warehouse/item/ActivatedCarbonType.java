package warehouse.item;

import scheduler.HospitalDate;

@warehouse.WareHouseAPI
public class ActivatedCarbonType extends MedicationType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new ActivatedCarbon();
	}

	@Override
	public boolean equals(Object object) {
		return object instanceof ActivatedCarbonType;
	}

	@Override
	@warehouse.WareHouseAPI
	public String name() {
		return "Activated Carbon";
	}
}
