package ui.registerpatientchain;

import java.util.Map;
import ui.DataBlob;
import ui.usecase;
import controllers.PatientFileDTO;
import controllers.RegisterPatientController;

public class RegisterCheckin extends usecase
{
	RegisterPatientController rpc;
	String name;
	Map<String,PatientFileDTO> nameMap;
	public RegisterCheckin(DataBlob data, RegisterPatientController rpc,
			Map<String, PatientFileDTO> namePatientFileMap, String name) {
		super(data);
		this.name=name;
		this.nameMap=namePatientFileMap;
		this.rpc=rpc;
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		rpc.registerPatient(nameMap.get(name));
		System.out.println(name+" has been checked in");
		// TODO Auto-generated method stub
		return new SelectDoctor(data,nameMap.get(name),rpc);
	}

}
