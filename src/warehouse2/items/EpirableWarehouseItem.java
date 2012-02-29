package warehouse2.items;

import scheduler.HospitalDate;

public abstract class EpirableWarehouseItem implements WarehouseItem
{
	private final HospitalDate _expiryDate;
	/**
	 * 
	 * @param expiryDate
	 */
	public EpirableWarehouseItem(HospitalDate expiryDate) {
		this._expiryDate = expiryDate.clone();
	}
	
	
	public HospitalDate getExpiryDate() {
		return _expiryDate;
	}
	
}
