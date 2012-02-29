package ui.registerpatient;

import java.util.HashMap;
import java.util.Map;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.RegisterPatientController;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidLoginControllerException;

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
		try {
			for (PatientFileIN patient : rpc.getAllPatients(data
					.getLoginController())) {
				namePatientMap.put(patient.getName(), patient);
				System.out.println(patient.getName());
			}
		} catch (InvalidLoginControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new EnterPatientName(data, namePatientMap, rpc);
	}

}
