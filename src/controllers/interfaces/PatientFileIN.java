package controllers.interfaces;

import java.util.Collection;

@controllers.PUBLICAPI
public interface PatientFileIN
{

@controllers.PUBLICAPI
	public boolean isDischarged();

@controllers.PUBLICAPI
	public Collection<DiagnoseIN> getAllDiagnosisIN();

@controllers.PUBLICAPI
	public Collection<TaskIN> getAllMedicalTestsIN();

@controllers.PUBLICAPI
	
	public Collection<DiagnoseIN> getPendingDiagnosisForIN(DoctorIN d);

@controllers.PUBLICAPI
public PatientIN getPatientIN();
}
