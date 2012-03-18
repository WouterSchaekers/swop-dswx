package warehouse.orderstrat;

import java.util.Observable;
import warehouse.Warehouse;
import warehouse.item.PlasterType;
import warehouse.stock.StockProvider;

public class PlasterOrderStrategy extends OrderStrategy 
{

	public PlasterOrderStrategy( Warehouse warehouse,
			StockProvider provider) {
		super(new PlasterType(), warehouse, provider);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//TODO:make the orderstrategy
	}

}
