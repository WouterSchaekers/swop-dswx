package depot;

import java.util.Date;

public abstract class CanExpireObject
{
	public final Date expiryDate;
	public CanExpireObject(Date expiryDate){
		this.expiryDate = expiryDate;
	}
	public Date getExpiryDate(){
		return expiryDate;
	}
}
