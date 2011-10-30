package ui.loginchain;

import java.util.Map;
import ui.DataBlob;
import ui.usecase;
import controllers.UserDTO;

public class login extends usecase
{
	Map<String, UserDTO> map;
	public login(DataBlob data,Map<String,UserDTO> nameUserMap) {
		super(data);
		this.map=nameUserMap;
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		System.out.println("Enter User name:");
		String name = input.nextLine();
		return new validateLogin(data,map,name);
	}

}
