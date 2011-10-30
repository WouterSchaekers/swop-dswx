package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import users.Doctor;
import users.Nurse;
import users.User.usertype;

public class RegisterPatientController
{
	PatientFileDTO openPatientFile;
	DataPasser dataPasser;

	public RegisterPatientController(LoginController loginController,
			DataPasser dataPasser) {
		if (!(loginController.getUser() instanceof Nurse)) {
			throw new IllegalArgumentException(loginController.getUser()
					.getName() + " is not a Nurse.");
		}
		this.dataPasser=dataPasser;
	}

	public Collection<PatientFileDTO> getAllPatients() {
		Collection<PatientFileDTO> RV = new ArrayList<PatientFileDTO>();
		for (PatientFile file : dataPasser.getPatientFileManager()
				.getAllPatientFiles())
			RV.add(new PatientFileDTO(file));
		return RV;
	}

	public void registerPatient(PatientFileDTO file) {
		this.dataPasser.getPatientFileManager().checkIn(file.getPatientFile());

		this.openPatientFile = file;
	}

	public AppointmentDTO CreateAppointMent(UserDTO userDTO,
			PatientFileDTO pfile) {
		if (userDTO == null || pfile == null)
			throw new IllegalArgumentException("null objects are illegal");
		if (userDTO.type() != usertype.Doctor)
			throw new IllegalArgumentException(userDTO.getName()
					+ "is not a doctor");
		if(this.openPatientFile == null)
			throw new IllegalStateException("No patientfile has been opened yet");
		if(pfile.getPatientFile().isDischarged())
			throw new IllegalArgumentException(pfile.getName()+" is not checked in");
		return new AppointmentDTO(dataPasser.getScheduler().addAppointment(
				pfile.getPatientFile(), (Doctor)userDTO.getUser(), 60));
	}

	public void createNewPatient(DataPasser dataPasser2, String name) {
		dataPasser2.getPatientFileManager().registerPatient(name);
		
	}

}
