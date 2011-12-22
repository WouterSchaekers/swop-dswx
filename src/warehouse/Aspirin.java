package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;

public class Aspirin extends Medication implements WarehouseItem
{
	public Aspirin(boolean sensitive, HospitalDate expirationDate) {
		super("Aspirin", sensitive, expirationDate);
	}

	@Override
	public boolean hasPassedDate(HospitalDate date) {
		// TODO Auto-generated method stub
		return false;
	}
}