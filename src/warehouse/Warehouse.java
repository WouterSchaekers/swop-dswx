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
	private Map<Class<? extends WarehouseItemType>, Integer> maxItemsMap_;
	private LinkedList<WarehouseItem> items_;
	private Campus campus_;

	/**
	 * Initialises this warehouse.
	 * 
	 * @param campus
	 *            The campus this warehouse is for.
	 */
	public Warehouse(Campus campus) {
		this.campus_ = campus;
	}

	/**
	 * @return An item of the specified type that should be removed.
	 */
	public WarehouseItem take(WarehouseItemType item)
			throws InvalidWarehouseItemException {
		WarehouseItem item1 = null;
		for (WarehouseItem i : items_) {
			if (i.getType().equals(item)) {
				item1 = i;
			}
		}
		if (item1 == null)
			throw new InvalidWarehouseItemException(
					"the specified item type was not found in this warehouse");
		items_.remove(item1);
		this.notifyObservers();
		return item1;
	}

	/**
	 * Adds an item to this warehouse.
	 */
	public void add(WarehouseItem item) throws WarehouseOverCapacityException {
		if (!canBeAdded(item))
			throw new WarehouseOverCapacityException("hot");
		items_.add(item);
		this.notifyObservers();
	}

	/**
	 * @return True if item can be added to this warehouse.
	 */
	private boolean canBeAdded(WarehouseItem item) {
		return getMaxCount(item.getType()) > getCurrentCount(item.getType());
	}

	/**
	 * @return The maximum amount of units this warehouse can hold of the
	 *         specified type. Returns -1 if no such item was found.
	 */
	public int getMaxCount(WarehouseItemType type) {
		if (maxItemsMap_.containsKey(type))
			return maxItemsMap_.get(type);
		return -1;
	}

	/**
	 * Sets the maximum amount of units this warehouse can hold of a specific
	 * item type.
	 */
	public void setMaxCount(WarehouseItemType type, int count)
			throws InvalidWarehouseItemException {
		if (!maxItemsMap_.containsKey(type))
			maxItemsMap_.put(type.getClass(), count);
		else
			throw new InvalidWarehouseItemException(
					"Trying to set the max count of an invalid item type!");
	}

	/**
	 * This function is package visible because stock manager needs to access
	 * it.
	 * 
	 * @return The current amount of units of the given item type.
	 */
	public int getCurrentCount(WarehouseItemType type) {
		int rv = 0;
		for (WarehouseItem item : items_)
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
	
	/**
	 * <B>THIS METHOD IS TO BE USED BY EXPIREDITEMREMOVER ONLY! DO NOT USE ANYWHERE ELSE <u>EVER!<u></B>
	 */
	public void removeItem(WarehouseItem warehouseItem){
		this.items_.remove(warehouseItem);
	}

	@Basic
	public LinkedList<WarehouseItem> getAllItems() {
		return new LinkedList<WarehouseItem>(this.items_);
	}

	@Basic
	public Campus getCampus() {
		return this.campus_;
	}
}