package warehouse;

import scheduler.HospitalDate;
import users.HospitalAdmin;

public abstract class StockOrder
{
	public final long TIME_FOR_EXPIRATION = HospitalDate.ONE_YEAR;
	
	public abstract HospitalDate getOrderDate();
	
	public abstract HospitalAdmin getHospitalAdmin();
	
	public abstract StockItem getStockItem();
}