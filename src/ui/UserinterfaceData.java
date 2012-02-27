package ui;

import system.HospitalState;
import controllers.LoginController;
import controllers.PatientFileOpenController;

/**
 * This class keeps the information that the user interface needs.
 */
public class UserinterfaceData
{
	private LoginController loginc;
	private PatientFileOpenController regpatctrl;
	private HospitalState data;

	/**
	 * Default constructor.
	 * 
	 * @param data
	 *            The datapasser for this dataobject.
	 */
	public UserinterfaceData(HospitalState data) {
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
	 * This method will set the register patient file controller for this
	 * dataobject.
	 * 
	 * @param regpatctrl
	 *            The registerpatientfilecontroller.
	 */
	public void setRegpatctrl(PatientFileOpenController regpatctrl) {
		this.regpatctrl = regpatctrl;
	}

	/**
	 * This method will set the data passer for this dataobject.
	 * 
	 * @param data
	 *            The datapasser.
	 */
	public void setData(HospitalState data) {
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
	public HospitalState getDataPasser() {
		return data;
	}

	/**
	 * @return The RegisterPatientFileController of this dataobject.
	 */
	public PatientFileOpenController getPatientFileOpenController() {
		return regpatctrl;
	}

	public void reset() {
		this.loginc=null;
		this.regpatctrl=null;
	}

}
