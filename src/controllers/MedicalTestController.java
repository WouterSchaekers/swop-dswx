package controllers;

import task.TaskManager;

/**
 * This class can be used to do schedule medical tests etc...
 */
public class MedicalTestController extends MedicalSuperController
{
	private TaskManager t;

	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The logincontroller of the user whom this controller is to be
	 *            assigned to.
	 * @param cpf
	 *            The consultpatientfilecontroller of the user whom this
	 *            controller is to be assigned to.
	 * @param s
	 *            The scheduler of the user whom this controller is to be
	 *            assigned to.
	 * @throws IllegalArgumentException
	 *             if one of the parameters is null.
	 */
	public MedicalTestController(LoginController lc,
			ConsultPatientFileController cpf, DataPasser dp, TaskManager t)
			throws IllegalArgumentException {
		super(lc, cpf);
		this.t = t;
	}
	
}
