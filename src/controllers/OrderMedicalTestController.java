package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestFactory;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.tasks.Task;
import users.Doctor;
import users.User;
import exceptions.CanNeverBeScheduledException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;

/**
 * Use to order medical tests for patients.
 */
public class OrderMedicalTestController extends NeedsLoginAndPatientFileController
{
	public OrderMedicalTestController(LoginController lc, ConsultPatientFileController cpf)
			throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileOpenController,
			InvalidPatientFileException {
		super(lc, cpf);
		if (cpf.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged");
	}

	/**
	 * Use to allow the user to select the factory from which he/she would like
	 * to create a medical test from.
	 */
	public Collection<MedicalTestFactory> getMedicalTestFactories() throws InvalidLoginControllerException,
			InvalidPatientFileException, InvalidPatientFileOpenController {
		
		return hospital.getMedicalTests();
	}

	/**
	 * Adds a new medical test created from the given factory to the patient
	 * file currently opened and tries to schedule it right away.
	 * 
	 * @return Returns null when the scheduling fails, otherwise returns the
	 *         date the test has been scheduled on.
	 * @throws FactoryInstantiationException
	 *             If there's an error in creating the description with the set
	 *             parameters in the given factory.
	 * @throws CanNeverBeScheduledException
	 *             If there is not enough staff in the hospital to schedule the
	 *             requested medical test.
	 */
	public <T extends MedicalTest> HospitalDate addMedicaltest(MedicalTestFactory medicalTestFactory)
			throws FactoryInstantiationException, CanNeverBeScheduledException {
		PatientFile pf = (PatientFile) cpfc.getPatientFile();
		// create the description and transform it into a task that gets added
		// to the taskman's queue right away.
		@SuppressWarnings("unchecked")
		Task<T> createdTest = (Task<T>) hospital.getTaskManager().add(pf.createMedicalTest(medicalTestFactory));
		pf.addMedicalTest(createdTest);

		// attempt to schedule and return date if it worked.
		hospital.getTaskManager().update(createdTest, null);
		if (createdTest.isScheduled())
			return createdTest.getDate();
		return null;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}