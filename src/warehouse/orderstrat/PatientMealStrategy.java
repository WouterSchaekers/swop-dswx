package warehouse.orderstrat;

import help.Collections;
import patient.PatientFileManager;
import scheduler.TimeLord;
import warehouse.Warehouse;
import warehouse.item.MealType;
import warehouse.stock.StockProvider;

public class PatientMealStrategy extends OrderStrategy
{
	private PatientFileManager man_;

	public PatientMealStrategy(MealType type, Warehouse warehouse,
			StockProvider provider, TimeLord timeLord) {
		super(type, warehouse, provider, timeLord);
	}

	/**
	 * @return 15 + amount of active patients * 3meals/patient/day * 3 meals -
	 *         amount in stock
	 */
	protected int getNeeded() {
		int patients = Collections.filter(man_.getAllPatientFiles(), PatientFileManager.active).size();
		int amount = 15 + patients * 6 - warehouse_.getCurrentCount(type_);
		if (amount < 0)
			return 0;
		return amount;
	}

}
