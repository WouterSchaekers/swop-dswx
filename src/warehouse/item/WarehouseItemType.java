package warehouse.item;

import scheduler.HospitalDate;

public abstract class WarehouseItemType
{

	public abstract WarehouseItem create(HospitalDate expirydate);

	public HospitalDate getExpirationDate(HospitalDate date) {
		return date;
	}
}
