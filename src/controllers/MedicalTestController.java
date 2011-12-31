package controllers;

import java.util.Collection;
import patient.PatientFile;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestFactory;
import medicaltest.MedicalTests;
import users.Doctor;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidResourceException;
import exceptions.InvalidTimeSlotException;
import exceptions.InvalidTreatmentException;


/**
 * This class can be used to do schedule medical tests etc...
 */
public class MedicalTestController 
{

	private LoginController logincontroller;
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
	 * @throws InvalidLoginControllerException 
	 * @throws InvalidPatientFileException 
	 */
	public MedicalTestController(LoginController lc,
			PatientFileOpenController cpf, DataPasser dp)
			throws IllegalArgumentException, InvalidLoginControllerException, InvalidPatientFileException {
		if(!isValidLoginController(lc))
			throw new InvalidLoginControllerException("");
		if(!isValidPatientFileOpenController(cpf,lc))
			throw new InvalidPatientFileException();
		this.logincontroller=lc;
		
	}

	private boolean isValidPatientFileOpenController(
		PatientFileOpenController patientFileOpenController,LoginController loginc) {
		if (patientFileOpenController == null)
			return false;
		if (!patientFileOpenController.isValidLoginController(loginc))
			return false;
		if (patientFileOpenController.getPatientFile() == null)
			return false;
		if(patientFileOpenController.getPatientFile().isDischarged())
			return false;
		return true;
	}

	private boolean isValidLoginController(LoginController lc) {
		if (lc == null)
			return false;
		if (this.logincontroller != null
				&& !logincontroller.equals(this.logincontroller))
			return false;
		if (!(lc.getUser() instanceof Doctor))
			return false;
		
		return true;
	}

	public Collection<MedicalTestFactory> getMedicalTestFactories(LoginController loginc,PatientFileOpenController patienfileOpenController) throws InvalidLoginControllerException, InvalidPatientFileException {
		if(!isValidLoginController(loginc))
			throw new InvalidLoginControllerException("");
		if(!isValidPatientFileOpenController(patienfileOpenController, loginc))
			throw new InvalidPatientFileException();
		return new MedicalTests().factories();
		
	}

	public void addMedicaltest(LoginController loginController2,
			PatientFileOpenController patientFileOpenController2,
			MedicalTest create,DataPasser data) throws InvalidLoginControllerException, InvalidPatientFileException, InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidAmountException, InvalidHospitalDateException, InvalidTreatmentException, InvalidTimeSlotException, InvalidHospitalDateArgument {
		if(!isValidLoginController(loginController2))
			throw new InvalidLoginControllerException("");
		if(!isValidPatientFileOpenController(patientFileOpenController2, loginController2))
			throw new InvalidPatientFileException();
		new MedicaltestDispatcher().dispatch(create,data.getUserManager(),data.getWareHouse(),(PatientFile) patientFileOpenController2.getPatientFile(),data.getTimeLord(),data.getTaskmanager(),data.getMachinePool());

	}
	

	
}
