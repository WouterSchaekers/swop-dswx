package ui.addhospitalequipment;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.AddHospitalEquipmentController;

public class AddHopsitalEquipment extends AddHospitalEquipmentSuperClass
{

	private AddHopsitalEquipment(UserinterfaceData data,
			AddHospitalEquipmentData chaindata) {
		super(data, chaindata);
	}

	public AddHopsitalEquipment(UserinterfaceData data) {
		this(data, new AddHospitalEquipmentData());
	}

	@Override
	public Usecase Execute() {
		try {
			// Step one create the controller that is to be used
			AddHospitalEquipmentController c = null;
			c = new AddHospitalEquipmentController(data.getLoginController());

			chainData.add(c);
			// Controller is created now yeya !
			return new ShowMachineTypes(data, chainData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
