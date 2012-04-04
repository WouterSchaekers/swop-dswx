package system;

import java.util.ArrayList;
import java.util.Collection;
import controllers.interfaces.CampusIN;
import machine.Machine;
import machine.MachinePool;
import scheduler.Schedulable;
import scheduler.TimeLord;
import warehouse.NormalWarehouseBuilder.WarehouseSet;
import warehouse.Warehouse;
import warehouse.WarehouseBuilder;
import warehouse.stock.StockProvider;

/**
 * This class represents a campus that's part of a hospital. Campusses have
 * their own MachinePool, Warehouse and name. Use this name to identify them.
 */
public class Campus implements Location,CampusIN
{
	private String campusName_;
	private MachinePool machinePool_ ;
	private WarehouseSet warehouse_;
	private Hospital hospital_;

	/**
	 * Creates a Campus & creates the double bind to hospital.
	 * @param warehouseBuilder TODO
	 */
	public Campus(String campusName, Hospital hospital, WarehouseBuilder warehouseBuilder,MachinePool machinePool) {
		this.campusName_ = campusName;
		this.hospital_ = hospital;
		hospital.addCampus(this);
		this.warehouse_ =warehouseBuilder.create(this);
		this.machinePool_=machinePool;
	}

	public Hospital getHospital() {
		return this.hospital_;
	}

	

	public Warehouse getWarehouse() {
		return this.warehouse_.warehouse;
	}

	public StockProvider getStockprovider() {
		return this.warehouse_.provider;
	}

	public MachinePool getMachinePool() {
		return this.machinePool_;
	}

	public TimeLord getSystemTime() {
		return this.hospital_.getTimeKeeper();
	}

	public String getCampusName() {
		return this.campusName_;
	}

	public Collection<Schedulable> getSchedulables() {
		Collection<Schedulable> rv = new ArrayList<Schedulable>();
		for (Machine m : machinePool_.getAllMachines())
			rv.add(m);
		return rv;
	}

	@Override
	public String toString() {
		String rv = this.campusName_;
		for (int i = rv.length(); i < 14; i++) {
			rv += " ";
		}
		return rv;
	}

	@Override
	public String getName() {
		
		return campusName_;
	}
}