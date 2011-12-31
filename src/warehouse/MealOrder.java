package warehouse;

import scheduler.HospitalDate;
import users.HospitalAdmin;

public class MealOrder extends StockOrder
{
	private final HospitalDate orderDate;
	private final HospitalAdmin hospitalAdmin;
	
	public MealOrder(HospitalDate orderDate, HospitalAdmin hospitalAdmin){
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
	public Meal getStockItem(){
		return new Meal(new HospitalDate(orderDate.getTimeSinceStart() + this.TIME_FOR_EXPIRATION));
	}
}