
package warehouse.orderstrat;

import help.Filter;
import java.util.Observable;
import patient.PatientFileManager;
import warehouse.Warehouse;
import warehouse.item.MealType;
import warehouse.item.WarehouseItemType;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;

public class PatientMealStrategy extends OrderStrategy
{
	public PatientMealStrategy(MealType type, Warehouse warehouse,
			StockProvider provider) {
		super(type, warehouse, provider);
		
	}

	private PatientFileManager man_;

	private int countNeeded() {
		int patients = man_.amountOfActivePatients();
		return patients * 4;// ofzo
	}

	@Override
	public void update(Observable o, Object arg) {
		int needed =Math.min(countNeeded(),warehouse_.getMaxCount(_type));
		int current = calculateCurrent();
		int needy = needed-current;
		while(needy>0)
		{
			provider_.add(new StockOrder<WarehouseItemType>(warehouse_, _type));
			needy--;
		}
	}

	private int calculateCurrent() {
		int futureCount = warehouse_.getCurrentCount(_type);
		futureCount+=help.Collections.filter(provider_.getOrders(), new Filter()
		{
			
			@Override
			public <T> boolean allows(T arg) {
				if(arg instanceof StockOrder)
					return ((StockOrder<?>)arg).getType().equals(_type);
				return false;
			}
		}).size();
		return futureCount;
	}
	

}
