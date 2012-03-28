package controllers.interfaces;

import java.util.Collection;

public interface PatientFileIN
{
	public boolean isDischarged();

	public Collection<DiagnoseIN> getAllDiagnosis();

	public Collection<TreatmentIN> getAllTreatments();

	public Collection<MedicalTestIN> getallMedicalTests();

	public String getName();

	public Collection<DiagnoseIN> getPendingDiagnosisFor(DoctorIN d);
}
