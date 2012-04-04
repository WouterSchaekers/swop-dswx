package warehouse.item;

import scheduler.HospitalDate;

public class SleepingTabletsType extends MedicationType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new SleepingTablets(expirydate);
	}
	@Override
	public boolean equals(Object object)
	{
		return object instanceof SleepingTabletsType;
	}
}
