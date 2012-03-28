package ui.approvediagnosis.enterdiagnose;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import ui.approvediagnosis.ApproveDiagnosisData;
import ui.approvediagnosis.ApproveDiagnosisSuper;
import ui.approvediagnosis.ShowResultingTreatment;
import controllers.interfaces.DiagnoseIN;

public class RegisterDiagnoseInput extends ApproveDiagnosisSuper
{

	public RegisterDiagnoseInput(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		try {
			DiagnoseIN diagnose = null;
			diagnose = this.chaindata.getEnterDiagnoseController()
					.enterDiagnose(chaindata.getDiagnoseString());
			chaindata.setDiagnose(diagnose);
			return new ShowResultingTreatment(data, chaindata);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
