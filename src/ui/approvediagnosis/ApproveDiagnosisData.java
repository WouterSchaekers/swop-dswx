package ui.approvediagnosis;

import controllers.ApproveDiagnosisController;

public class ApproveDiagnosisData
{

	private ApproveDiagnosisController approveDiagnosisController;

	public void add(ApproveDiagnosisController controller) {
		this.approveDiagnosisController = controller;
		
	}

	public ApproveDiagnosisController getController() {
	return approveDiagnosisController;
}

}
