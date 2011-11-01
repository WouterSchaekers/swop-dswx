package ui;

import controllers.DataPasser;
import controllers.LoginController;
import controllers.RegisterPatientController;

/**
 * This class keeps the information that the user interface needs.
 */
public class UserinterfaceData
{
	private LoginController loginc;
	private RegisterPatientController regpatctrl;
	private DataPasser data;

	/**
	 * Default constructor. 
	 * @param data
	 * The datapasser for this dataobject.
	 */
	public UserinterfaceData(DataPasser data) {
		this.data = data;
	}
	
	/**
	 * This method will set the logincontroller of this dataobject.
	 * 
	 * @param loginc
	 *            The LoginController.
	 */
	public void setLoginc(LoginController loginc) {
		this.loginc = loginc;
	}

	/**
	 * This method will set the register patient file controller for this dataobject.
	 * @param regpatctrl
	 * The registerpatientfilecontroller.
	 */
	public void setRegpatctrl(RegisterPatientController regpatctrl) {
		this.regpatctrl = regpatctrl;
	}

	/**
	 * This method will set the data passer for this dataobject.
	 * @param data
	 * The datapasser.
	 */
	public void setData(DataPasser data) {
		this.data = data;
		
	}

	/**
	 * @return The logincontroller of this dataobject.
	 */
	public LoginController getLoginController() {
		return loginc;
	}

	/**
	 * @return The datapasser of this dataobject.
	 */
	public DataPasser getDataPasser() {
		return data;
	}

	/**
	 * @return The RegisterPatientFileController of this dataobject.
	 */
	public RegisterPatientController getRegisterPatientController() {
		return regpatctrl;
	}

	
}
