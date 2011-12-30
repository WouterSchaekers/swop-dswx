package warehouse;

public class SleepingTabletsType implements MedicationType
{
	public SleepingTabletsType() {
	}

	@Override
	public boolean equals(MedicationType medicationType) {
		return medicationType instanceof SleepingTabletsType;
	}
	
	@Override
	public String toString(){
		return "SleepingTablets";
	}
}