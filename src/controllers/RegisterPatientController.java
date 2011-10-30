package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import users.Nurse;

public class RegisterPatientController
{

	DataPasser dataPasser;
	public RegisterPatientController(LoginController loginController,
			DataPasser dataPasser) {
		if(!(loginController.getUser() instanceof Nurse))
		{
			throw new IllegalArgumentException();
		}
	}
	public Collection<PatientFileDTO> getAllPatients()
	{
		Collection<PatientFileDTO> RV = new ArrayList<PatientFileDTO>();
		for(PatientFile file : 	dataPasser.getPatientFileManager().getAllPatientFiles())
			RV.add(new PatientFileDTO(file));
		return RV;
	}

}
