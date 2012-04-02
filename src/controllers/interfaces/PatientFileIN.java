package controllers.interfaces;

import java.util.Collection;

public interface PatientFileIN
{
	public boolean isDischarged();

	public Collection<DiagnoseIN> getAllDiagnosis();

	public Collection<TaskIN> getallMedicalTests();

	public String getPatientName();

	public Collection<DiagnoseIN> getPendingDiagnosisFor(DoctorIN d);
}
