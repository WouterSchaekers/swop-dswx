package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import scheduler.HospitalDate;
import exceptions.WarehouseOverCapacityException;
import warehouse.item.WarehouseItem;
import warehouse.item.WarehouseItemType;

public class Warehouse extends Observable
{
	private Map<Class<? extends WarehouseItemType>, Integer> _maxMap;
	private Collection<WarehouseItem> _items;

	public Warehouse() {

	}
	public WarehouseItem take(WarehouseItemType item) 
	{
		WarehouseItem item1 = null;
		for (WarehouseItem i : _items) {
			if (i.getType().equals(item)) {
				item1 = i;

			}
		}
		_items.remove(item1);
		this.notifyObservers();
		return item1;
	}

	public void add(WarehouseItem item) throws WarehouseOverCapacityException {
		if (!canBeAdded(item))
			throw new WarehouseOverCapacityException("hot");
		_items.add(item);
		this.notifyObservers();
	}

	private boolean canBeAdded(WarehouseItem item) {
		return getMaxCount(item.getType()) >= getCurrentCount(item.getType()) + 1;
	}

	public int getMaxCount(WarehouseItemType type) {
		if (_maxMap.containsKey(type))
			return _maxMap.get(type);
		else
			return 0;
	}

	// TODO: schrijf in verslag waarom getclas()
	public void setMaxCount(WarehouseItemType type, int count) {
		if (!_maxMap.containsKey(type))
			_maxMap.put(type.getClass(), count);
		else
			return;

	}
	/**
	 * Package only want stockmanager moet daaraan kunnen.
	 * @param type
	 * @return
	 */
	int getCurrentCount(WarehouseItemType type) {
		int rv = 0;
		for (WarehouseItem item : _items)
			if (item.getType().equals(type))
				rv++;
		return rv;
	}

	public boolean has(WarehouseItemType type, int amount) {
		return getCurrentCount(type) >= amount;
	}
}
