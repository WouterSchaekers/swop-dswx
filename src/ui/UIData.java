package ui;

import controllers.LoginController;
import controllers.interfaces.HospitalIN;
import system.Hospital;

public class UIData
{

	private HospitalIN hospital_;
	private LoginController loginc;
	public UIData(Hospital hospital) {
		this.hospital_=hospital;
	}
	public LoginController getLoginController()
	{
		return loginc;
	}
	public HospitalIN hospital()
	{
		return hospital_;
	}
	public void setLoginController(LoginController loginc) {
		this.loginc=loginc;
		
	}
}
