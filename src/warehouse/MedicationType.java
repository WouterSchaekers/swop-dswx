package warehouse;

import scheduler.HospitalDate;
import treatment.Medication;

public interface MedicationType
{	
	public boolean equals(MedicationType medicationType);
	
	public Medication create(HospitalDate expirationDate);
}