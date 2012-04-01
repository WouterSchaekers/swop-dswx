package controllers;

import java.util.Collection;
import users.Nurse;
import users.User;
import controllers.interfaces.TaskIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;
import exceptions.InvalidResultException;

/**
 * Use this controller to enter the result of a treatment.
 */
public class EnterTreatmentResultController extends NeedsLoginAndPatientFileController
{

	public EnterTreatmentResultController(LoginController lc, ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileOpenController {
		super(lc, pfoc);
	}

	/**
	 * 
	 * @return All finished Treatments that do not have results yet.
	 * @throws InvalidLoginControllerException
	 * @throws InvalidPatientFileOpenController
	 */
	public Collection<TaskIN> getTreatmentsThatNeedResults() {
		return hospital.getTaskManager().getTreatmentsThatNeedResults();
	}

	/**
	 * Adds the given report to the selected Task.
	 * 
	 * @throws InvalidResultException
	 */
	public void addResultTo(TaskIN selected, String report) throws InvalidResultException {
		if (!(selected.isFinished() || selected.getDescription().needsResult()))
			throw new InvalidResultException("Selected task does not need a result!");
		selected.getDescription().setResult(report);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
