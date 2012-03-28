package ui.entermedicaltestresult;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class ShowUnfinishedMedicalTests extends
		EnterMedicalTestResultSuperClass
{

	public ShowUnfinishedMedicalTests(UserinterfaceData data,
			EnterMedicalTestResultData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		try {
			chaindata.getMedtestcontroller().getAllMedicalTests();
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}
}
