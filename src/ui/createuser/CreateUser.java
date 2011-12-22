package ui.createuser;

import ui.Usecase;
import ui.UserinterfaceData;
import users.UserManager;

public class CreateUser extends Usecase
{

	public CreateUser(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usecase Execute() {
		return new selectUserType(data);
	}

}
