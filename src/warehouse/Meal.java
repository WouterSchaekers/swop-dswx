package warehouse;

import scheduler.HospitalDate;


public class Meal implements Expirable, StockItem
{
	private final HospitalDate expiryDate;
	public Meal(HospitalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Override
	public HospitalDate getExpiryDate() {
		return this.expiryDate;
	}

	@Override
	public boolean hasPassedDate(HospitalDate date){
		if(this.expiryDate.before(date)){
			return true;
		}
		else{
			return false;
		}
	}
}
