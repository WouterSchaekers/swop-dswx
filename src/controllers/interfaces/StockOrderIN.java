package controllers.interfaces;

import warehouse.item.WarehouseItemType;

public interface StockOrderIN
{
	public boolean hasBeenDelivered();

	public boolean canBeDelivered();
	
	public <T extends WarehouseItemType> T getType();
}
