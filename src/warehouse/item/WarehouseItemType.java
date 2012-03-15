package warehouse.item;

import scheduler2.HospitalDate;

public abstract class WarehouseItemType
{
	
	public abstract WarehouseItem create(HospitalDate expirydate);
	public HospitalDate getExpirationDate(HospitalDate date)
	{
		return new HospitalDate(date.getTimeSinceStart()+HospitalDate.ONE_YEAR);
	}
}
