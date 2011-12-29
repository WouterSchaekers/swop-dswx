package controllers;

/**
 * This is the supercontroller for the medicaltestcontroller and also extends
 * the normal supercontroller.
 * 
 */
public class MedicalSuperController extends SuperController
{
	private PatientFileOpenController cpf; // the cpf for this
												// supercontroller

	/**
	 * Use of empty constructor is <B>NOT</B> allowed!
	 */
	private MedicalSuperController() {
		super(null);
	}

	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The logincontroller for this super0controller.
	 * @param cpf
	 *            The cpf for this super0controller
	 * @throws IllegalArgumentException
	 *             if the logincontroller or cpf are null.
	 */
	public MedicalSuperController(LoginController lc,
			PatientFileOpenController cpf) throws IllegalArgumentException {
		super(lc);
		if (cpf == null)
			throw new IllegalArgumentException("cpf is null!");
		this.cpf = cpf;
	}

	/**
	 * Protected method for the medicaltestcontroller: allows to get the cpf.
	 * 
	 * @return the cpf.
	 */
	protected PatientFileOpenController getConsultPatientFileController() {
		return cpf;
	}

}
