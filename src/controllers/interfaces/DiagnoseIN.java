package controllers.interfaces;

import java.util.Collection;

@controllers.PUBLICAPI
public interface DiagnoseIN
{


@controllers.PUBLICAPI
	public DoctorIN getAttendingIN();

@controllers.PUBLICAPI
	public String getComplaintsIN();

@controllers.PUBLICAPI
	public String getDiagnoseIN();

@controllers.PUBLICAPI
	public Collection<TaskIN> getTreatmentsIN();

@controllers.PUBLICAPI	
	public boolean isApproved();

@controllers.PUBLICAPI	
	public DoctorIN needsSecOpFromIN();
}
