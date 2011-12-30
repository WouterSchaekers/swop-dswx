package warehouse;

public class MiscType implements MedicationType
{
	public MiscType() {
	}

	@Override
	public boolean equals(MedicationType medicationType) {
		return medicationType instanceof MiscType;
	}
}