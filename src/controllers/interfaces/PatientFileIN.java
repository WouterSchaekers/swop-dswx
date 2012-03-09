package controllers.interfaces;

import java.util.Collection;
import users.User;

public interface PatientFileIN
{

	String getName();

	boolean isDischarged();

	public Collection<DiagnoseIN> getAlldiagnosis();

	public Collection<TreatmentIN> getAllTreatments();

	public Collection<MedicalTestIN> getallMedicalTests();

	

}
