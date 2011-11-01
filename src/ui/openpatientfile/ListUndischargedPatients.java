package ui.openpatientfile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import controllers.DTOPatientFile;
import controllers.PatientFileOpenController;
import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.usecase;
import users.User.usertype;

public class ListUndischargedPatients extends usecase
{
	UserinterfaceData data;
	ConsutlPatientFileData patfiledata;
	public ListUndischargedPatients(UserinterfaceData data, ConsutlPatientFileData d) {
		super(data);
	}

	@Override
	public usecase Execute() {
		if(data.getLoginController().getUserDTO().type()!=usertype.Doctor)
		{
			System.out.println(data.getLoginController().getUserDTO().getName()+" is not a Doctor");
			return new SelectUsecase(data);
		
		}
		patfiledata.setPatientFileOpenController(new PatientFileOpenController(data.getDataPasser(), data.getLoginController()));
		Map<String,DTOPatientFile> map = new HashMap<String, DTOPatientFile>();
		Collection<DTOPatientFile> patientfiles =patfiledata.getPatientfileOpenController().getAllPatientFiles(data.getLoginController());
		for(DTOPatientFile file:patientfiles)
		{
			map.put(file.getName(), file);
			System.out.println(file.getName());
		}
		//String name = input.nextLine();
		//TODO fix 
		return new SelectUsecase(data);
	}

}
