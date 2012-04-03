package controllers.interfaces;

import java.util.Collection;

public interface DiagnoseIN
{

	
	public DoctorIN getAttending();

	public String getComplaints();

	public String getDiagnose();

	public Collection<TaskIN> getTreatments();
	
	public boolean isApproved();
	
	public DoctorIN needsSecOpFrom();
}
