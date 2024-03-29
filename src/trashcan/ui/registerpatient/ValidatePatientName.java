package ui.registerpatient;

import java.util.Map;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.RegisterPatientController;
import controllers.interfaces.PatientFileIN;

public class ValidatePatientName extends Usecase
{
	String name;
	RegisterPatientController rpc;
	Map<String, PatientFileIN> namePatientFileMap;

	public ValidatePatientName(UserinterfaceData data,
			RegisterPatientController rpc,
			Map<String, PatientFileIN> namePatientMap, String in) {
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
