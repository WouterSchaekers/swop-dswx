package warehouse.item;

import scheduler.HospitalDate;

@warehouse.WareHouseAPI
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
	
	@Override
	public long getTimeToLive() {
		return HospitalDate.ONE_DAY * 7;
	}
	@Override
	public String name() {
		return "Meal";
	}
}
