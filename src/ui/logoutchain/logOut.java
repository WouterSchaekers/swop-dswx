package ui.logoutchain;

import ui.DataBlob;
import ui.SelectUsecase;
import ui.usecase;

public class logOut extends usecase
{

	public logOut(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		if (data.getLoginController() != null)
			System.out.println(data.getLoginController().getUserDTO().getName()
					+ "succesfully logged out.");
		this.data.setLoginc(null);
		return new SelectUsecase(data);
	}

}
