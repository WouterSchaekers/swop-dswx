package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestFactory;
import medicaltest.MedicalTests;
import patient.PatientFile;
import system.Hospital;
import users.Doctor;
import users.User;
import exceptions.*;

/**
 * This class can be used to create medical tests etc...
 */
public class OrderMedicalTestController extends NeedsLoginAndPatientFileController
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
	public OrderMedicalTestController(LoginController lc,
			PatientFileOpenController cpf, Hospital dp)
			throws IllegalArgumentException, InvalidLoginControllerException,
			InvalidPatientFileException, InvalidHospitalStateException,
			InvalidPatientFileOpenController {
		super(dp, lc, cpf);
	}

	public Collection<MedicalTestFactory> getMedicalTestFactories()
			throws InvalidLoginControllerException,
			InvalidPatientFileException, InvalidPatientFileOpenController {
		return new MedicalTests().factories();

	}

	public void addMedicaltest(MedicalTest create, Hospital data)
			throws InvalidLoginControllerException,
			InvalidPatientFileException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidTreatmentException, InvalidTimeSlotException,
			InvalidHospitalDateArgument, InvalidPatientFileOpenController {
		new MedicaltestDispatcher().dispatch(create, data.getUserManager(),
				data.getWarehouse(), (PatientFile) pfoc.getPatientFile(),
				data.getSystemTime(), data.getTaskManager(),
				data.getMachinePool());

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
