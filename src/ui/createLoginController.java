package ui;

import controllers.LoginController;

public class createLoginController extends usecase
{

	public createLoginController(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
			data.logingc=new LoginController(data.data);
		return new displayAllNames(data);
	}

}
