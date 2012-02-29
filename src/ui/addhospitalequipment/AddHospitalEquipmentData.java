package ui.addhospitalequipment;

import machine.MachineBuilder;
import controllers.AddHospitalEquipmentController;

public class AddHospitalEquipmentData
{

	private AddHospitalEquipmentController hospitalEquipController;
	private MachineBuilder machineBuilder;

	public void add(AddHospitalEquipmentController c) {
		this.hospitalEquipController = c;

	}

	public AddHospitalEquipmentController getController() {
		return hospitalEquipController;
	}

	public void add(MachineBuilder builder) {
		this.machineBuilder = builder;

	}

	public MachineBuilder getMachineBuilder() {
		return this.machineBuilder;
	}

}
