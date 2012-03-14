package ui.approvediagnosis.enterdiagnose;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import ui.approvediagnosis.ApproveDiagnosisData;
import ui.approvediagnosis.ApproveDiagnosisSuper;
import controllers.EnterDiagnoseController;

public class EnterDiagnose extends ApproveDiagnosisSuper
{
	public EnterDiagnose(UserinterfaceData data, ApproveDiagnosisData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		// Create controller
		try {
			EnterDiagnoseController c = null;

			c = new EnterDiagnoseController(data.getLoginController(),
					data.getPatientFileOpenController());

			this.chaindata.setController(c);

			return new PresentEnterDiagInputForm(data, chaindata);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
