package ui.registerpatientchain;

import java.util.Map;
import ui.UserinterfaceData;
import ui.usecase;
import controllers.PatientFileDTO;
import controllers.RegisterPatientController;

public class validatePatientName extends usecase
{
	String name;
	RegisterPatientController rpc;
	Map<String,PatientFileDTO>	 namePatientFileMap;
	public validatePatientName(UserinterfaceData data, RegisterPatientController rpc,
			Map<String, PatientFileDTO> namePatientMap, String in) {
		super(data);
		this.rpc= rpc;
		this.name=in;
		this.namePatientFileMap=namePatientMap;
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		if(namePatientFileMap.containsKey(name))
			return new RegisterCheckin(data,rpc,namePatientFileMap,name);
		// TODO Auto-generated method stub
		return new displayAllPatients(data, rpc);
	}

}
