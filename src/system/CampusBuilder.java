package system;

import warehouse.NormalWarehouseBuilder;
import warehouse.WarehouseBuilder;

public class CampusBuilder
{
	private String campusName_;
	private Hospital hospital_;
	private WarehouseBuilder warehouseBuilder_;
	public Campus create()
	{
	//	new NormalWarehouseBuilder(campus);
		warehouseBuilder_ = new NormalWarehouseBuilder();
		Campus campus = new Campus(campusName_, hospital_, warehouseBuilder_);
		
		return null;
		
	}
}
