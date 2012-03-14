package ui.enterdiagnosis;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.EnterDiagnoseController;

public class EnterDiagnose extends EnterDiagnoseSuperClass
{
	public EnterDiagnose(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		// Create controller
		try {
			EnterDiagnoseController c = null;

			c = new EnterDiagnoseController(data.getLoginController(),
					data.getPatientFileOpenController());

			this.chaindata.setController(c);
			// Controller created
			return new PresentEnterDiagInputForm(data, chaindata);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
