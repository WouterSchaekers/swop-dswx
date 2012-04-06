package warehouse.item;

import scheduler.HospitalDate;
@warehouse.WareHouseAPI
public abstract class MedicationType extends WarehouseItemType
{

	@Override
	public long getTimeToLive() {
		return HospitalDate.ONE_MONTH;
	}

	@warehouse.WareHouseAPI
	public abstract String name() ;
}
