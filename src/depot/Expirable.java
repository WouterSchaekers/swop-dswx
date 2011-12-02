package depot;

import java.util.Date;

public abstract interface Expirable
{
	public Date getExpiryDate();
	public boolean hasPassedDate(Date date);
}
