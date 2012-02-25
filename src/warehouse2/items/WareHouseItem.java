package warehouse2.items;

import scheduler.HospitalDate;

public abstract class WareHouseItem
{
	private HospitalDate _expiryDate;
	public WareHouseItem(HospitalDate expiryDate)
	{
		this._expiryDate = expiryDate.clone();
	}
	public  HospitalDate getExpiryDate(){return _expiryDate;}
}
