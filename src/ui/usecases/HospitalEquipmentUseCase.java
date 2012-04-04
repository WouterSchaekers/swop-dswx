package ui.usecases;

import controllers.AddHospitalEquipmentController;
import ui.UIData;
import ui.UseCase;

public class HospitalEquipmentUseCase extends UseCase
{

	private AddHospitalEquipmentController addEquipment;

	public HospitalEquipmentUseCase(UIData data) throws Exception {
		super(data);
		addEquipment = new AddHospitalEquipmentController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		printLn("Add hospital equipment:");
		//addEquipment.getAllMachineBuilders();
		return null;
	}

}
