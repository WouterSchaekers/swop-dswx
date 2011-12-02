package depot;

import java.util.Date;

public class Meal implements CanExpireObject
{
	private final Date expirationDate;
	public Meal(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public Date getExpiryDate() {
		return this.expirationDate;
	}

}
