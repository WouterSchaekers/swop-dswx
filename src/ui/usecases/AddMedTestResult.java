package ui.usecases;

import ui.UIData;
import ui.UseCase;
import controllers.EnterMedicaltestResultController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class AddMedTestResult extends UseCase
{

	private EnterMedicaltestResultController controller;

	public AddMedTestResult(UIData data) throws InvalidLoginControllerException, InvalidHospitalException {
		super(data,0);
		controller = new EnterMedicaltestResultController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		
		return null;
	}

}
