package warehouse2.item;

import scheduler.HospitalDate;

public class Meal extends WarehouseItem
{
	
	Meal(HospitalDate date)
	{
		
	}
	@Override
	public WarehouseItemType getType() {
		return new MealType();
	}

	@Override
	public boolean isExpired(HospitalDate date) {
		// TODO Auto-generated method stub
		return false;
	}

}
