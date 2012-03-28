package ui.registerpatient;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.RegisterPatientController;

public class CreateRegisterController extends Usecase
{

	public CreateRegisterController(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		RegisterPatientController reg;
		try {
			reg = new RegisterPatientController(data.getLoginController());

			return new DisplayAllPatients(data, reg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
