package warehouse2.item;

import scheduler.HospitalDate;

public abstract class WarehouseItem
{
	public abstract boolean isExpired(HospitalDate date);
	public abstract WarehouseItemType getType();

}
