package controllers.interfaces;

import java.util.Collection;

public interface PatientFileIN
{
	public boolean isDischarged();

	public Collection<DiagnoseIN> getAllDiagnosis();

	public Collection<TaskIN> getAllMedicalTests();

	
	public Collection<DiagnoseIN> getPendingDiagnosisFor(DoctorIN d);
	public PatientIN getPatient();
}
