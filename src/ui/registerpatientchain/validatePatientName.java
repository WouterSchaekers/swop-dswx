package ui.registerpatientchain;

import java.util.Map;
import ui.UserinterfaceData;
import ui.usecase;
import controllers.DTOPatientFile;
import controllers.RegisterPatientController;

public class validatePatientName extends usecase
{
	String name;
	RegisterPatientController rpc;
	Map<String,DTOPatientFile>	 namePatientFileMap;
	public validatePatientName(UserinterfaceData data, RegisterPatientController rpc,
			Map<String, DTOPatientFile> namePatientMap, String in) {
		super(data);
		this.rpc= rpc;
		this.name=in;
		this.namePatientFileMap=namePatientMap;
	}

	@Override
	public usecase Execute() {
		if(namePatientFileMap.containsKey(name))
			return new RegisterCheckin(data,rpc,namePatientFileMap,name);
		return new displayAllPatients(data, rpc);
	}

}
