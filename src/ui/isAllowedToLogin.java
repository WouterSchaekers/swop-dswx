package ui;

public class isAllowedToLogin extends usecase
{

	public isAllowedToLogin(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		if(data.logingc==null||!data.logingc.loggedIn())
			return new createLoginController(data);
		return new SelectUsecase(data);
	}

}
