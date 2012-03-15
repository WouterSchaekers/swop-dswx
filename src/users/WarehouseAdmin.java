package users;

import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;
import controllers.interfaces.WarehouseAdminIN;
import exceptions.InvalidNameException;
import exceptions.WarehouseOverCapacityException;

/**
 * This class represents the administrator of the warehouse that is in the
 * hospital.
 */
public class WarehouseAdmin extends User implements WarehouseAdminIN
{
	private Warehouse _wh;
	private StockProvider _spv;

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
	 * @throws InvalidNameException
	 */
	public WarehouseAdmin(String name, Warehouse warehouse,
			StockProvider stockProvider) throws InvalidNameException {
		super(name);
		this._wh = warehouse;
		this._spv = stockProvider;
	}

	/**
	 * Adds an item of the specified type to the warehouse associated with this warehouse admin.
	 */
	public void addItem(WarehouseItemType type)
			throws WarehouseOverCapacityException{
		_wh.add(type.create(_wh.getCampus().getSystemTime()));
	}

	public void addOrder(WarehouseItemType type, int count) {
		for (int i = 0; i < count; i++)
			_spv.add(new StockOrder<WarehouseItemType>(_wh, type, _wh
					.getCampus().getSystemTime()));
	}
	//TODO: wat gebeurt er bij advance time()?
}