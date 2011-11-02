package controllers;

import scheduler.Appointment;
import task.TaskManager;
import medicaltest.MedicalTest;
import medicaltest.Result;

/**
 * This class can be used to do schedule medical tests etc...
 */
public class MedicalTestController extends MedicalSuperController
{

	private TaskManager t;
	
	/**
	 * Use of empty constructor is <B>NOT</B> allowed!
	 */
	private MedicalTestController(TaskManager t) {
		super(null, null);
		this.t = t;
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
	}

	/**
	 * This method is used to schedule an XRayscan.
	 * @return The appointment made.
	 */
	public Appointment orderXRay(String bodypart, int amountOfImages, int zoomLevel) {
		return t.scheduldeXRay(bodypart, amountOfImages, zoomLevel);
	}
	
	/**
	 * This method is used to schedule an UltraSoundScan.
	 * @return The appointment made.
	 */
	public Appointment orderUltraSound(String focus, boolean recVid, boolean recImg) {
		return t.scheduldeUltraSound(focus, recVid, recImg);
	}
	
	/**
	 * This method is used to schedule a BloodAnalysis.
	 * @return The appointment made.
	 */
	public Appointment orderBloodAnalysis(String focus, int amountOfAnalyses) {
		return t.scheduldeBloodAnalysis(focus, amountOfAnalyses);
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
