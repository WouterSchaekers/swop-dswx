package warehouse.item;

import scheduler.HospitalDate;

public class ActivatedCarbonType extends MedicationType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new ActivatedCarbon();
	}
	@Override
	public boolean equals(Object object)
	{
		return object instanceof ActivatedCarbonType;
	}
	@Override
	public String name() {
		return "Activated Carbon";
	}
}
