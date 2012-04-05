package controllers;

import java.util.Collection;
import users.Nurse;
import users.User;
import controllers.interfaces.TaskIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidResultException;

/**
 * Use this controller to enter the result of a treatment that has finished.
 */
@controllers.PUBLICAPI
public class EnterTreatmentResultController extends NeedsLoginController
{

	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that wants to enter the
	 *            result of a treatment.
	 * @throws InvalidLoginControllerException
	 *             If the user to whom the given login controller belongs to is
	 *             not a nurse, or if the given controller is invalid in any
	 *             other way.
	 * @throws InvalidHospitalException
	 * @see HospitalController
	 * @see NeedsLoginController
	 */
	@controllers.PUBLICAPI
	public EnterTreatmentResultController(LoginController loginController) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(loginController);
	}

	/**
	 * @return All treatments that have finished and need results.
	 */
	@controllers.PUBLICAPI
	public Collection<TaskIN> getTreatmentsThatNeedResults() {
		return hospital.getTaskManager().getTreatmentsThatNeedResults();
	}

	/**
	 * Adds the given report to the selected treatment.
	 * 
	 * @throws InvalidResultException
	 *             If the given result is invalid.
	 */
	@controllers.PUBLICAPI
	public void addResultTo(TaskIN selectedTreatment, String report) throws InvalidResultException {
		if (!(selectedTreatment.isFinished() || selectedTreatment.getDescription().needsResult()))
			throw new InvalidResultException("Selected task does not need a result!");
		selectedTreatment.getDescription().setResult(report);
	}

	/**
	 * @return True if the given user is a nurse.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
