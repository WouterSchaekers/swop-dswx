package ui.entermedicaltestresult;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.EnterMedicaltestResultController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterMedicalTestResult extends EnterMedicalTestResultSuperClass
{

	public EnterMedicalTestResult(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		// create controller
		EnterMedicaltestResultController enterMedTestContr = null;
		try {
			enterMedTestContr = new EnterMedicaltestResultController(
					data.getDataPasser(), data.getLoginController(),
					data.getPatientFileOpenController());
		} catch (InvalidLoginControllerException e) {
			System.out.println("not allowed to do this ");
			return new SelectUsecase(data);
		} catch (InvalidHospitalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPatientFileOpenController e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chaindata.setMedtestcontroller(enterMedTestContr);
		return new ShowUnfinishedMedicalTests(data, chaindata);
	}

}
