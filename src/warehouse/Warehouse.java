package warehouse;

import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import system.Campus;
import warehouse.item.WarehouseItem;
import warehouse.item.WarehouseItemType;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidWarehouseItemException;
import exceptions.WarehouseOverCapacityException;

/**
 * This class represents a warehouse on the campus of a hospital.
 */
public class Warehouse extends Observable
{
	private Map<Class<? extends WarehouseItemType>, Integer> _maxMap;
	private LinkedList<WarehouseItem> _items;
	private Campus _cp;

	/**
	 * Initialises this warehouse.
	 * 
	 * @param campus
	 *            The campus this warehouse is for.
	 */
	public Warehouse(Campus campus) {
		this._cp = campus;
	}

	/**
	 * @return An item of the specified type that should be removed.
	 */
	public WarehouseItem take(WarehouseItemType item) throws InvalidWarehouseItemException {
		WarehouseItem item1 = null;
		for (WarehouseItem i : _items) {
			if (i.getType().equals(item)) {
				item1 = i;
			}
		}
		
		if(item1 == null) 
			throw new InvalidWarehouseItemException("the specified item type was not found in this warehouse");
		
		_items.remove(item1);
		this.notifyObservers();
		return item1;
	}

	/**
	 * Adds an item to this warehouse.
	 */
	public void add(WarehouseItem item) throws WarehouseOverCapacityException {
		if (!canBeAdded(item))
			throw new WarehouseOverCapacityException("hot");
		_items.add(item);
		this.notifyObservers();
	}

	/**
	 * @return True if item can be added to this warehouse.
	 */
	private boolean canBeAdded(WarehouseItem item) {
		return getMaxCount(item.getType()) >= getCurrentCount(item.getType()) + 1;
	}

	/**
	 * @return The maximum amount of units this warehouse can hold of the specified type.
	 */
	public int getMaxCount(WarehouseItemType type) {
		if (_maxMap.containsKey(type))
			return _maxMap.get(type);
		else
			return 0;
	}

	/**
	 * Sets the maximum amount of units this warehouse can hold of a specific
	 * item type.
	 */
	public void setMaxCount(WarehouseItemType type, int count)
			throws InvalidWarehouseItemException {
		if (!_maxMap.containsKey(type))
			_maxMap.put(type.getClass(), count);
		else
			throw new InvalidWarehouseItemException(
					"Trying to set the max count of an invalid item type!");
	}

	/**
	 * @return The current amount of units of the given item type.
	 */
	// This function is package visible because stock manager needs to access
	// it.
	int getCurrentCount(WarehouseItemType type) {
		int rv = 0;
		for (WarehouseItem item : _items)
			if (item.getType().equals(type))
				rv++;
		return rv;
	}

	/**
	 * @return True if this warehouse has the requested amount of units of the
	 *         given type.
	 */
	public boolean has(WarehouseItemType type, int amount) {
		return getCurrentCount(type) >= amount;
	}

	@Basic
	public Campus getCampus() {
		return this._cp;
	}
}