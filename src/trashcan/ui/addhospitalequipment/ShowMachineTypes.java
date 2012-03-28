package ui.addhospitalequipment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import machine.MachineBuilder;
import ui.SelectUsecase;
import ui.SelectionMenu;
import ui.Usecase;
import ui.UserinterfaceData;

public class ShowMachineTypes extends AddHospitalEquipmentSuperClass
{

	public ShowMachineTypes(UserinterfaceData data,
			AddHospitalEquipmentData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		try {
			Map<String, MachineBuilder> map = new HashMap<String, MachineBuilder>();

			Collection<MachineBuilder> mb = chainData.getController()
					.getAllMachineBuilders();
			for (MachineBuilder b : mb) {

				map.put(b.toString(), b);
			}

			SelectionMenu<MachineBuilder> menu = new SelectionMenu<MachineBuilder>(
					map);
			MachineBuilder builder = menu.execute();
			if (builder == null) {
				System.out.println("Invalid menu option please try again");
				return this;
			}
			chainData.add(builder);
			return new BuildMachine(data, chainData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
