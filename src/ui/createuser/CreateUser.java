package ui.createuser;

import ui.Usecase;
import ui.UserinterfaceData;

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
