package warehouse.item;

import scheduler.HospitalDate;

public class MiscType extends MedicationType
{

	@Override
	public WarehouseItem create(HospitalDate expirydate) {
		return new Misc();
	}

	@Override
	public boolean equals(Object object) {
		return object instanceof MiscType;
	}

	@Override
	public long getTimeToLive() {
		return HospitalDate.ONE_YEAR * 100;
	}

	@Override
	public String name() {
		return "misc";
	}

}
