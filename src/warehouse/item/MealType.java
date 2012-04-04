package warehouse.item;

import scheduler.HospitalDate;

public class MealType extends WarehouseItemType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new Meal(expirydate);
	}
	@Override
	public boolean equals(Object object)
	{
		return object instanceof MealType;
	}
}
