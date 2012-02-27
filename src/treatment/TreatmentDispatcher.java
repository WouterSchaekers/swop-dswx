package treatment;

import machine.MachinePool;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.TimeLord;
import scheduler.task.TaskManager;
import scheduler.task.unscheduled.treatment.UnscheduledCast;
import scheduler.task.unscheduled.treatment.UnscheduledMedication;
import scheduler.task.unscheduled.treatment.UnscheduledSurgery;
import users.UserManager;
import warehouse.Warehouse;
import controllers.interfaces.CastIN;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.MedicationIN;
import controllers.interfaces.SurgeryIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidResourceException;
import exceptions.InvalidTimeSlotException;
import exceptions.InvalidTreatmentException;

public class TreatmentDispatcher
{
	public void dispatchCast(CastIN treatment,DiagnoseIN Diagnose,UserManager userm,Warehouse warehouse,PatientFile file,TimeLord systemtime,TaskManager taskmanager) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidAmountException, InvalidHospitalDateException, InvalidTreatmentException, InvalidTimeSlotException, InvalidHospitalDateArgument
	{
		UnscheduledCast newCast = new UnscheduledCast(file, (Diagnose)Diagnose, systemtime.getSystemTime(), (Treatment) treatment, userm, warehouse);
		((Diagnose)Diagnose).assignTreatment((Treatment)treatment);
		taskmanager.addTask(newCast);
	}
	
	public void dispatchMedication(MedicationIN med,DiagnoseIN Diagnose,UserManager userm,Warehouse warehouse,PatientFile file,TimeLord systemtime,TaskManager taskmanager) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidAmountException, InvalidHospitalDateException, InvalidTreatmentException, InvalidTimeSlotException, InvalidHospitalDateArgument
	{
		UnscheduledMedication m = new UnscheduledMedication(file,(Diagnose) Diagnose, systemtime.getSystemTime(),(Medication) med, userm, warehouse);
		((Diagnose)Diagnose).assignTreatment((Treatment)med);
		taskmanager.addTask(m);
	}
	
	public void dispatchSurgery(SurgeryIN treatment,DiagnoseIN Diagnose,UserManager userm,MachinePool pool,PatientFile file,TimeLord systemtime,TaskManager taskmanager) throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidAmountException, InvalidHospitalDateException, InvalidTreatmentException, InvalidTimeSlotException, InvalidHospitalDateArgument
	{
		UnscheduledSurgery newSurg = new UnscheduledSurgery(file,(Diagnose) Diagnose, systemtime.getSystemTime(), userm, pool, (Surgery)treatment);
		((Diagnose)Diagnose).assignTreatment((Treatment) treatment);
		taskmanager.addTask(newSurg);
	}
}
