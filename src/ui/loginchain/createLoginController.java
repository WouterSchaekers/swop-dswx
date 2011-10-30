package ui.loginchain;

import ui.DataBlob;
import ui.usecase;
import controllers.LoginController;

public class createLoginController extends usecase
{

	public createLoginController(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
			data.setLoginc(new LoginController(data.getDataPasser()));
		return new displayAllNames(data);
	}

}
