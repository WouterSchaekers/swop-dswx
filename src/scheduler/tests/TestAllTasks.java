package scheduler.tests;

import static org.junit.Assert.assertTrue;
import java.util.Collection;
import machine.BloodAnalyserBuilder;
import machine.MachineBuilder;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTest;
import org.junit.Test;
import patient.PatientFile;
import scheduler.tasks.AppointmentDescription;
import scheduler.tasks.Task;
import system.Campus;
import system.Hospital;
import system.StandardHospitalBuilder;
import users.Doctor;
import users.DoctorFactory;
import users.Nurse;
import users.NurseFactory;
import users.UserFactory;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;
import exceptions.UserAlreadyExistsException;

public class TestAllTasks
{
	Hospital hospital = new StandardHospitalBuilder().build();
	Campus location = hospital.getAllCampuses().iterator().next();
	@Test
	public void scheduleAppointment() throws UserAlreadyExistsException, InvalidNameException, InvalidPatientFileException, InvalidHospitalDateException, CanNeverBeScheduledException, InvalidLocationException
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
	@Test
	public void scheduleMedicalTestBlood() throws Exception
	{
		NurseFactory f= new NurseFactory();
		f.setName("Jenny");
		f.setLocation(location);
		Nurse user =(Nurse) hospital.getUserManager().createUser(f);
		PatientFile file =hospital.getPatientFileManager().registerPatient("jenny", location);
		BloodAnalyserBuilder builder = bloodanalyzer(location.getMachinePool().getAllBuilders());
		builder.setLocation(location);
		builder.setLocationWithinCampus("Randomstring");
		builder.setSerial(123);
		location.getMachinePool().addMachine(builder);
		//There is a blood analyzer a nurse and a patient
		BloodAnalysisFactory fact = new BloodAnalysisFactory();
		fact.setCreationDate(hospital.getTimeKeeper().getSystemTime());
		fact.setFocus("GG");
		fact.setNumberOfAnalysis(2);
		fact.setPatientFile(file);
		Task<MedicalTest> task = hospital.getTaskManager().add(fact.create());
		assertTrue(task.isScheduled());
	}
	private BloodAnalyserBuilder bloodanalyzer(Collection<MachineBuilder> allBuilders) {
		for(MachineBuilder builder:allBuilders)
			if(builder instanceof BloodAnalyserBuilder)
				return (BloodAnalyserBuilder)builder;
		return null;
	}
	@Test
	public void scheduleMedicalTestXrayScan()
	{
		
	}
	@Test
	public void scheduleMedicalTestUltraSound()
	{
		
	}
}
