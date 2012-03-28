package ui.approvediagnosis;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.ApproveDiagnoseController;
import exceptions.InvalidLoginControllerException;

public class ApproveDiagnosis extends ApproveDiagnosisSuper
{

	public ApproveDiagnosis(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		// create controller!
		ApproveDiagnoseController controller = null;
		try {
			controller = new ApproveDiagnoseController(data.getLoginController(), data.getPatientFileOpenController());
		} catch (InvalidLoginControllerException e) {
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
		this.chaindata.add(controller);
		return new ShowDiagnosisOptionsToApprove(data, chaindata);
	}

}
