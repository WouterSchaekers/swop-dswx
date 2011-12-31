package observers;

import java.util.Observable;
import java.util.Observer;
import scheduler.HospitalDate;
import warehouse.StockProvider;

public class TimeLordObserverStockProvider implements Observer
{
	StockProvider stockProvider;

	TimeLordObserverStockProvider(StockProvider stockProvider) {
		this.stockProvider = stockProvider;
	}

	@Override
	public void update(Observable arg0, Object newDate) {
		if (!(newDate instanceof HospitalDate))
			throw new IllegalArgumentException(
					"Object given to TimeLordObserver was not a hospital date!");
		try {
			this.stockProvider.updateTime((HospitalDate) newDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
