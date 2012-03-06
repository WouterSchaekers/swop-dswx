package ui.enterdiagnosis;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.EnterDiagnoseController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnderDiagnosis extends EnterDiagnoseSuperClass
{
	public EnderDiagnosis(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		// Create controller
		EnterDiagnoseController c = null;
		try {
			c = new EnterDiagnoseController(data.getDataPasser(),
					data.getLoginController(),
					data.getPatientFileOpenController());
		} catch (InvalidLoginControllerException e) {
			System.out.println("Invalid login aborting");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileOpenController e) {
			System.out.println("No patient file opened fatal error ");
			return new SelectUsecase(data);
		} catch (InvalidHospitalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.chaindata.setController(c);
		// Controller created
		return new PresentEnterDiagInputForm(data, chaindata);
	}

}
