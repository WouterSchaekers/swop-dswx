package ui.loginchain;

import java.util.HashMap;
import java.util.Map;
import ui.DataBlob;
import ui.usecase;
import controllers.UserDTO;

public class displayAllNames extends usecase
{

	public displayAllNames(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		System.out.println("All users in the system:");
		Map<String, UserDTO> nameUserMap=new HashMap<String, UserDTO>();
		for(UserDTO u:data.getLoginController().getAllUsers2())
		{
			System.out.println(u.getName());
			nameUserMap.put(u.getName(), u);
		}
		return new login(data,nameUserMap);
	}

}
