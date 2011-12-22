package external;

public class StockOrder<T>
{
	private int amount;
	public StockOrder(int amount)
	{
		this.amount=amount;
	}
	int getAmount(){
		return amount;
		
	}
}
