package ui.approvediagnosis;

import ui.Usecase;
import ui.UserinterfaceData;
import controllers.ApproveDiagnoseController;
import exceptions.InvalidHospitalException;
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
			controller = new ApproveDiagnoseController(data.getDataPasser(),
					data.getLoginController());
		} catch (InvalidLoginControllerException e) {
			System.out.println();
		} catch (InvalidHospitalException e) {

		}
		this.chaindata.add(controller);
		return new ShowDiagnosisOptionsToApprove(data, chaindata);
	}

}
