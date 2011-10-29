package controllers;

/**
 * This is the supercontroller for the medicaltestcontroller and 
 * also extends the normal supercontroller.
 *
 */
public class super0Controller extends superController
{
	private ConsultPatientFileController cpf; // the cpf for this supercontroller

	/**
	 * Use of empty constructor is <B>NOT</B> allowed!
	 */
	private super0Controller() {super(null);}
	
	/**
	 * Default constructor.
	 * @param lc
	 * @param cpf
	 * @throws IllegalArgumentException
	 */
	public super0Controller(LoginController lc, ConsultPatientFileController cpf)
			throws IllegalArgumentException {
		super(lc);
		this.cpf = cpf;
	}

	protected ConsultPatientFileController getConsultPatientFileController() {
		return cpf;
	}

}
