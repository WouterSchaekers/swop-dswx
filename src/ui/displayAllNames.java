package ui;

import java.util.HashMap;
import java.util.Map;
import controllers.USERDTO;

public class displayAllNames extends usecase
{

	public displayAllNames(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		System.out.println("All users in the system:");
		Map<String, USERDTO> nameUserMap=new HashMap<String, USERDTO>();
		for(USERDTO u:data.logingc.getAllUsers2())
		{
			System.out.println(u.getName());
			nameUserMap.put(u.getName(), u);
		}
		return new login(data,nameUserMap);
	}

}
