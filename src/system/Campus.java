package system;

import java.util.ArrayList;
import java.util.Collection;
import machine.Machine;
import machine.MachinePool;
import scheduler.Schedulable;
import scheduler.TimeLord;
import warehouse.NormalWarehouseBuilder.WarehouseSet;
import warehouse.Warehouse;
import warehouse.WarehouseBuilder;
import warehouse.stock.StockProvider;
import controllers.interfaces.CampusIN;

/**
 * This class represents a campus that's part of a hospital. Campusses have
 * their own MachinePool, Warehouse and name. Use this name to identify them.
 */
public class Campus implements Location, CampusIN
{
	private String campusName_;
	private MachinePool machinePool_;
	private WarehouseSet warehouse_;
	private Hospital hospital_;

	/**
	 * Default constructor.
	 * 
	 * @param campusName
	 *            The name of the Campus.
	 * @param hospital
	 *            The Hospital of this Campus.
	 * @param warehouseBuilder
	 *            Builds the Warehouse for this Campus.
	 * @param machinePool
	 *            The MachinePool for this Campus.
	 */
	public Campus(String campusName, Hospital hospital, WarehouseBuilder warehouseBuilder, MachinePool machinePool) {
		this.campusName_ = campusName;
		this.hospital_ = hospital;
		hospital.addCampus(this);
		this.warehouse_ = warehouseBuilder.create(this);
		this.machinePool_ = machinePool;
		this.warehouse_.warehouse.addObserver(hospital.getTaskManager());
	}

	/**
	 * @return The Hospital of this Campus.
	 */
	public Hospital getHospital() {
		return this.hospital_;
	}

	/**
	 * @return The Warehouse of this Campus.
	 */
	public Warehouse getWarehouse() {
		return this.warehouse_.warehouse;
	}

	/**
	 * @return The StockProvider of the Warehouse of this Campus.
	 */
	public StockProvider getStockprovider() {
		return this.warehouse_.provider;
	}

	/**
	 * @return The MachinePool of this Campus.
	 */
	public MachinePool getMachinePool() {
		return this.machinePool_;
	}

	/**
	 * @return The SystemTime of the Hospital.
	 */
	public TimeLord getSystemTime() {
		return this.hospital_.getTimeKeeper();
	}

	/**
	 * @return The name of the Campus.
	 */
	public String getCampusName() {
		return this.campusName_;
	}

	/**
	 * @return A collection of all Machines in this Campus.
	 */
	public Collection<Schedulable> getSchedulables() {
		Collection<Schedulable> rv = new ArrayList<Schedulable>();
		for (Machine m : machinePool_.getAllMachines())
			rv.add(m);
		return rv;
	}

	/**
	 * The textual representation of the Campus.
	 */
	@Override
	public String toString() {
		return this.campusName_;
	}
	
	/**
	 * The textual representation of the Campus.
	 */
	@Override
	public String getName() {
		return this.campusName_;
	}
}