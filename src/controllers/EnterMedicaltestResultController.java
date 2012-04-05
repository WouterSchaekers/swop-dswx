package controllers;

import java.util.Collection;
import users.Nurse;
import users.User;
import controllers.interfaces.TaskIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * Use this controller to enter the result of a medical test that has completed.
 */
@controllers.PUBLICAPI
public class EnterMedicaltestResultController extends NeedsLoginController
{
	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that wants to enter a result
	 *            of a medical test.
	 * @throws InvalidLoginControllerException
	 *             If the given login controller is not owned by a nurse or is
	 *             invalid in any other way.
	 * @throws InvalidHospitalException
	 * @see HospitalController
	 * @see NeedsLoginController
	 */
	@controllers.PUBLICAPI
	public EnterMedicaltestResultController(LoginController loginController) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(loginController);
	}

	/**
	 * Use this method to get all medical tests that do not have a result yet
	 * and require one.
	 * 
	 * @return All finished medical tests that do not have results yet and
	 *         require one.
	 */
	@controllers.PUBLICAPI
	public Collection<TaskIN> getMedicalTestsThatNeedResults() {
		return null;//hospital.getTaskManager().getMedicalTestsThatNeedResults();
	}

	/**
	 * Adds the given report to the selected Task.
	 * 
	 * @throws InvalidResultException
	 *             If the selected task does not need a result or is not
	 *             finished yet.
	 */
//	@controllers.PUBLICAPI
//	public void addResultTo(TaskIN selectedMedicalTest, String report) throws InvalidResultException {
//		if (!(selectedMedicalTest.isFinished() || selectedMedicalTest.getDescription().needsResult()))
//			throw new InvalidResultException("Selected task does not need a result!");
//		selectedMedicalTest.getDescription().setResult(report);
//	}

	/**
	 * @return True if the given user is a nurse.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
