package warehouse;

import scheduler.HospitalDate;

public class SleepingTabletsType implements MedicationType
{
	public SleepingTabletsType() {
	}

	@Override
	public boolean equals(MedicationType medicationType) {
		return medicationType instanceof SleepingTabletsType;
	}

	@Override
	public String toString() {
		return "SleepingTablets";
	}

	@Override
	public SleepingTablets create(HospitalDate expirationDate) {
		return new SleepingTablets(false, expirationDate);
	}
}