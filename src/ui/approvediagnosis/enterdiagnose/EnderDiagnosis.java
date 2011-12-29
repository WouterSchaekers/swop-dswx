package ui.approvediagnosis.enterdiagnose;

import controllers.EnterDiagnoseController;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import ui.approvediagnosis.ApproveDiagnosisData;
import ui.approvediagnosis.ApproveDiagnosisSuper;

public class EnderDiagnosis extends ApproveDiagnosisSuper
{
	public EnderDiagnosis(UserinterfaceData data, ApproveDiagnosisData chaindata) {
		super(data, chaindata);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usecase Execute() {
		// Create controller
		EnterDiagnoseController c;
		try {
			c = new EnterDiagnoseController(data.getLoginController(),
					data.getPatientFileOpenController());
		} catch (InvalidLoginControllerException e) {
			System.out.println("Invalid login aborting");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileOpenController e) {
			System.out.println("No patient file opened fatal error ");
			return new SelectUsecase(data);
		}
		this.chaindata.setController(c);

		return new PresentEnterDiagInputForm(data, chaindata);
	}

}