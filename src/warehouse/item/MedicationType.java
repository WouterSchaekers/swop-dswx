package warehouse.item;

import scheduler.HospitalDate;

public abstract class MedicationType extends WarehouseItemType
{

	@Override
	public long getTimeToLive() {
		return HospitalDate.ONE_MONTH;
	}

	public abstract String name() ;
}
