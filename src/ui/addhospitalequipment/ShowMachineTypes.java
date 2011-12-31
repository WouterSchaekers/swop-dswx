package ui.addhospitalequipment;

import java.util.HashMap;
import java.util.Map;
import exceptions.InvalidLoginControllerException;
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
		Map<String, MachineBuilder> map = new HashMap<String, MachineBuilder>();
		try {
			for(MachineBuilder b : chainData.getController().getAllMachines(data.getLoginController()))
			{
				map.put(b.toString(), b);
			}
		} catch (InvalidLoginControllerException e) {
			System.out.println("You're not allowed to do that, aborting !");
			return new SelectUsecase(data);
		}
	
		
		SelectionMenu<MachineBuilder> menu = new SelectionMenu<MachineBuilder>(map);
		MachineBuilder builder = menu.execute();
		if(builder == null){
			System.out.println("Invalid menu option please try again");
			return this;			
		}
		chainData.add(builder);
		return new BuildMachine(data,chainData);
	}

}
