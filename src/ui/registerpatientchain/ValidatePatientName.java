package ui.registerpatientchain;

import java.util.Map;
import ui.UserinterfaceData;
import ui.Usecase;
import controllers.DTOPatientFile;
import controllers.RegisterPatientController;

public class ValidatePatientName extends Usecase
{
	String name;
	RegisterPatientController rpc;
	Map<String, DTOPatientFile> namePatientFileMap;

	public ValidatePatientName(UserinterfaceData data,
			RegisterPatientController rpc,
			Map<String, DTOPatientFile> namePatientMap, String in) {
		super(data);
		this.rpc = rpc;
		this.name = in;
		this.namePatientFileMap = namePatientMap;
	}

	@Override
	public Usecase Execute() {
		if (namePatientFileMap.containsKey(name))
			return new RegisterCheckin(data, rpc, namePatientFileMap, name);
		return new DisplayAllPatients(data, rpc);
	}

}
