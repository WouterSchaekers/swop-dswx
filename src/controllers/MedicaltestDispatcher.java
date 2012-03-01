package controllers;

import machine.MachinePool;
import medicaltest.MedicalTest;
import patient.PatientFile;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import scheduler.task.unscheduled.tests.UnscheduledMedicalTest;
import users.UserManager;
import warehouse.Warehouse;
import exceptions.*;

public class MedicaltestDispatcher
{

	public void dispatch(MedicalTest test, UserManager userm,
			Warehouse warehouse, PatientFile file, TimeLord systemtime,
			TaskManager taskmanager, MachinePool pool)
			throws InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException, InvalidTreatmentException,
			InvalidTimeSlotException, InvalidHospitalDateArgument {
		UnscheduledMedicalTest t = test.getUnscheduled(userm, warehouse, file,
				systemtime, taskmanager, pool);
		file.addMedicalTest(test);
		taskmanager.addTask(t);
	}

}
