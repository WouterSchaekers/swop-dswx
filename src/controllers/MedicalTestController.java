package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestFactory;
import medicaltest.MedicalTests;
import patient.PatientFile;
import system.HospitalState;
import users.Doctor;
import users.User;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;
import exceptions.InvalidResourceException;
import exceptions.InvalidTimeSlotException;
import exceptions.InvalidTreatmentException;

/**
 * This class can be used to do schedule medical tests etc...
 */
public class MedicalTestController extends NeedsLoginAndPatientFileController
{

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
	 * @throws InvalidLoginControllerException
	 * @throws InvalidPatientFileException
	 * @throws InvalidPatientFileOpenController
	 * @throws InvalidHospitalStateException
	 */
	public MedicalTestController(LoginController lc,
			PatientFileOpenController cpf, HospitalState dp)
			throws IllegalArgumentException, InvalidLoginControllerException,
			InvalidPatientFileException, InvalidHospitalStateException,
			InvalidPatientFileOpenController {
		super(dp, lc, cpf);
	}

	public Collection<MedicalTestFactory> getMedicalTestFactories(
			LoginController loginc,
			PatientFileOpenController patienfileOpenController)
			throws InvalidLoginControllerException,
			InvalidPatientFileException, InvalidPatientFileOpenController {
		checkValidity(loginc, patienfileOpenController);
		return new MedicalTests().factories();

	}

	public void addMedicaltest(LoginController loginController2,
			PatientFileOpenController patientFileOpenController2,
			MedicalTest create, HospitalState data)
			throws InvalidLoginControllerException,
			InvalidPatientFileException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidTreatmentException, InvalidTimeSlotException,
			InvalidHospitalDateArgument, InvalidPatientFileOpenController {
		checkValidity(loginController2, patientFileOpenController2);
		new MedicaltestDispatcher().dispatch(create, data.getUserManager(),
				data.getWarehouse(),
				(PatientFile) patientFileOpenController2.getPatientFile(),
				data.getSystemTime(), data.getTaskManager(),
				data.getMachinePool());

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
