package ui.registerpatientchain;

import java.util.HashMap;
import java.util.Map;
import ui.UserinterfaceData;
import ui.usecase;
import controllers.PatientFileDTO;
import controllers.RegisterPatientController;

public class displayAllPatients extends usecase
{
	Map<String,PatientFileDTO> namePatientMap;
	RegisterPatientController rpc;
	public displayAllPatients(UserinterfaceData data,RegisterPatientController registerpatientcontroller) {
		super(data);
		this.rpc=registerpatientcontroller;
		namePatientMap = new HashMap<String, PatientFileDTO>();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public usecase Execute() {
		System.out.println("List of all patients:");
		for(PatientFileDTO patient:rpc.getAllPatients()){
			namePatientMap.put(patient.getName(), patient);
			System.out.println(patient.getName());
		}
		return new EnterPatientName(data,namePatientMap,rpc);
	}

}
