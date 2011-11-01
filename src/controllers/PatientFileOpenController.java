package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;

public class PatientFileOpenController
{
	DataPasser data;
	public PatientFileOpenController(DataPasser data,LoginController loginController) {
		this.data = data;
	}
	public Collection<DTOPatientFile> getAllPatientFiles(){
		ArrayList<DTOPatientFile> RV = new ArrayList<DTOPatientFile>();
		for(PatientFile file :data.getPatientFileManager().getAllPatientFiles())
			RV.add(new DTOPatientFile(file));
		return RV;
	}
	public void openPatientFile(DTOPatientFile pfdto) {
		// TODO Auto-generated method stub
		
	}

}
