package controllers;

import help.Collections;
import help.Filter;
import java.util.ArrayList;
import java.util.Collection;
import medicaltest.MedicalTest;
import result.Result;
import result.ResultFactory;
import scheduler.tasks.Task;
import users.Nurse;
import users.User;
import controllers.interfaces.TaskIN;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidResultException;

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

	private final Filter medicalTestFilter = new Filter()
	{

		@Override
		public <T> boolean allows(T arg) {
			if (arg instanceof Task)
				if (((Task<?>) arg).getDescription() instanceof MedicalTest)
					if (((Task<?>) arg).getResult() == null)
						return ((Task<?>) arg).getResources().contains(loginController_.getUser());
			return false;
		}
	};

	/**
	 * Returns the collection of medicaltest tasks that have no result that are
	 * for the nurse that is currently logged in.
	 * 
	 * @return
	 */
	public Collection<TaskIN> getMedicalTests() {
		return new ArrayList<TaskIN>(Collections.filter(hospital.getTaskManager().getAllTasks(), medicalTestFilter));
	}

	/**
	 * Adds the result to the system.
	 * 
	 * @param task
	 * @param factory
	 * @return
	 * @throws InvalidResultException
	 * @throws FactoryInstantiationException
	 */
	public Result setResult(TaskIN task, ResultFactory factory) throws InvalidResultException,
			FactoryInstantiationException {
		
		return task.give(factory);
	}

	/**
	 * @return True if the given user is a nurse.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
