package controllers;

import java.util.Collection;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestFactory;
import medicaltest.MedicalTests;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.tasks.ScheduledTask;
import scheduler.UnscheduledTask;
import users.Doctor;
import users.User;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;
import exceptions.InvalidResourceException;
import exceptions.InvalidTimeSlotException;

/**
 * This class can be used to create medical tests etc...
 */
public class OrderMedicalTestController extends
		NeedsLoginAndPatientFileController
{

	public OrderMedicalTestController(LoginController lc,
			ConsultPatientFileController cpf)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController, InvalidPatientFileException {
		super(lc, cpf);
		if (cpf.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged");
	}

	public Collection<MedicalTestFactory> getMedicalTestFactories()
			throws InvalidLoginControllerException,
			InvalidPatientFileException, InvalidPatientFileOpenController {
		return new MedicalTests().factories();
	}
	
	public HospitalDate addMedicaltest(MedicalTest medicalTest)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException, InvalidTimeSlotException,
			InvalidHospitalDateArgument {

		UnscheduledTask t = medicalTest.getUnscheduled(hospital.getUserManager(),
				hospital.getWarehouse(), (PatientFile) pfoc.getPatientFile(),
				hospital.getSystemTime(), hospital.getMachinePool());
		
		((PatientFile) pfoc.getPatientFile()).addMedicalTest(medicalTest);
		ScheduledTask temp = hospital.getTaskManager().addTask(t);
		if(temp == null)
			return null;
		return temp.getStartDate();
			
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}