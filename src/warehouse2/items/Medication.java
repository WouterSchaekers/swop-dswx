package warehouse2.items;

import scheduler.HospitalDate;

public abstract class Medication extends EpirableWarehouseItem
{
	private final String _description;

	public String getDescription() {
		return _description;
	}


	public Medication(HospitalDate expiryDate, 
			String description) {
		super(expiryDate);
		this._description = description;
	}

}
