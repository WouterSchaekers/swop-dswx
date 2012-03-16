package DietersSandbox;

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

	public PrescribeTreatmentController(LoginController lc, ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController, InvalidPatientFileException {
		super(lc, pfoc);
		if (pfoc.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged");
	}

	public void addTreatment(Treatment treatment) {
		//TODO: fix
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}
