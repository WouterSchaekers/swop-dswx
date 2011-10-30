package ui.loginchain;

import ui.DataBlob;
import ui.SelectUsecase;
import ui.usecase;

public class isAllowedToLogin extends usecase
{

	public isAllowedToLogin(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		if(data.getLoginController()==null||!data.getLoginController().loggedIn())
			return new createLoginController(data);
		return new SelectUsecase(data);
	}

}
