package controllers.interfaces;

import java.util.Collection;

public interface DiagnoseIN
{

	public boolean isApproved();

	public DoctorIN needsSecOpFrom();

	public Collection<TreatmentIN> getTreatments();

	public DoctorIN getAttending();

	public String getDiagnosis();
	
	public boolean canBeReplacedWith(DiagnoseIN replacement);
}
