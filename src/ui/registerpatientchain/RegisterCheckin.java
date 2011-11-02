package ui.registerpatientchain;

import java.util.Map;
import ui.UserinterfaceData;
import ui.usecase;
import controllers.DTOPatientFile;
import controllers.RegisterPatientController;

public class RegisterCheckin extends usecase
{
	RegisterPatientController rpc;
	String name;
	Map<String,DTOPatientFile> nameMap;
	public RegisterCheckin(UserinterfaceData data, RegisterPatientController rpc,
			Map<String, DTOPatientFile> namePatientFileMap, String name) {
		super(data);
		this.name=name;
		this.nameMap=namePatientFileMap;
		this.rpc=rpc;
	}

	@Override
	public usecase Execute() {
		rpc.registerPatient(nameMap.get(name));
		System.out.println(name+" has been checked in");
		return new SelectDoctor(data,nameMap.get(name),rpc);
	}

}
