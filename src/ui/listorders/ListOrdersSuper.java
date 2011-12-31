package ui.listorders;

import ui.Usecase;
import ui.UserinterfaceData;

public abstract class ListOrdersSuper extends Usecase
{
	protected ListOrderData chainData;

	public ListOrdersSuper(UserinterfaceData data) {
		this(data, new ListOrderData());
	}

	public ListOrdersSuper(UserinterfaceData data, ListOrderData listOrderData) {
		super(data);
		chainData = listOrderData;
	}
}