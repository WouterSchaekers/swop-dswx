package warehouse;

import scheduler.HospitalDate;

public abstract interface Expirable 
{
	public HospitalDate getExpiryDate();
	/**
	 * @return True if date is after the expiry date of this Expirable.
	 */
	public boolean hasPassedDate(HospitalDate date);
}
