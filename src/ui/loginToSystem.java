package ui;

import controllers.USERDTO;

public class loginToSystem extends usecase
{
	USERDTO user;
	public loginToSystem(DataBlob data,USERDTO user) {
		super(data);
		this.user=user;
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		data.logingc.logIn(user);
		System.out.println(user.getName()+" was succesfully logged in");
		return new SelectUsecase(data);
	}

}
