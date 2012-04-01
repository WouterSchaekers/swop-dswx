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
	public boolean isExpiredAt(HospitalDate date) {
		return date.after(this.expiryDate);
	}

	@Override
	public boolean equals(WarehouseItemType warehouseItemType) {
		return warehouseItemType instanceof MealType;
	}

}
