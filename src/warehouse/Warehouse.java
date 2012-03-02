package warehouse;

import java.util.Collection;
import java.util.Map;
import exceptions.WarehouseOverCapacityException;
import warehouse.item.WarehouseItem;
import warehouse.item.WarehouseItemType;

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
	public void setMaxCount(WarehouseItemType type,int count)
	{
		if(!_maxMap.containsKey(type))
			_maxMap.put(type.getClass(), count);
		else
			return;
			
	}
	private int getCurrentCount(WarehouseItemType type)
	{
		int rv= 0;
		for(WarehouseItem item:_items)
			if(item.getType().equals(type))
				rv++;
		return rv;
	}
	public boolean has(WarehouseItemType type,int amount)
	{
		return getCurrentCount(type)>=amount;
	}
}
