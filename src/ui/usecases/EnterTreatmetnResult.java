package ui.usecases;

import ui.UIData;
import ui.UseCase;
import controllers.EnterTreatmentResultController;

public class EnterTreatmetnResult extends UseCase
{

	private EnterTreatmentResultController controller;

	public EnterTreatmetnResult(UIData data) throws Exception {
		super(data,0);
		controller = new EnterTreatmentResultController(data.getLoginController());
		
	}

	@Override
	public UseCase execute() {
		
		return null;
	}
	@Override
	public String toString()
	{
		return "enter treatment result";
	}

}
