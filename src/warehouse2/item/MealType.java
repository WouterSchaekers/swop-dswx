package warehouse2.item;

import scheduler.HospitalDate;

public class MealType extends WarehouseItemType
{
	@Override
	public Meal create()
	{
		return new Meal(new HospitalDate());
	}
}
