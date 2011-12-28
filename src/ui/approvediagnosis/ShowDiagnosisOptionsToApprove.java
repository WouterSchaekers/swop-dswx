package ui.approvediagnosis;

import controllers.ApproveDiagnosisController;
import ui.Usecase;
import ui.UserinterfaceData;

public class ShowDiagnosisOptionsToApprove extends ApproveDiagnosisSuper
{

	protected ShowDiagnosisOptionsToApprove(UserinterfaceData data, ApproveDiagnosisData chaindata) {
		super(data,chaindata);
	}

	@Override
	public Usecase Execute() {
		ApproveDiagnosisController c =chaindata.getController();
		c.getAllPatienFiles(data.getDataPasser());
		
		// TODO Auto-generated method stub
		return null;
	}

}
