package ui.entermedicaltestresult;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.EnterMedicaltestResultController;

public class EnterMedicalTestResult extends EnterMedicalTestResultSuperClass
{

	public EnterMedicalTestResult(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		// create controller
		try {
			EnterMedicaltestResultController enterMedTestContr = null;
			enterMedTestContr = new EnterMedicaltestResultController(
					data.getLoginController());

			chaindata.setMedtestcontroller(enterMedTestContr);
			return new ShowUnfinishedMedicalTests(data, chaindata);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
