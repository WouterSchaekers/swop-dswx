package warehouse.orderstrat;

import patient.PatientFileManager;
import warehouse.Warehouse;
import warehouse.item.MealType;
import warehouse.stock.StockProvider;

public class PatientMealStrategy extends OrderStrategy
{
	private PatientFileManager man_;

	public PatientMealStrategy(MealType type, Warehouse warehouse,
			StockProvider provider) {
		super(type, warehouse, provider);
	}

	/**
	 * @return 15 + amount of active patients * 3meals/patient/day * 3 meals -
	 *         amount in stock
	 */
	protected int getNeeded() {
		int patients = man_.amountOfActivePatients();
		int amount = 15 + patients * 6 - warehouse_.getCurrentCount(type_);
		if (amount < 0)
			return 0;
		return amount;
	}

}
