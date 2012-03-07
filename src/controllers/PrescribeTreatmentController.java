package controllers;

import patient.PatientFile;
import scheduler.task.unscheduled.treatment.UnscheduledTreatment;
import system.Hospital;
import treatment.Treatment;
import users.Doctor;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;

public class PrescribeTreatmentController extends
		NeedsLoginAndPatientFileController
{

	public PrescribeTreatmentController(LoginController loginc,
			Hospital hospital, ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController, InvalidPatientFileException {
		super(hospital, loginc, pfoc);
		if (pfoc.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged");
	}

	public void addTreatment(Treatment treatment) {
		UnscheduledTreatment task = treatment.getUnscheduled(
				hospital.getUserManager(), hospital.getWarehouse(),
				(PatientFile) pfoc.getPatientFile(), hospital.getSystemTime(),
				hospital.getTaskManager(), hospital.getMachinePool());
		((PatientFile) pfoc.getPatientFile()).getDiagnosis().addTreatment(treatment);
		hospital.getTaskManager().addTask(t);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}
