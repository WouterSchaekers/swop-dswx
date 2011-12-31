package controllers.interfaces;

import java.util.Collection;

public interface PatientFileIN
{

	String getName();

	boolean isDischarged();

	public Collection<DiagnoseIN> getAlldiagnosis();

	public Collection<TreatmentIN> getAllTreatments();

	public Collection<MedicalTestIN> getallMedicalTests();

}
