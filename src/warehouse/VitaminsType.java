package warehouse;

public class VitaminsType implements MedicationType
{
	public VitaminsType() {
	}

	@Override
	public boolean equals(MedicationType medicationType) {
		return medicationType instanceof VitaminsType;
	}
	
	@Override
	public String toString(){
		return "Vitamins";
	}
}