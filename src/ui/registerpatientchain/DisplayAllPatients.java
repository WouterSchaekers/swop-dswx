package ui.registerpatientchain;

import java.util.HashMap;
import java.util.Map;
import ui.UserinterfaceData;
import ui.Usecase;
import controllers.DTOPatientFile;
import controllers.RegisterPatientController;

public class DisplayAllPatients extends Usecase
{
	Map<String,DTOPatientFile> namePatientMap;
	RegisterPatientController rpc;
	public DisplayAllPatients(UserinterfaceData data,RegisterPatientController registerpatientcontroller) {
		super(data);
		this.rpc=registerpatientcontroller;
		namePatientMap = new HashMap<String, DTOPatientFile>();
	}
	
	@Override
	public Usecase Execute() {
		System.out.println("List of all patients:");
		for(DTOPatientFile patient:rpc.getAllPatients()){
			namePatientMap.put(patient.getName(), patient);
			System.out.println(patient.getName());
		}
		return new EnterPatientName(data,namePatientMap,rpc);
	}

}
