package warehouse;

import java.util.Collection;
import java.util.Map;
import exceptions.WarehouseOverCapacityException;
import warehouse2.item.WarehouseItem;
import warehouse2.item.WarehouseItemType;

public class Warehouse
{
	private Map<Class<?extends WarehouseItemType>, Integer> _maxMap;
	private Collection<WarehouseItem> _items;
	public Warehouse()
	{
		
	}
	public void add(WarehouseItem item) throws WarehouseOverCapacityException
	{
		if(!canBeAdded(item))
			throw new WarehouseOverCapacityException("hot");
		_items.add(item);
	}
	private boolean canBeAdded(WarehouseItem item) {
		return getMaxCount(item.getType()) >= getCurrentCount(item.getType())+1;
	}
	public int getMaxCount(WarehouseItemType type)
	{
		if(_maxMap.containsKey(type))
			return _maxMap.get(type);
		else
			return 0;
	}
	public int getCurrentCount(WarehouseItemType type)
	{
		int rv= 0;
		for(WarehouseItem item:_items)
			if(item.getType().equals(type))
				rv++;
		return rv;
	}
}
