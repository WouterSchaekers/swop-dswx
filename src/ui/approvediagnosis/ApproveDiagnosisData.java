package ui.approvediagnosis;

import java.util.Collection;
import controllers.ApproveDiagnosisController;
import controllers.interfaces.DiagnoseIN;

public class ApproveDiagnosisData
{

	private ApproveDiagnosisController approveDiagnosisController;
	private Collection<DiagnoseIN> d;
	private DiagnoseIN diagnose;

	public void add(ApproveDiagnosisController controller) {
		this.approveDiagnosisController = controller;
		
	}

	public ApproveDiagnosisController getController() {
	return approveDiagnosisController;
}

	public void setUnapprovedDiagnoses(Collection<DiagnoseIN> d) {
		this.d=d;
		
	}
	public Collection<DiagnoseIN> getAllTheDiagnosis()
	{
		return d;
	}

	public void setCreatedDiagnose(DiagnoseIN diagnose) {
		this.diagnose=diagnose;
		
	}

	public DiagnoseIN getDiagnose() {
		return diagnose;
	}

}
