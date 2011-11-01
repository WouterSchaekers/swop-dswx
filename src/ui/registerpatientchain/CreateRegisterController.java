package ui.registerpatientchain;

import controllers.RegisterPatientController;
import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.usecase;

/**
 * This class 
 *
 */
public class CreateRegisterController extends usecase
{

	public CreateRegisterController(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		RegisterPatientController reg ;
		try{
			reg = new RegisterPatientController(data.getLoginController(), data.getDataPasser());
		}catch(IllegalArgumentException illegal)
		{
			System.out.println(illegal.getMessage());
			return new SelectUsecase(data);
		}catch(Exception e)
		{
			System.out.println("Illegal argument");
			return new SelectUsecase(data);
		}
		return new displayAllPatients(data,reg);
	}

}
