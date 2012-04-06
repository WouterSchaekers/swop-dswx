package warehouse.item;

import scheduler.HospitalDate;

@warehouse.WareHouseAPI
public class AsprinType extends MedicationType
{

	@Override
	public WarehouseItem create(HospitalDate expiryDate) {
		return new Asprin(expiryDate);
	}
	@Override
	public boolean equals(Object object)
	{
		return object instanceof AsprinType;
	}
	@Override
	@warehouse.WareHouseAPI
	public String name() {
		return "Aspirin";
	}
}
