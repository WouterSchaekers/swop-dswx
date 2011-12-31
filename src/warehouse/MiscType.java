package warehouse;

import scheduler.HospitalDate;

public class MiscType implements MedicationType
{
	public MiscType() {
	}

	@Override
	public boolean equals(MedicationType medicationType) {
		return medicationType instanceof MiscType;
	}

	@Override
	public Misc create(HospitalDate expirationDate) {
		return new Misc(false, expirationDate);
	}
}