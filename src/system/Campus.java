package system;

import java.util.ArrayList;
import java.util.Collection;
import machine.Machine;
import machine.MachinePool;
import scheduler.Schedulable;
import scheduler.TimeLord;
import warehouse.NormalWarehouseBuilder;
import warehouse.NormalWarehouseBuilder.WarehouseSet;
import warehouse.Warehouse;
import warehouse.stock.StockProvider;

/**
 * This class represents a campus that's part of a hospital. Campusses have
 * their own MachinePool, Warehouse and name. Use this name to identify them.
 */
public class Campus implements Location
{
	private String campusName_;
	private MachinePool machinePool_ = new MachinePool();
	private WarehouseSet warehouseSet_;
	private Hospital hospital_;

	/**
	 * @param timeLord
	 * To create normal warehousebuilder with
	 */
	public Campus(String campusName, Hospital hospital, TimeLord timeLord) {
		this.campusName_ = campusName;
		this.hospital_ = hospital;
		this.warehouseSet_ = new NormalWarehouseBuilder(this, timeLord).create();
	}

	public Hospital getHospital() {
		return this.hospital_;
	}

	public void setHospital(Hospital hospital) {
		this.hospital_ = hospital;
	}

	public Warehouse getWarehouse() {
		return this.warehouseSet_.warehouse;
	}

	public StockProvider getStockprovider() {
		return this.warehouseSet_.provider;
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
}