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
	 * The logincontroller for this super0controller.
	 * @param cpf
	 * The cpf for this super0controller
	 * @throws IllegalArgumentException
	 * if the logincontroller or cpf are null.
	 */
	public super0Controller(LoginController lc, ConsultPatientFileController cpf)
			throws IllegalArgumentException {
		super(lc);
		if(cpf.equals(null)) throw new IllegalArgumentException("cpf is null!");
		this.cpf = cpf;
	}

	/**
	 * Protected method for the medicaltestcontroller: allows to get the cpf.
	 * @return the cpf.
	 */
	protected ConsultPatientFileController getConsultPatientFileController() {
		return cpf;
	}

}
