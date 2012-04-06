package controllers;

import help.Collections;
import help.Filter;
import java.util.ArrayList;
import java.util.Collection;
import result.Result;
import result.ResultFactory;
import scheduler.tasks.Task;
import treatment.Treatment;
import users.Nurse;
import users.User;
import controllers.interfaces.TaskIN;
import exceptions.FactoryInstantiationException;
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

	private final Filter treatmentTaskFilter = new Filter()
	{
		
		@Override
		public <T> boolean allows(T arg) {
			if(arg instanceof Task)
				if(((Task<?>)arg).getDescription() instanceof Treatment)
					if(((Task<?>)arg).getResult()==null)
						return ((Task<?>) arg).getResources().contains(loginController_.getUser());
			return false;
		}
	};
	/**
	 * Returns the collection of treatment tasks that have no result that are for the nurse that is currently logged in.
	 * @return
	 */
	public Collection<TaskIN> getTreatments()
	{
		return new ArrayList<TaskIN>(Collections.filter(hospital.getTaskManager().getAllTasks(),treatmentTaskFilter));
	}
	/**
	 * Adds the result to the system.
	 * @param task
	 * @param factory
	 * @return
	 * @throws InvalidResultException
	 * @throws FactoryInstantiationException
	 */
	public Result setResult(TaskIN task,ResultFactory factory) throws InvalidResultException, FactoryInstantiationException
	{
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
