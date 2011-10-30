package ui;

import controllers.RegisterPatientController;
import users.User.usertype;

public class CreateRegisterController extends usecase
{

	public CreateRegisterController(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		RegisterPatientController reg ;
		try{
			reg = new RegisterPatientController(data.logingc, data.data);
		}catch(IllegalArgumentException illegal)
		{
			System.out.println(data.logingc.getUserDTO().getName()+illegal.getMessage());
			return new SelectUsecase(data);
		}
		return new displayAllPatients(data,reg);
	}

}
