package ui.registerpatientchain;

import java.util.Map;
import ui.UserinterfaceData;
import ui.Usecase;
import controllers.RegisterPatientController;
import controllers.interfaces.PatientFileIN;

public class RegisterCheckin extends Usecase
{
	RegisterPatientController rpc;
	String name;
	Map<String, PatientFileIN> nameMap;

	public RegisterCheckin(UserinterfaceData data,
			RegisterPatientController rpc,
			Map<String, PatientFileIN> namePatientFileMap, String name) {
		super(data);
		this.name = name;
		this.nameMap = namePatientFileMap;
		this.rpc = rpc;
	}

	@Override
	public Usecase Execute() {
		rpc.registerPatient(nameMap.get(name));
		System.out.println(name + " has been checked in");
		return new SelectDoctor(data, nameMap.get(name), rpc);
	}

}
