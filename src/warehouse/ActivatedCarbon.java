package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;

public class ActivatedCarbon extends Medication
{
	public ActivatedCarbon(boolean sensitive, HospitalDate expirationDate) {
		super("Activated Carbon", sensitive, expirationDate,
				new ActivatedCarbonType());
	}

	@Override
	public boolean hasPassedDate(HospitalDate date) {
		return this.getExpiryDate().after(date);
	}
}