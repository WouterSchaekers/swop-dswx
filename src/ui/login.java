package ui;

import java.util.Map;
import controllers.USERDTO;

public class login extends usecase
{
	Map<String, USERDTO> map;
	public login(DataBlob data,Map<String,USERDTO> nameUserMap) {
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
