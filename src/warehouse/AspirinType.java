package warehouse;

import scheduler.HospitalDate;

public class AspirinType implements MedicationType
{
	public AspirinType() {
	}

	@Override
	public boolean equals(MedicationType medicationType) {
		return medicationType instanceof AspirinType;
	}
	
	@Override
	public String toString(){
		return "Aspirin";
	}

	@Override
	public Aspirin create(HospitalDate expirationDate) {
		return new Aspirin(false, expirationDate);
	}
}