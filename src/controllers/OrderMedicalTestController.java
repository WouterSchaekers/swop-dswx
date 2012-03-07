package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestFactory;
import medicaltest.MedicalTests;
import patient.PatientFile;
import scheduler.task.unscheduled.tests.UnscheduledMedicalTest;
import system.Hospital;
import users.Doctor;
import users.User;
import exceptions.*;

/**
 * This class can be used to create medical tests etc...
 */
public class OrderMedicalTestController extends
		NeedsLoginAndPatientFileController
{

	public OrderMedicalTestController(LoginController lc,
			ConsultPatientFileController cpf, Hospital hospital)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController, InvalidPatientFileException {
		super(hospital, lc, cpf);
		if (cpf.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged");
	}

	public Collection<MedicalTestFactory> getMedicalTestFactories()
			throws InvalidLoginControllerException,
			InvalidPatientFileException, InvalidPatientFileOpenController {
		return new MedicalTests().factories();
	}

	/**
	 * Adds and schedules the given medical test.
	 */
	public void addMedicaltest(MedicalTest medicalTest)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException, InvalidTimeSlotException,
			InvalidHospitalDateArgument {

		UnscheduledMedicalTest t = medicalTest.getUnscheduled(hospital.getUserManager(),
				hospital.getWarehouse(), (PatientFile) pfoc.getPatientFile(),
				hospital.getSystemTime(), hospital.getMachinePool());
		
		((PatientFile) pfoc.getPatientFile()).addMedicalTest(medicalTest);
		hospital.getTaskManager().addTask(t);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}