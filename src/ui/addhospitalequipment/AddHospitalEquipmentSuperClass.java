package ui.addhospitalequipment;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class AddHospitalEquipmentSuperClass extends Usecase
{

	protected AddHospitalEquipmentData chainData;

	public AddHospitalEquipmentSuperClass(UserinterfaceData data,AddHospitalEquipmentData chaindata) {
		super(data);
		this.chainData=chaindata;
	}

}
