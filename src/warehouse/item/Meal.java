package warehouse.item;

import scheduler.HospitalDate;

public class Meal extends WarehouseItem
{
	Meal(HospitalDate date) {
		super(date);
	}

	@Override
	public WarehouseItemType getType() {
		return new MealType();
	}

	@Override
	public boolean isExpired(HospitalDate date) {
		return date.after(this.expiryDate);
	}

}
