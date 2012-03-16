package DietersSandbox;

import java.util.LinkedList;
import medicaltest.MedicalTest;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class EnterMedicaltestResultController extends NeedsLoginController
{
	public EnterMedicaltestResultController(LoginController lc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController {
		super(lc);
	}

	public LinkedList<MedicalTest> getAllMedicalTests()
			throws InvalidLoginControllerException,
			InvalidPatientFileOpenController {
		return null;
		// TODO: fix
	}

	/**
	 * 
	 * @param report
	 * @param index
	 * The index of the medical test of which you want to <...>
	 */
	public void addResultTo(String report, int index) {
		// TODO: implement
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
