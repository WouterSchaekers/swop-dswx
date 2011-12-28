package ui.approvediagnosis;

import controllers.ApproveDiagnosisController;
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
		ApproveDiagnosisController controller=new ApproveDiagnosisController(data.getDataPasser());
		this.chaindata.add(controller);
		return new ShowDiagnosisOptionsToApprove(data,chaindata);
	}

}
