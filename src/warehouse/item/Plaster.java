package warehouse.item;

import scheduler.HospitalDate;

public class Plaster extends WarehouseItem
{

	@Override
	public boolean isExpired(HospitalDate date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WarehouseItemType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}