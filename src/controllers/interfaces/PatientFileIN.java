package controllers.interfaces;

import java.util.Collection;

public interface PatientFileIN
{
	public boolean isDischarged();

	public Collection<DiagnoseIN> getAllDiagnosisIN();

	public Collection<TaskIN> getAllMedicalTestsIN();

	
	public Collection<DiagnoseIN> getPendingDiagnosisForIN(DoctorIN d);
	public PatientIN getPatientIN();
}
