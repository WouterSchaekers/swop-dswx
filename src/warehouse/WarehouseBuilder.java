package warehouse;

import system.Campus;
import warehouse.NormalWarehouseBuilder.WarehouseSet;

public interface WarehouseBuilder
{

	public abstract WarehouseSet create(Campus campus);

}