package ui;

import java.util.Map;
import controllers.USERDTO;

public class validateLogin extends usecase
{
	Map<String, USERDTO> map;
	String name;
	public validateLogin(DataBlob data,Map<String,USERDTO> map,String name) {
		super(data);
		this.map=map;
		this.name=name;
	}

	@Override
	public usecase Execute() {
		if(map.containsKey(name))
			return new loginToSystem(data,map.get(name));
		return new displayAllNames(data);
	}

}
