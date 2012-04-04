package scheduler.tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import patient.PatientFile;
import scheduler.tasks.AppointmentDescription;
import scheduler.tasks.Task;
import system.Campus;
import system.Hospital;
import system.StandardHospitalBuilder;
import users.Doctor;
import users.DoctorFactory;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;
import exceptions.UserAlreadyExistsException;

public class TestAllTasks
{	
	Hospital hospital = new StandardHospitalBuilder().build();
	Campus location = hospital.getAllCampuses().iterator().next();
	@Test
	public void scheduleAppointment() throws UserAlreadyExistsException, InvalidNameException, InvalidPatientFileException, InvalidHospitalDateException, CanNeverBeScheduledException
	{
		DoctorFactory f= new DoctorFactory();
		f.setLocation(location);
		f.setName("Jonathan");
		Doctor user =(Doctor) hospital.getUserManager().createUser(f);
		PatientFile file =hospital.getPatientFileManager().registerPatient("jenny", location);
		AppointmentDescription appointment =new AppointmentDescription(user, file, hospital.getTimeKeeper().getSystemTime());
		Task<AppointmentDescription> task = hospital.getTaskManager().add(appointment);
		assertTrue(task.isScheduled());
	}
}
