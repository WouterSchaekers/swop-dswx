package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;

public class Aspirin extends Medication implements WarehouseItem
{
	public Aspirin(boolean sensitive, HospitalDate expirationDate) {
		super("Aspirin", sensitive, expirationDate, new AspirinType());
	}

	@Override
	public boolean hasPassedDate(HospitalDate date) {
		return this.getExpiryDate().after(date);
	}
}