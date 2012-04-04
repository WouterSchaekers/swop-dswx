package warehouse.item;

import scheduler.HospitalDate;

public class AsprinType extends MedicationType
{

	@Override
	public WarehouseItem create(HospitalDate expiryDate) {
		return new Asprin(expiryDate);
	}
	@Override
	public boolean equals(Object object)
	{
		return object instanceof AsprinType;
	}
}
