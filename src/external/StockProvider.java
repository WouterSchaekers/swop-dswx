package external;

import java.util.Collection;
import javax.swing.Spring;

public class StockProvider
{
	//XXX:: waaroom is dit generic help !@
	private Collection<StockOrder<Spring>> orders;

	public void setOrders(Collection<StockOrder<Spring>> orders) {
		this.orders = orders;
	}

	public Collection<StockOrder<Spring>> getOrders() {
		return orders;
	}
}
