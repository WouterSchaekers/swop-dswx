package controllers;

import java.util.Collection;
import resources.MedicalTest;
import resources.Result;
import scheduler.Scheduler;
import patient.PatientFile;
import users.Doctor;

/**
 * This class can be used to do schedule medical tests etc...
 */
public class MedicalTestController extends MedicalSuperController
{
	private Scheduler s; // the scheduler for this controller

	/**
	 * Use of empty constructor is <B>NOT</B> allowed!
	 */
	private MedicalTestController() {
		super(null, null);
	}

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
			ConsultPatientFileController cpf, DataPasser dp)
			throws IllegalArgumentException {
		super(lc, cpf);
		if (s.equals(null))
			throw new IllegalArgumentException("Scheduler can't be null!!");
		this.s = s;
	}

	/**
	 * This method is used to schedule a medical test.
	 * 
	 * @param d
	 *            The doctor who orders the test.
	 * @param m
	 *            The test being ordered.
	 */
	public void orderMedicalTest(Doctor d, MedicalTest m, PatientFile p) {
		// TODO: implement properly.
	}

	/**
	 * @param m
	 *            The medicaltest whose result needs to be fetched.
	 * @return The result of an executed medical test.
	 */
	public Result getResultsFrom(MedicalTest m) {
		// TODO: implement
		return null;
	}
}
