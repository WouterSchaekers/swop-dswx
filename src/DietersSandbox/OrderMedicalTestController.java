package DietersSandbox;

import java.util.Collection;
import medicaltest.MedicalTestFactory;
import medicaltest.MedicalTests;
import scheduler.HospitalDate;
import users.Doctor;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;

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
	
	public HospitalDate addMedicaltest() {
		//TODO: implement
		return null;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}