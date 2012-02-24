package warehouse2;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import warehouse2.items.WareHouseItem;
/**
 * Draft version of WareHouse class
 *
 */
public class WareHouse
{
	private Collection<WareHouseItem> _items;
	private Map<Class<? extends WareHouseItem>, Integer> _maxMap;

	public WareHouse() {
		_items = new LinkedList<WareHouseItem>();
		_maxMap = new HashMap<Class<? extends WareHouseItem>, Integer>();

	}

	public void setMaxCount(Class<? extends WareHouseItem> type, int count) {
		_maxMap.put(type, count);
	}

	public void add(WareHouseItem item) throws WareHouseOverFlowException {
		if (!canBeAdded(item))
			throw new WareHouseOverFlowException();
		_items.add(item);
	}

	private boolean canBeAdded(WareHouseItem item) {
		// Check max count
		if (!_maxMap.containsKey(item.getClass()))
			return false;
		if (getMaxCount(item) < getCurrentCountFor(item) + 1)
			return false;
		return true;

	}

	public int getCurrentCountFor(WareHouseItem item) {
		int rv = 0;
		for (WareHouseItem i : _items)
			if (i.getClass().equals(item.getClass()))
				rv++;
		return rv;
	}

	public int getMaxCount(WareHouseItem item) {
		return _maxMap.get(item.getClass());
	}
}
