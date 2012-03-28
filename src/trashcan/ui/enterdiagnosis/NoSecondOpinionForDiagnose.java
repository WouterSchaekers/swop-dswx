package ui.enterdiagnosis;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class NoSecondOpinionForDiagnose extends EnterDiagnoseSuperClass
{

	NoSecondOpinionForDiagnose(UserinterfaceData data,
			EnterDiagnoseData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		try {
			chaindata.getController().enterDiagnose(chaindata.getDiag());
			System.out.println("Diagnose was succesfully entered");
			return new SelectUsecase(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
