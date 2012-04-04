package system;

import machine.MachinePool;
import machine.MachinePoolBuilder;
import warehouse.NormalWarehouseBuilder;
import warehouse.WarehouseBuilder;

public class CampusBuilder
{
	private String campusName_;
	private Hospital hospital_;
	private WarehouseBuilder warehouseBuilder_;
	public CampusBuilder(String string,Hospital hospital)
	{
		campusName_ = string;
		hospital_=hospital;
	}
	public Campus create()
	{
		warehouseBuilder_ = new NormalWarehouseBuilder();
		Campus campus = new Campus(campusName_, hospital_, warehouseBuilder_,new MachinePoolBuilder().create());
		return campus;
		
	}
}
