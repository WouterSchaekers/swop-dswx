package warehouse;

import scheduler.HospitalDate;
import users.WarehouseAdmin;

public abstract class StockOrder
{
	public final long TIME_FOR_EXPIRATION = HospitalDate.ONE_YEAR;

	public abstract HospitalDate getOrderDate();

	public abstract WarehouseAdmin getWarehouseAdmin();

	public abstract StockItem getStockItem();
	
	@Override
	public String toString(){
		return this.getStockItem().toString() + " ordered on " + this.getOrderDate();
	}
}