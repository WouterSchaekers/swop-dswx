package ui.registerpatient;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.RegisterPatientController;

/**
 * This class
 * 
 */
public class CreateRegisterController extends Usecase
{

	public CreateRegisterController(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		RegisterPatientController reg;
		try {
			reg = new RegisterPatientController(data.getLoginController(),
					data.getDataPasser());
		} catch (IllegalArgumentException illegal) {
			System.out.println(illegal.getMessage());
			return new SelectUsecase(data);
		} catch (Exception e) {
			System.out.println("Illegal argument");
			return new SelectUsecase(data);
		}
		return new DisplayAllPatients(data, reg);
	}

}
