package ui.approvediagnosis;

import ui.Usecase;
import ui.UserinterfaceData;
import controllers.ApproveDiagnosisController;
import exceptions.InvalidLoginControllerException;

public class ApproveDiagnosis extends ApproveDiagnosisSuper
{

	public ApproveDiagnosis(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		//create controller!
		ApproveDiagnosisController controller = null;
		try {
			controller = new ApproveDiagnosisController(data.getLoginController());
		} catch (InvalidLoginControllerException e) {
			System.out.println();
		}
		this.chaindata.add(controller);
		return new ShowDiagnosisOptionsToApprove(data,chaindata);
	}

}
