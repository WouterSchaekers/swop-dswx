package ui;

import patient.PatientFileManager;
import scheduler.Scheduler;
import users.UserManager;
import controllers.DataPasser;
import controllers.LoginController;
import controllers.RegisterPatientController;

public class DataBlob
{
	public void setLoginc(LoginController loginc) {
		this.loginc = loginc;
	}
	public void setRegpatctrl(RegisterPatientController regpatctrl) {
		this.regpatctrl = regpatctrl;
	}
	public void setData(DataPasser data) {
		this.data = data;
	}
	private  LoginController loginc;
	private RegisterPatientController regpatctrl;
	private DataPasser data;
	public LoginController getLoginController()
	{
		return loginc;
	}
	public DataPasser getDataPasser(){
		return data;
	}
	public RegisterPatientController getRegisterPatientController(){
		return regpatctrl;
	}
	public DataBlob(DataPasser data) {
		this.data=data;
	}

}
