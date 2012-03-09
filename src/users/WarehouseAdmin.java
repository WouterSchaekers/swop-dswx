package users;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFileManager;
import scheduler.DummyDate;
import scheduler.HospitalDate;
import scheduler.TimeLord;
import treatment.Medication;
import warehouse.Warehouse;
import warehouse.item.ActivatedCarbonType;
import warehouse.item.Meal;
import warehouse.item.MiscType;
import warehouse.item.Plaster;
import warehouse.item.SleepingTabletsType;
import warehouse.item.WarehouseItemType;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;
import controllers.interfaces.WarehouseAdminIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidNameException;
import exceptions.MealException;
import exceptions.WarehouseException;
import exceptions.WarehouseOverCapacityException;

/**
 * This class represents the administrator of the warehouse that is in the
 * hospital.
 */
public class WarehouseAdmin extends User implements WarehouseAdminIN
{
	private Warehouse warehouse;
	private StockProvider stockProvider;
	private TimeLord	_timeKeeper;

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
	public WarehouseAdmin(String name, Warehouse warehouse,
			StockProvider stockProvider,TimeLord timelord)
			throws InvalidNameException {
		super(name);
		this.warehouse = warehouse;
		this.stockProvider = stockProvider;
		this._timeKeeper =timelord;
		
		
		
	}

	public StockProvider getStockProvider() {
		return this.stockProvider;
	}
	public void addItem(WarehouseItemType type) throws WarehouseOverCapacityException
	{
		warehouse.add(type.create(_timeKeeper.getSystemTime()));
	}
	public void addOrder(WarehouseItemType type,int count)
	{
		for(int i = 0;i<count;i++)
			stockProvider.add(new StockOrder<WarehouseItemType>(warehouse, type, _timeKeeper.getSystemTime()));
	}


//TODO: fix this
	private void advanceTime(HospitalDate newDate) throws MealException,
			InvalidAmountException, WarehouseException {
	//advance time
	}
	






	

}