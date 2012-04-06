package warehouse.item;

import scheduler.HospitalDate;

public class PlasterType extends WarehouseItemType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new Plaster();
	}
	@Override
	public boolean equals(Object object)
	{
		return object instanceof PlasterType;
	}
	
	@Override
	public long getTimeToLive() {
		return HospitalDate.ONE_YEAR * 100;
	}
}
