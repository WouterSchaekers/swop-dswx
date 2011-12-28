package ui.registerpatient;

import java.util.HashMap;
import java.util.Map;
import ui.UserinterfaceData;
import ui.Usecase;
import controllers.RegisterPatientController;
import controllers.interfaces.PatientFileIN;

public class DisplayAllPatients extends Usecase
{
	Map<String, PatientFileIN> namePatientMap;
	RegisterPatientController rpc;

	public DisplayAllPatients(UserinterfaceData data,
			RegisterPatientController registerpatientcontroller) {
		super(data);
		this.rpc = registerpatientcontroller;
		namePatientMap = new HashMap<String, PatientFileIN>();
	}

	@Override
	public Usecase Execute() {
		System.out.println("List of all patients:");
		for (PatientFileIN patient : rpc.getAllPatients()) {
			namePatientMap.put(patient.getName(), patient);
			System.out.println(patient.getName());
		}
		return new EnterPatientName(data, namePatientMap, rpc);
	}

}
