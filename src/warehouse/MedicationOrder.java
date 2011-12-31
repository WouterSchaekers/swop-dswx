package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;
import users.HospitalAdmin;
import users.WarehouseAdmin;

public class MedicationOrder extends StockOrder
{
	private final HospitalDate orderDate;
	private final MedicationType medicationType;
	private final WarehouseAdmin warehouseAdmin;
	
	public MedicationOrder(HospitalDate orderDate, WarehouseAdmin warehouseAdmin, MedicationType medicationType){
		this.orderDate = orderDate;
		this.warehouseAdmin = warehouseAdmin;
		this.medicationType = medicationType;
	}
	
	@Override
	public HospitalDate getOrderDate(){
		return new HospitalDate(this.orderDate);
	}
	
	@Override
	public WarehouseAdmin getWarehouseAdmin(){
		return this.warehouseAdmin;
	}

	@Override
	public Medication getStockItem() {
		return new Medication(medicationType.toString(), false, new HospitalDate(orderDate.getTimeSinceStart() + this.TIME_FOR_EXPIRATION), medicationType)
		{};
	}
}