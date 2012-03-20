package users;

import java.util.LinkedList;
import scheduler.HospitalDate;
import warehouse.Warehouse;
import warehouse.item.WarehouseItem;
import warehouse.stock.StockProvider;
import controllers.interfaces.WarehouseAdminIN;
import exceptions.InvalidNameException;

/**
 * This class represents the administrator of the warehouse that is associated
 * with a campus.
 */
public class WarehouseAdmin extends User implements WarehouseAdminIN
{
	private Warehouse warehouse_;
	private StockProvider provider_;

	/**
	 * Initialises this warehouse admin and appoints him to a warehouse. Also
	 * provides him with a stockprovider.
	 * 
	 * @param name
	 *            The desired name for this warehouse admin.
	 * @param warehouse
	 *            The warehouse this admin manages.
	 * @param stockProvider
	 *            The stock provider this admin can place stock orers with.
	 */
	public WarehouseAdmin(String name, Warehouse warehouse,	StockProvider stockProvider) throws InvalidNameException {
		super(name);
		this.warehouse_ = warehouse;
		this.provider_ = stockProvider;
	}
	
	/**
	 * Updates the warehouse the way it has to after the time has changed.
	 */
	public void updateTime() {
		this.removeExpiredItems();
		
	}
	
	private void removeExpiredItems() {
		LinkedList<WarehouseItem> items = warehouse_.getAllItems();
		HospitalDate date = warehouse_.getCampus().getSystemTime();
		for(WarehouseItem i : items)
			if(i.isExpiredAt(date))
				warehouse_.removeItem(i);
	}
	
//	/**
//	 * Places stock orders for the depleting supplies of the warehouse.
//	 */
//	private void placeOrders() {
//		Collection<WarehouseItemType> lowItems = this.warehouse_.getLowItemTypes();
//		
//		for(WarehouseItemType t : lowItems)
//			this.provider_.add(new StockOrder<WarehouseItemType>(this, t));
//		
//	}

//	/**
//	 * Initialises a collection of default ordering strategies.
//	 */
//	private Collection<OrderStrategy> defaultOrderStrategies() {
//		Collection<OrderStrategy> strat = new LinkedList<OrderStrategy>();
//		strat.add(new PatientMealStrategy(new MealType(), warehouse_, provider_));
//		//TODO: add more strategies?
//		return strat;
//	}
//	/**
//	 * Manually adds a stockorder to a stock provider so that new items can be delivered.
//	 */
//	public void addOrder(WarehouseItemType type, int count) {
//		for (int i = 0; i < count; i++)
//			provider_.add(new StockOrder<WarehouseItemType>(this, type));
//	}
//	/**
//	 * Adds an item of the specified type to the warehouse associated with this warehouse admin.
//	 */
//	public void addItem(WarehouseItemType type)
//			throws WarehouseOverCapacityException{
//		warehouse_.add(type.create(warehouse_.getCampus().getSystemTime()));
//	}

}