package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestFactory;
import scheduler.HospitalDate;
import scheduler.tasks.Task;
import users.Doctor;
import users.User;
import controllers.interfaces.PatientFileIN;
import exceptions.CanNeverBeScheduledException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidConsultPatientFileController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;

/**
 * Use to order medical tests for patients.
 */
@controllers.PUBLICAPI
public class OrderMedicalTestController extends NeedsLoginAndPatientFileController
{
	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that watns to order medical
	 *            tests for a patient in this hospital.
	 * @param consultPatientFileController
	 *            The consult patient file controller of the user that watns to
	 *            order a medical test.
	 * @throws InvalidLoginControllerException
	 *             If the user that is stored in the given login controller is
	 *             not a doctor or if the controller is invalid in any other
	 *             way.
	 * @throws InvalidHospitalException
	 * @throws InvalidConsultPatientFileController
	 *             If the consult patient file controller given to this
	 *             constructor is invalid in any way.
	 * @throws InvalidPatientFileException
	 *             If the patient file opened in the consult patient file
	 *             controller has been discharged.
	 * @see HospitalController
	 * @see NeedsLoginAndPatientFileController
	 */
	@controllers.PUBLICAPI
	public OrderMedicalTestController(LoginController loginController,
			ConsultPatientFileController consultPatientFileController) throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidConsultPatientFileController, InvalidPatientFileException {
		super(loginController, consultPatientFileController);
		if (consultPatientFileController.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged");
	}

	/**
	 * Use to select the factory with which you would like to create a medical
	 * test.
	 * 
	 * @return A collection of factories that let you create medical tests.
	 */
	@controllers.PUBLICAPI
	public Collection<MedicalTestFactory> getMedicalTestFactories()  {
		return hospital.getMedicalTests();
	}

	/**
	 * Use the returned collection to set the patient file field of the medical
	 * test factory that you want to use to create a medical test for the
	 * patient file that has been opened.
	 */
	public Collection<PatientFileIN> getPatientFiles() {
		return this.hospital.getPatientFileManager().getPatientFileINs();
	}

	/**
	 * Adds a new medical test created from the given factory to the patient
	 * file currently opened and tries to schedule it right away.
	 * 
	 * @return Returns null when the scheduling fails, otherwise returns the
	 *         date the test has been scheduled on.
	 * @throws FactoryInstantiationException
	 *             If a problem occurs while creating the description with the
	 *             set parameters in the given factory.
	 * @throws CanNeverBeScheduledException
	 *             If there is not enough staff in the hospital to schedule the
	 *             requested medical test.
	 */
	@controllers.PUBLICAPI
	public <T extends MedicalTest> HospitalDate addMedicaltest(MedicalTestFactory medicalTestFactory)
			throws FactoryInstantiationException, CanNeverBeScheduledException {
		
		medicalTestFactory.setCreationDate(hospital.getTimeKeeper().getSystemTime());
		@SuppressWarnings("unchecked")
		Task<T> createdTest = (Task<T>) hospital.getTaskManager().add(medicalTestFactory.create());

		if (createdTest.isScheduled())
			return createdTest.getDate();
		return null;
	}

	/**
	 * @return True if the given user is a doctor.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}