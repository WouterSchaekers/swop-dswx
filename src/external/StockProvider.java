package external;

import java.util.Collection;

public class StockProvider
{
	private Collection<StockOrder> orders;

	public void setOrders(Collection<StockOrder> orders) {
		this.orders = orders;
	}
	
	//TODO fix dat het exceptions throwt
	public void addOrder(StockOrder o) {
		this.orders.add(o);
	}

	public Collection<StockOrder> getOrders() {
		return orders;
	}
}
