package ui.enterdiagnosis;

import controllers.EnterDiagnoseController;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class EnderDiagnosis extends EnterDiagnoseSuperClass
{
	public EnderDiagnosis(UserinterfaceData data) {
		super(data);
	}


	@Override
	public Usecase Execute() {
		//Create controller
		EnterDiagnoseController c;
		try {
			c = new EnterDiagnoseController(data.getLoginController(),data.getPatientFileOpenController());
		} catch (InvalidLoginControllerException e) {
			System.out.println("Invalid login aborting");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileOpenController e) {
			System.out.println("No patient file opened fatal error ");
			return new SelectUsecase(data);
		}
		this.chaindata.setController(c);
		//Controller created
		return new PresentEnterDiagInputForm(data,chaindata);
	}


}
