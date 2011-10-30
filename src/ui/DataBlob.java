package ui;

import patient.PatientFileManager;
import users.UserManager;
import controllers.DataPasser;
import controllers.LoginController;
import controllers.RegisterPatientController;

public class DataBlob
{
	LoginController logingc;
	RegisterPatientController regpatctrl;
	DataPasser data;
	
	public DataBlob(DataPasser data) {
		this.data=data;
	}

}
