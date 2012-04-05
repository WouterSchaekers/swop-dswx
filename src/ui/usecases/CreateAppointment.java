package ui.usecases;

import java.util.Collection;
import scheduler.HospitalDate;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import controllers.CreateAppointmentController;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import exceptions.CanNeverBeScheduledException;
import exceptions.InvalidConsultPatientFileController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class CreateAppointment extends UseCase
{

	private CreateAppointmentController controller;
	private Displayer<DoctorIN> doctorDisplayer = new Displayer<DoctorIN>()
	{

		@Override
		public void display(DoctorIN t) {
			print("Doctor:"+t.getName());
		}
	};
	private Displayer<PatientFileIN> patientFileINdisplayer = new Displayer<PatientFileIN>()
			{

				@Override
				public void display(PatientFileIN t) {
					print("Patient :"+t.getPatientIN().getName());
				}
			};

	public CreateAppointment(UIData data) throws InvalidLoginControllerException, InvalidHospitalException, InvalidConsultPatientFileController {
		super(data, 4);
		controller = new CreateAppointmentController(data.getLoginController());
		
	}

	@Override
	public UseCase execute() {
		printLn("Create appointment !");
		Collection<DoctorIN> doctors =controller.getAllDoctors();
		if(doctors.size()==0)
		{
			printLn("No doctors in the system.");
		}
		Collection<PatientFileIN> patientFiles = controller.getAllPatientFiles();
		if(patientFiles.size()==0)
		{
			printLn("No patients in this hospital");
		}
		printLn("Select a doctor");
		Selector<DoctorIN> doctorSelector= new Selector<DoctorIN>(doctors, doctorDisplayer);
		Selector<PatientFileIN> patientFileSelector = new Selector<PatientFileIN>(patientFiles, patientFileINdisplayer);
		DoctorIN selectedDoctor =doctorSelector.get();
		printLn("Doctor "+selectedDoctor.getName()+" was selected.");
		PatientFileIN file = patientFileSelector.get();
		printLn("Patient "+file.getPatientIN().getName()+" was selected");
		HospitalDate task;
		try {
			task = controller.scheduleNewAppointment(selectedDoctor, file);
		} catch (CanNeverBeScheduledException e) {
			printLn("This can never be scheduled."+e.getMessage());
			return new MainMenu(data);
		} 
		printLn("Appointment was succesfully scheduled at "+task.toString()+"");
		return new MainMenu(data);
	}

}
