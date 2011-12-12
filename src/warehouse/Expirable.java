package warehouse;

import scheduler.HospitalDate;

public abstract interface Expirable
{
	public HospitalDate getExpiryDate();
	public boolean hasPassedDate(HospitalDate date);
}
