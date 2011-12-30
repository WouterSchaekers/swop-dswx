package ui.entermedicaltestresult;

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
		chaindata.getMedtestcontroller().allMedicalTests(data.getLoginController())
		return null;
	}

}
