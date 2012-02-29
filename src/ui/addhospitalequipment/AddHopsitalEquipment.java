package ui.addhospitalequipment;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.AddHospitalEquipmentController;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

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
		// Step one create the controller that is to be used
		AddHospitalEquipmentController c = null;
		try {
			c = new AddHospitalEquipmentController(data.getLoginController(),
					data.getDataPasser());
		} catch (InvalidLoginControllerException e) {
			System.out.println("invalid login controller, user not logged in");
			return new SelectUsecase(data);
		} catch (InvalidHospitalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chainData.add(c);
		// Controller is created now yeya !
		return new ShowMachineTypes(data, chainData);
	}

}
