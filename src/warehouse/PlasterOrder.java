package warehouse;

import scheduler.HospitalDate;

public class PlasterOrder implements StockOrder
{
	private final HospitalDate orderDate;
	
	public PlasterOrder(HospitalDate orderDate){
		this.orderDate = orderDate;
	}
	
	public HospitalDate getOrderDate(){
		return new HospitalDate(this.orderDate);
	}
}