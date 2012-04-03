package controllers.interfaces;

import java.util.Collection;

public interface DiagnoseIN
{

	
	public DoctorIN getAttendingIN();

	public String getComplaintsIN();

	public String getDiagnoseIN();

	public Collection<TaskIN> getTreatmentsIN();
	
	public boolean isApprovedIN();
	
	public DoctorIN needsSecOpFromIN();
}
