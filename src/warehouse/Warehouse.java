package warehouse;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import system.Campus;
import warehouse.item.PlasterType;
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
		else
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
	 * @return True if this warehouse is low on items of the specified type.
	 */
	private boolean isLowOn(WarehouseItemType type) {
		if(type instanceof PlasterType)
			return this.getMaxCount(type) < maxItemsMap_.get(type);
		return this.getCurrentCount(type) < (maxItemsMap_.get(type))/2;
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
	 * Use this method only in warehouse admin.
	 */
	public void removeExpiredItems() {
		
	}
	
	/**
	 * @return The item types that this warehouse is low on.
	 */
	//XXX:guys this is bad mowkay, er zou 1 objectje per type moeten zijn om de stock te managen
	// het 'low' wijn van iets is niet een eigenschap van het warehouse, maar van het aantal items
	// in het warehouse & het aantal items in de stockprovider =/
	public Collection<WarehouseItemType> getLowStockItemTypes() {
		Collection<WarehouseItemType> rv = new LinkedList<WarehouseItemType>();
		
		for(WarehouseItem item : this.items_) {
			if(this.isLowOn(item.getType()))
				rv.add(item.getType());
		}
		return rv;
	}

	@Basic
	public Campus getCampus() {
		return this.campus_;
	}
}