package controllers.interfaces;

import warehouse.item.WarehouseItemType;

@controllers.PUBLICAPI
public interface StockOrderIN
{
	@controllers.PUBLICAPI
	public boolean hasBeenDelivered();

@controllers.PUBLICAPI
	public boolean canBeDelivered();

@controllers.PUBLICAPI
	public <T extends WarehouseItemType> T getType();
}
