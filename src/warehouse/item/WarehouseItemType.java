package warehouse.item;

import scheduler.HospitalDate;
import scheduler.requirements.Requirable;

public abstract class WarehouseItemType implements Requirable
{
	public abstract WarehouseItem create(HospitalDate expirydate);
	public abstract long getTimeToLive();
}
