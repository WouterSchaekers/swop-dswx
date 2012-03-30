package controllers.interfaces;

import java.util.Collection;

public interface DiagnoseIN
{

	public boolean isApproved();

	public DoctorIN needsSecOpFrom();

	public Collection<TaskIN> getTreatments();

	public DoctorIN getAttending();

	public String getDiagnose();
	
	public boolean canBeReplacedWith(DiagnoseIN replacement);
}
