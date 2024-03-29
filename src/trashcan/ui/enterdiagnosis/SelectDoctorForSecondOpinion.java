package ui.enterdiagnosis;

import help.Collections;
import help.Filter;
import java.util.HashMap;
import java.util.Map;
import ui.SelectUsecase;
import ui.SelectionMenu;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class SelectDoctorForSecondOpinion extends EnterDiagnoseSuperClass
{

	SelectDoctorForSecondOpinion(UserinterfaceData data,
			EnterDiagnoseData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		// list all the doctors and select one :)
		Map<String, DoctorIN> doctors = new HashMap<String, DoctorIN>();
		for (UserIN d : Collections.filter(data.getLoginController()
				.getAllUsers(), new Filter()
		{

			@Override
			public <T> boolean allows(T arg) {
				return arg instanceof DoctorIN;
			}
		})) {
			DoctorIN doc = (DoctorIN) d;
			doctors.put(doc.getName(), doc);
		}
		DoctorIN choice;
		do {
			SelectionMenu<DoctorIN> t = new SelectionMenu<DoctorIN>(doctors);
			choice = t.execute();
		} while (choice == null);
		try {
			chaindata.getController().enterDiagnose(chaindata.getDiag());
		} catch (InvalidLoginControllerException e) {
			System.out.println("Priviliged action please log in again");
			return new SelectUsecase(data);

		} catch (InvalidPatientFileOpenController e) {
			System.out.println("No patienf file was opened");
			return new SelectUsecase(data);

		} catch (InvalidDiagnoseException e) {
			System.out
					.println("Somethign went wrong with creating the diagnose please try again");
			return new SelectUsecase(data);
		} catch (InvalidDoctorException e) {
			System.out.println("Wrong doctor ");
			return new SelectUsecase(data);
		}
		System.out.println("Diagnose succesfully entered !");

		return new SelectUsecase(data);
	}

}
