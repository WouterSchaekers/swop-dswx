package ui.approvediagnosis;

import controllers.ApproveDiagnosisController;
import exceptions.InvalidLoginControllerException;
import ui.Usecase;
import ui.UserinterfaceData;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.chaindata.add(controller);
		return new ShowDiagnosisOptionsToApprove(data,chaindata);
	}

}
