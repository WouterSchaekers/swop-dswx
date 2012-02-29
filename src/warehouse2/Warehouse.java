package warehouse2;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import warehouse2.items.WarehouseItem;

/**
 * Draft version of WareHouse class
 * 
 */
public class Warehouse
{
	private Collection<WarehouseItem> _items;
	private Map<Class<? extends WarehouseItem>, Integer> _maxMap;

	public Warehouse() {
		_items = new LinkedList<WarehouseItem>();
		_maxMap = new HashMap<Class<? extends WarehouseItem>, Integer>();

	}

	public void setMaxCount(Class<? extends WarehouseItem> type, int count) {
		_maxMap.put(type, count);
	}

	public void add(WarehouseItem item) throws WareHouseOverFlowException {
		if (!canBeAdded(item.getClass()))
			throw new WareHouseOverFlowException();
		_items.add(item);
	}

	private boolean canBeAdded(Class<? extends WarehouseItem> type) {
		// Check max count
		if (!_maxMap.containsKey(type))
			return false;
		if (getMaxCount(type) < getCurrentCountFor(type) + 1)
			return false;
		return true;

	}

	public int getCurrentCountFor(Class<? extends WarehouseItem> type) {
		int rv = 0;
		for (WarehouseItem i : _items)
			if (type.equals(i.getClass()))
				rv++;
		return rv;
	}

	public int getMaxCount(Class<? extends WarehouseItem> type) {
		return _maxMap.get(type);
	}
}
