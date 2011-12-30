package warehouse;

public class ActivatedCarbonType implements MedicationType
{
	public ActivatedCarbonType(){}
	
	@Override
	public boolean equals(MedicationType medicationType){
		return medicationType instanceof ActivatedCarbon;
	}
	
	@Override
	public String toString(){
		return "ActivatedCarbon";
	}
}