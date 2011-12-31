package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;
import users.HospitalAdmin;

public class MedicationOrder extends StockOrder
{
	private final HospitalDate orderDate;
	private final HospitalAdmin hospitalAdmin;
	private final MedicationType medicationType;
	
	public MedicationOrder(HospitalDate orderDate, HospitalAdmin hospitalAdmin, MedicationType medicationType){
		this.orderDate = orderDate;
		this.hospitalAdmin = hospitalAdmin;
		this.medicationType = medicationType;
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
	public Medication getStockItem() {
		return new Medication(medicationType.toString(), false, new HospitalDate(orderDate.getTimeSinceStart() + this.TIME_FOR_EXPIRATION), medicationType)
		{};
	}
}