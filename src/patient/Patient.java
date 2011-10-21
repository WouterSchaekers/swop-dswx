package patient;

public class Patient
{
	
	private PatientFile pfile;
	
	public Patient(PatientFile file) {
		this.pfile = file;
	}
	
	public PatientFile getFile() {
		return this.pfile;
	}
}
