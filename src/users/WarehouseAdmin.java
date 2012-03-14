package users;

import scheduler.TimeLord;
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

	/**
	 * Default constructor. Will appoint this admin his warehouse.
	 * 
	 * @param depot
	 *            The warehouse of this warehouse admin.
	 * @param patientFileManager
	 *            The patientfile manager where this warehouse admin should get
	 *            the amount of active patients in its hospital.
	 * @throws InvalidNameException
	 */
	public WarehouseAdmin(String name, Warehouse warehouse)
			throws InvalidNameException {
		super(name);
		this._wh = warehouse;
	}
	
	public WarehouseAdmin(String name) throws InvalidNameException{
		super(name);
	}
	
	public void addItem(WarehouseItemType type) throws WarehouseOverCapacityException, NullPointerException
	{
		if(_wh == null){
			throw new NullPointerException("The warehouse has not been initialized yet.");
		}
		_wh.add(type.create(_wh.getSystemTime()));
	}
	
	public void addOrder(WarehouseItemType type,int count)
	{
		for(int i = 0;i<count;i++)
			stockProvider.add(new StockOrder<WarehouseItemType>(_wh, type, _timeKeeper.getSystemTime()));
	}


//TODO: fix this
//	private void advanceTime(HospitalDate newDate) throws MealException,
//			InvalidAmountException, WarehouseException {
//	//advance time
//	}
//	

}