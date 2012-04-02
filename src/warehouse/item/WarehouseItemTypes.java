package warehouse.item;

import java.util.Collection;
import java.util.LinkedList;

public class WarehouseItemTypes
{
	public static Collection<WarehouseItemType> itemTypes() {
		Collection<WarehouseItemType> rv = new LinkedList<WarehouseItemType>();
		rv.add(new ActivatedCarbonType());
		rv.add(new AsprinType());
		rv.add(new MealType());
		rv.add(new MiscType());
		rv.add(new PlasterType());
		rv.add(new VitaminType());
		return rv;
	}
	
}
