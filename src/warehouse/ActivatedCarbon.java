package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;

public class ActivatedCarbon extends Medication
{
	public ActivatedCarbon(boolean sensitive, HospitalDate expirationDate) {
		super("Activated Carbon", sensitive, expirationDate);
	}

	@Override
	public boolean hasPassedDate(HospitalDate date) {
		// TODO Auto-generated method stub
		return false;
	}

}