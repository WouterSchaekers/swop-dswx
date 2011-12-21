package users;

import java.util.Collection;
import treatment.Medication;
import warehouse.Warehouse;
import warehouse.Meal;
import exceptions.DepotOverCapacityException;
import exceptions.InvalidNameException;

/**
 * This class represents the administrator of the warehouse that is in the
 * hospital.
 */
public class WarehouseAdmin extends User
{
	private Warehouse depot;

	/**
	 * Default constructor. Will appoint this admin his warehouse.
	 * 
	 * @param depot
	 *            The warehouse of this warehouse admin.
	 * @throws InvalidNameException
	 */
	public WarehouseAdmin(Warehouse depot) throws InvalidNameException {
		super("The Warehouse administrator");
		this.depot = depot;
	}

	/**
	 * This method adds plaster to the warehouse that has been assigned to this
	 * warehouse admin.
	 * 
	 * @param units
	 *            The amount of plaster to add to the warehouse.
	 * @throws DepotOverCapacityException
	 */
	public void addPlaster(int units) throws DepotOverCapacityException {
		depot.addPlaster(units);
	}

	/**
	 * This method adds medication to the warehouse that has been assigned to
	 * this warehouse admin.
	 * 
	 * @param units
	 *            The amount of medication to add to the warehouse.
	 * @throws DepotOverCapacityException
	 */
	public void addMedication(Collection<Medication> medication)
			throws DepotOverCapacityException {
		depot.addMedication(medication);
	}

	/**
	 * This method adds meals to the warehouse that has been assigned to this
	 * warehouse admin.
	 * 
	 * @param units
	 *            The amount of meals to add to the warehouse.
	 * @throws DepotOverCapacityException
	 */
	public void addMeals(Collection<Meal> meals)
			throws DepotOverCapacityException {
		depot.addMeals(meals);
	}

	/**
	 * @return True if u is a valid amount of units for whatever kind of thing
	 *         one would like to add to the warehouse of this warehouse
	 *         administrator.
	 */
	public static boolean isValidAmountOfUnits(int u) {
		return u >= 0;
	}
}