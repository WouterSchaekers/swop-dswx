package warehouse;

import scheduler.HospitalDate;

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
	
	public ActivatedCarbon create(HospitalDate expirationDate){
		return new ActivatedCarbon(false, expirationDate);
	}
}