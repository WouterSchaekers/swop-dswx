package warehouse.item;

import scheduler2.HospitalDate;

public class ActivatedCarbon extends WarehouseItem
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
