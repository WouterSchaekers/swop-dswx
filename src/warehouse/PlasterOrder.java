package warehouse;

import scheduler.HospitalDate;
import users.HospitalAdmin;

public class PlasterOrder extends StockOrder implements StockItem
{
	private final HospitalDate orderDate;
	private final HospitalAdmin hospitalAdmin;
	
	public PlasterOrder(HospitalDate orderDate, HospitalAdmin hospitalAdmin){
		this.orderDate = orderDate;
		this.hospitalAdmin = hospitalAdmin;
	}
	
	@Override
	public HospitalDate getOrderDate(){
		return new HospitalDate(this.orderDate);
	}
	
	@Override
	public HospitalAdmin getHospitalAdmin(){
		return this.hospitalAdmin;
	}

	@Override
	public Plaster getStockItem() {
		return new Plaster();
	}
}