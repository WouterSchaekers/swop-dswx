package warehouse.item;

import scheduler.HospitalDate;

public class VitaminType extends MedicationType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new Vitamin(expirydate);
	}
	@Override
	public boolean equals(Object object)
	{
		return object instanceof VitaminType;
	}
}
