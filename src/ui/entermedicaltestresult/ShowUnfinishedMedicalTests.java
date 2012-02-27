package ui.entermedicaltestresult;

import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;
import ui.Usecase;
import ui.UserinterfaceData;

public class ShowUnfinishedMedicalTests extends EnterMedicalTestResultSuperClass
{

	public ShowUnfinishedMedicalTests(UserinterfaceData data,
			EnterMedicalTestResultData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		try {
			chaindata.getMedtestcontroller().allMedicalTests(data.getLoginController(), data.getPatientFileOpenController());
		} catch (InvalidLoginControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPatientFileOpenController e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
