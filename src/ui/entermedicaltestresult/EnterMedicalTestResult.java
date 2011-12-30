package ui.entermedicaltestresult;

import controllers.EnterMedicaltestResultController;
import exceptions.InvalidLoginControllerException;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class EnterMedicalTestResult extends EnterMedicalTestResultSuperClass
{

	public EnterMedicalTestResult(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {	
		//create controller
		EnterMedicaltestResultController enterMedTestContr;
		try {
			enterMedTestContr=new EnterMedicaltestResultController(data.getLoginController(), data.getDataPasser());
		} catch (InvalidLoginControllerException e) {
			System.out.println("not allowed to do this ");
			return new SelectUsecase(data);
		}
		chaindata.setMedtestcontroller(enterMedTestContr);
		return new ShowUnfinishedMedicalTests(data,chaindata);
	}

}
