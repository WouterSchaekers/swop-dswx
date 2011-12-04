package warehouse;

import java.util.Date;

public class Meal implements Expirable
{
	private final Date expiryDate;
	public Meal(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Override
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	@Override
	public boolean hasPassedDate(Date date){
		if(this.expiryDate.before(date)){
			return true;
		}
		else{
			return false;
		}
	}

}
