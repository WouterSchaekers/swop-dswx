package ui.addhospitalstaff;

import ui.Usecase;
import ui.UserinterfaceData;

public class CreateUser extends Usecase
{

	public CreateUser(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		return new selectUserType(data);
	}

}
